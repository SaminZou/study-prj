package com.samin.minio.model.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class ObjectInfo {

    /**
     * 所在桶
     */
    private String bucket;

    /**
     * 对象的key
     */
    private String objectKey;

    /**
     * 文件地址
     */
    private String path;
}
