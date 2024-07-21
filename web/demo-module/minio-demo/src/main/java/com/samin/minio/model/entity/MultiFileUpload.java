package com.samin.minio.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("multi_file_upload")
public class MultiFileUpload implements Serializable {

    private Long id;
    /**
     * 分片上传的 uploadId
     */
    private String uploadId;
    /**
     * 文件唯一标识（md5）
     */
    private String fileIdentifier;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 所属桶名
     */
    private String bucketName;
    /**
     * 文件的 key
     */
    private String objectKey;
    /**
     * 文件大小（byte）
     */
    private Long totalSize;
    /**
     * 每个分片大小（byte）
     */
    private Long chunkSize;
    /**
     * 分片数量
     */
    private Integer chunkNum;
}
