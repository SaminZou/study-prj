DROP TABLE IF EXISTS `multi_file_upload`;
CREATE TABLE `multi_file_upload`
(
    `id`              bigint                                                        NOT NULL,
    `upload_id`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分片上传的uploadId',
    `file_identifier` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件唯一标识（md5）',
    `file_name`       varchar(500) COLLATE utf8mb4_general_ci                       NOT NULL COMMENT '文件名',
    `bucket_name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属桶名',
    `object_key`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件的key',
    `total_size`      bigint                                                        NOT NULL COMMENT '文件大小（byte）',
    `chunk_size`      bigint                                                        NOT NULL COMMENT '每个分片大小（byte）',
    `chunk_num`       int                                                           NOT NULL COMMENT '分片数量',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uq_file_identifier` (`file_identifier`) USING BTREE,
    UNIQUE KEY `uq_upload_id` (`upload_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='分片上传表';