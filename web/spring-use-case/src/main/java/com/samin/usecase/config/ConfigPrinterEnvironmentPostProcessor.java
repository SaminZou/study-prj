package com.samin.usecase.config;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;

@Order(Ordered.LOWEST_PRECEDENCE)
public class ConfigPrinterEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(ConfigPrinterEnvironmentPostProcessor.class);

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("====== 配置源信息开始打印 ======");

        List<PropertySource<?>> sources = environment.getPropertySources()
                                                     .stream()
                                                     .collect(Collectors.toList());

        for (PropertySource<?> source : sources) {
            System.out.println(">>> 来源: " + source.getName());

            if (source instanceof EnumerablePropertySource) {
                EnumerablePropertySource<?> eps = (EnumerablePropertySource<?>) source;
                for (String key : eps.getPropertyNames()) {
                    Object value = eps.getProperty(key);
                    System.out.println("    " + key + " = " + value);
                }
            }
        }

        System.out.println("====== 配置源信息打印结束 ======");
    }
}