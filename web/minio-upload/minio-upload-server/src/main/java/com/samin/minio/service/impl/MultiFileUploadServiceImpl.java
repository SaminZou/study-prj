package com.samin.minio.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.ListPartsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.PartListing;
import com.amazonaws.services.s3.model.PartSummary;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.samin.minio.constant.MinioConstant;
import com.samin.minio.mapper.MultiFileUploadMapper;
import com.samin.minio.model.dto.RecordDTO;
import com.samin.minio.model.dto.TaskInfoDTO;
import com.samin.minio.enums.ResultEnum;
import com.samin.minio.exception.BusinessException;
import com.samin.minio.model.entity.MultiFileUpload;
import com.samin.minio.model.param.InitTaskParam;
import com.samin.minio.properties.MinioProperties;
import com.samin.minio.service.MultiFileUploadService;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
 * 分片上传-分片任务记录（MultiFileUpload）表服务实现类
 *
 * @since 2022-08-22 17:47:31
 */
@Service
@RequiredArgsConstructor
public class MultiFileUploadServiceImpl extends ServiceImpl<MultiFileUploadMapper, MultiFileUpload> implements
        MultiFileUploadService {

    private final AmazonS3 amazonS3;

    private final MinioProperties minioProperties;

    private final MultiFileUploadMapper multiFileUploadMapper;

    @Override
    public MultiFileUpload getByIdentifier(String identifier) {
        return multiFileUploadMapper.selectOne(new QueryWrapper<MultiFileUpload>().lambda()
                                                                                .eq(MultiFileUpload::getFileIdentifier,
                                                                                    identifier));
    }


    @Override
    public TaskInfoDTO initTask(InitTaskParam param) {
        Date currentDate = new Date();
        String bucketName = minioProperties.getBucket();
        String key = generateObjectKey(param.getFileName(), currentDate);
        String contentType = determineContentType(key);
        String uploadId = initiateMultipartUpload(bucketName, key, contentType);
        
        MultiFileUpload task = createUploadTask(param, bucketName, key, uploadId);
        multiFileUploadMapper.insert(task);
        
        return buildTaskInfoDTO(task, bucketName, key, false);
    }
    
    /**
     * 生成对象存储键
     */
    private String generateObjectKey(String fileName, Date currentDate) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return StrUtil.format("{}/{}.{}", DateUtil.format(currentDate, "YYYY-MM-dd"), IdUtil.randomUUID(), suffix);
    }
    
    /**
     * 确定文件内容类型
     */
    private String determineContentType(String key) {
        return MediaTypeFactory.getMediaType(key)
                              .orElse(MediaType.APPLICATION_OCTET_STREAM)
                              .toString();
    }
    
    /**
     * 初始化分片上传
     */
    private String initiateMultipartUpload(String bucketName, String key, String contentType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        InitiateMultipartUploadResult result = amazonS3.initiateMultipartUpload(
                new InitiateMultipartUploadRequest(bucketName, key).withObjectMetadata(objectMetadata));
        return result.getUploadId();
    }
    
    /**
     * 创建上传任务记录
     */
    private MultiFileUpload createUploadTask(InitTaskParam param, String bucketName, String key, String uploadId) {
        int chunkNum = (int) Math.ceil(param.getTotalSize() * 1.0 / param.getChunkSize());
        
        return new MultiFileUpload()
                .setBucketName(bucketName)
                .setChunkNum(chunkNum)
                .setChunkSize(param.getChunkSize())
                .setTotalSize(param.getTotalSize())
                .setFileIdentifier(param.getIdentifier())
                .setFileName(param.getFileName())
                .setObjectKey(key)
                .setUploadId(uploadId);
    }
    
    /**
     * 构建任务信息DTO
     */
    private TaskInfoDTO buildTaskInfoDTO(MultiFileUpload task, String bucketName, String key, boolean finished) {
        return new TaskInfoDTO()
                .setFinished(finished)
                .setTaskRecord(RecordDTO.convertFromEntity(task))
                .setPath(getPath(bucketName, key));
    }

    @Override
    public String getPath(String bucket, String objectKey) {
        return StrUtil.format("{}/{}/{}", minioProperties.getEndpoint(), bucket, objectKey);
    }

    @Override
    public TaskInfoDTO getTaskInfo(String identifier) {
        MultiFileUpload task = getByIdentifier(identifier);
        if (task == null) {
            return null;
        }
        
        TaskInfoDTO result = buildTaskInfoDTO(task, task.getBucketName(), task.getObjectKey(), true);
        
        boolean isUploadComplete = isUploadComplete(task);
        if (!isUploadComplete) {
            List<PartSummary> uploadedParts = listUploadedParts(task);
            updateTaskInfoWithUploadedParts(result, uploadedParts);
        }
        
        return result;
    }
    
    /**
     * 检查上传是否完成
     */
    private boolean isUploadComplete(MultiFileUpload task) {
        return amazonS3.doesObjectExist(task.getBucketName(), task.getObjectKey());
    }
    
    /**
     * 更新任务信息，添加已上传的分片列表
     */
    private void updateTaskInfoWithUploadedParts(TaskInfoDTO taskInfo, List<PartSummary> uploadedParts) {
        taskInfo.setFinished(false)
               .getTaskRecord()
               .setExitPartList(uploadedParts);
    }

    @Override
    public String genPreSignUploadUrl(String bucket, String objectKey, Map<String, String> params) {
        Date currentDate = new Date();
        Date expireDate = DateUtil.offsetMillisecond(currentDate, MinioConstant.PRE_SIGN_URL_EXPIRE.intValue());
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, objectKey).withExpiration(expireDate)
                                                                                                .withMethod(HttpMethod.PUT);
        if (params != null) {
            params.forEach((key, val) -> request.addRequestParameter(key, val));
        }
        URL preSignedUrl = amazonS3.generatePresignedUrl(request);
        return preSignedUrl.toString();
    }

    @Override
    public void merge(String identifier) {
        MultiFileUpload task = getByIdentifier(identifier);
        if (task == null) {
            throw new BusinessException(ResultEnum.ERROR_400, "分片任务不存在");
        }

        List<PartSummary> uploadedParts = listUploadedParts(task);
        validatePartsCount(uploadedParts, task.getChunkNum());
        completeMultipartUpload(task, uploadedParts);
    }
    
    /**
     * 列出已上传的分片
     */
    private List<PartSummary> listUploadedParts(MultiFileUpload task) {
        ListPartsRequest listPartsRequest = new ListPartsRequest(
                task.getBucketName(), 
                task.getObjectKey(), 
                task.getUploadId());
        PartListing partListing = amazonS3.listParts(listPartsRequest);
        return partListing.getParts();
    }
    
    /**
     * 验证分片数量是否正确
     */
    private void validatePartsCount(List<PartSummary> uploadedParts, Integer expectedCount) {
        if (!expectedCount.equals(uploadedParts.size())) {
            throw new BusinessException(ResultEnum.ERROR_400, "分片缺失，请重新上传");
        }
    }
    
    /**
     * 完成分片合并
     */
    private void completeMultipartUpload(MultiFileUpload task, List<PartSummary> uploadedParts) {
        List<PartETag> partETags = uploadedParts.stream()
                                               .map(partSummary -> new PartETag(
                                                       partSummary.getPartNumber(),
                                                       partSummary.getETag()))
                                               .collect(Collectors.toList());
        
        CompleteMultipartUploadRequest request = new CompleteMultipartUploadRequest()
                .withUploadId(task.getUploadId())
                .withKey(task.getObjectKey())
                .withBucketName(task.getBucketName())
                .withPartETags(partETags);
                
        amazonS3.completeMultipartUpload(request);
    }
}
