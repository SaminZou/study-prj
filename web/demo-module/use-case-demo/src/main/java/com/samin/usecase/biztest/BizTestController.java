package com.samin.usecase.biztest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 业务测试
 * <p>
 * Description: 业务测试
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-03-11
 */
@RestController
@RequiredArgsConstructor
public class BizTestController {

    private final TableConfigRepository tableConfigRepository;
    private final ObjectMapper objectMapper;

    /**
     * BIZ 根据请求区分内外网返回配置数据
     *
     * <p>测试数据：{"WebSiteA":{"name":"WebSiteA","address":"http://websitea.cn"},"WebSiteB":{"name":"WebSiteB","address":"http://websiteb.cn"}}</p>
     *
     * @param request
     * @return
     */
    @GetMapping("/biz/config_data")
    public BizResp configData(HttpServletRequest request) throws IOException {
        BizResp resp = new BizResp();

        String uri = request.getRemoteAddr();
        if (uri.equals("127.0.0.1")) {
            // 内网
            Optional<TableConfig> dataOpt = tableConfigRepository.findFirstByCode("internalConfigData");
            if (dataOpt.isPresent()) {
                resp.setData(objectMapper.readValue(dataOpt.get()
                        .getValue(), HashMap.class));
            }
        } else {
            // 外网
        }

        return resp;
    }

    @Data
    public static class BizResp {
        Map data;
    }
}
