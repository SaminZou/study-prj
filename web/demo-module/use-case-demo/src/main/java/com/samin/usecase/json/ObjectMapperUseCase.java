package com.samin.usecase.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json")
public class ObjectMapperUseCase {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/generics")
    public BaseResp<JsonData> generics() throws JsonProcessingException {
        String json = "{\"code\":0, \"msg\":\"success\",\"data\":{\"name\":\"test\"}}";
        // 这种写法会报 Unchecked assignment
        // BaseResp<JsonData> resp = objectMapper.readValue(json, BaseResp.class);
        BaseResp<JsonData> resp = objectMapper.readValue(json, new TypeReference<BaseResp<JsonData>>() {});
        return resp;
    }

    @Data
    public static class JsonData {

        private String name;
    }
}
