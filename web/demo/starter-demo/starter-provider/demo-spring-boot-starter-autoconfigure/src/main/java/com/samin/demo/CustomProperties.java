package com.samin.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {

    private Part1 part1 = new Part1();

    private Part2 part2 = new Part2();

    @Data
    public static class Part1 {

        /**
         * 参数 1 ( idea 环境，在 application.yaml 中输入此属性会有注释的提示 )
         * <p> spring-boot-configuration-processor 的作用
         * <p> 会在 META-INF 文件夹下生成一个 spring-configuration-metadata.json
         */
        private String customKey;
    }

    @Data
    public static class Part2 {

        private String key;
    }
}
