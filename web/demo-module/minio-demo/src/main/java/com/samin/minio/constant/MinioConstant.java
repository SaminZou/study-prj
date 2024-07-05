package com.samin.minio.constant;


public interface MinioConstant {

    /**
     * 分块大小
     */
    int DEFAULT_CHUNK_SIZE = 10 * 1024 * 1024;

    /**
     * 预签名url过期时间(ms)
     */
    Long PRE_SIGN_URL_EXPIRE = 60 * 10 * 1000L;
}
