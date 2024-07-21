package com.samin.minio.controller;


import com.samin.minio.model.dto.Result;
import com.samin.minio.model.dto.TaskInfoDTO;
import com.samin.minio.model.entity.MultiFileUpload;
import com.samin.minio.model.param.InitTaskParam;
import com.samin.minio.service.MultiFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


/**
 * 分片上传控制器
 * <p>
 * Description: 分片上传控制器
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-06-26
 */
@RestController
@RequestMapping("/v1/minio/tasks")
public class MinioUploadTaskController {

    /**
     * 服务对象
     */
    @Autowired
    private MultiFileUploadService multiFileUploadService;

    /**
     * 获取上传进度
     *
     * @param identifier 文件 md5
     * @return
     */
    @GetMapping("/{identifier}")
    public Result<TaskInfoDTO> taskInfo(@PathVariable("identifier") String identifier) {
        return Result.ok(multiFileUploadService.getTaskInfo(identifier));
    }

    /**
     * 创建一个上传任务
     *
     * @return
     */
    @PostMapping
    public Result<TaskInfoDTO> initTask(@Valid @RequestBody InitTaskParam param, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getFieldError()
                    .getDefaultMessage());
        }
        return Result.ok(multiFileUploadService.initTask(param));
    }

    /**
     * 获取每个分片的预签名上传地址
     *
     * @param identifier
     * @param partNumber
     * @return
     */
    @GetMapping("/{identifier}/{partNumber}")
    public Result preSignUploadUrl(@PathVariable("identifier") String identifier, @PathVariable("partNumber") Integer partNumber) {
        MultiFileUpload task = multiFileUploadService.getByIdentifier(identifier);
        if (task == null) {
            return Result.error("分片任务不存在");
        }
        Map<String, String> params = new HashMap<>();
        params.put("partNumber", partNumber.toString());
        params.put("uploadId", task.getUploadId());
        return Result.ok(multiFileUploadService.genPreSignUploadUrl(task.getBucketName(), task.getObjectKey(), params));
    }

    /**
     * 合并分片
     *
     * @param identifier
     * @return
     */
    @PostMapping("/merge/{identifier}")
    public Result merge(@PathVariable("identifier") String identifier) {
        multiFileUploadService.merge(identifier);
        return Result.ok();
    }
}
