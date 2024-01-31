package com.samin.auth.service;

import com.samin.auth.entity.SystemLog;
import com.samin.auth.repo.SystemLogRepository;
import com.samin.auth.vo.req.PageReq;
import com.samin.auth.vo.resp.LogResp;
import com.samin.auth.vo.resp.PageResp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * 日志服务类
 * <p>
 * Description: 日志服务类
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-09-17
 */
@Service
@RequiredArgsConstructor
public class LogService {

    private final SystemLogRepository systemLogRepository;

    /**
     * 分页查询
     *
     * @param req 请求入参
     * @return 分页数据
     */
    public PageResp<LogResp> page(PageReq req) {
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), Sort.by("createTime")
                .descending());

        PageResp<SystemLog> logs = PageResp.success(systemLogRepository.findAll(pageable));

        PageResp<LogResp> resp = PageResp.baseOf(logs);
        resp.setContent(logs.getContent()
                .stream()
                .map(LogResp::getInstance)
                .collect(Collectors.toList()));

        return resp;
    }
}