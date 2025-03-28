package com.samin.minio.model.dto;

import cn.hutool.core.bean.BeanUtil;
import com.amazonaws.services.s3.model.PartSummary;
import com.samin.minio.model.entity.MultiFileUpload;
import java.util.List;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class RecordDTO extends MultiFileUpload {

    /**
     * 已上传完的分片
     */
    private List<PartSummary> exitPartList;

    public static RecordDTO convertFromEntity(MultiFileUpload task) {
        RecordDTO dto = new RecordDTO();
        BeanUtil.copyProperties(task, dto);
        return dto;
    }
}
