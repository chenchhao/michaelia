package com.michaelia.emma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置Swagger2
 * @author fengyouwei
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * swagger2的配置文件
     *
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo())
        		.select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.michaelia.emma"))
                .paths(PathSelectors.any())
                .build()
                .groupName("subscriptions");
    }
 
    /**
     * api文档的详细信息函数
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("emma  RESTful API")
                .version("0.0.1")
                .description("API 描述")
                .contact(new Contact("chenchao","rm","254502989@qq.com"))
                .build();
    }
}
