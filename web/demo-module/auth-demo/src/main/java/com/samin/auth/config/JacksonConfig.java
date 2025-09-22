package com.samin.auth.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        // 函数式接口，用lambda表达式实现
        return builder -> {
            // 1. 日期配置
            // Date类型格式
            builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                   // 时区
                   .timeZone(TimeZone.getTimeZone("GMT+8"))
                   .modules(new JavaTimeModule()
                                    // JDK8日期类型支持
                                    .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                    .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

            // 2. null值处理
            // 显示所有 null 值
            builder.serializationInclusion(JsonInclude.Include.ALWAYS)
                   // 格式化JSON（开发环境用，生产环境关闭）
                   .featuresToEnable(SerializationFeature.INDENT_OUTPUT)
                   // 忽略 Map 中的 null 值（可选）
                   .featuresToDisable(SerializationFeature.WRITE_NULL_MAP_VALUES);

            // 3. 字段命名策略
            builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);// 驼峰转下划线

            // 4. 自定义序列化器/反序列化器
            SimpleModule module = new SimpleModule();
            // 自定义序列化
            // module.addSerializer(Integer.class, new CustomSerializer())
            //       // 自定义反序列化
            //       .addDeserializer(Integer.class, new CustomDeserializer()).builder.modules(module);

            // 5. 其他配置：比如允许单引号、允许非标准 JSON 格式
            // 允许 JSON 里用单引号（比如'user_name':'张三'）
            builder.featuresToEnable(JsonParser.Feature.ALLOW_SINGLE_QUOTES,
                                     // 允许字段名不加引号（比如user_name:'张三'）
                                     JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
                                     // 空字符串转 null
                                     DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        };
    }
}