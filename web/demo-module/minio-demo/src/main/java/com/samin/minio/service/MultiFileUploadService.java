package com.samin.minio.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.samin.minio.model.dto.TaskInfoDTO;
import com.samin.minio.model.entity.MultiFileUpload;
import com.samin.minio.model.param.InitTaskParam;

import java.util.Map;

/**
 * 分片上传-分片任务记录(MultiFileUpload)表服务接口
 *
 * @since 2022-08-22 17:47:30
 */
public interface MultiFileUploadService extends IService<MultiFileUpload> {

    /**
     * 根据md5标识获取分片上传任务
      * @param identifier
     * @return
     */
    MultiFileUpload getByIdentifier (String identifier);

    /**
     * 初始化一个任务
     */
    TaskInfoDTO initTask (InitTaskParam param);

    /**
     * 获取文件地址
     * @param bucket
     * @param objectKey
     * @return
     */
    String getPath (String bucket, String objectKey);

    /**
     * 获取上传进度
     * @param identifier
     * @return
     */
    TaskInfoDTO getTaskInfo (String identifier);

    /**
     * 生成预签名上传url
     * @param bucket 桶名
     * @param objectKey 对象的key
     * @param params 额外的参数
     * @return
     */
    String genPreSignUploadUrl (String bucket, String objectKey, Map<String, String> params);

    /**
     * 合并分片
     * @param identifier
     */
    void merge (String identifier);
}
