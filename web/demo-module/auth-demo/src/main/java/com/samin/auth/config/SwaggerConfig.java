package com.samin.auth.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Swagger 配置类
 * <p>
 * Description: Swagger 配置类，默认文档访问地址：/doc.html
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2023-08-25
 */
@Configuration
@EnableKnife4j
@EnableSwagger2WebMvc
@ComponentScan({"springfox.documentation.schema"})
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                                                      .enable(true)
                                                      .select()
                                                      .apis(RequestHandlerSelectors.basePackage("com.samin.auth"))
                                                      .apis(RequestHandlerSelectors.withClassAnnotation(
                                                              RestController.class))
                                                      .paths(PathSelectors.any())
                                                      .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API测试文档")
                                   .description("接口文档")
                                   .contact(new Contact("Samin", "https://github.com/SaminZou", "822085977@qq.com"))
                                   .version("v1.0.0")
                                   .build();
    }
}