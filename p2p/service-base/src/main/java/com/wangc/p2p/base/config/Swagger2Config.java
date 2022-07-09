package com.wangc.p2p.base.config;

import com.google.common.base.Predicates;
import org.apache.http.client.UserTokenHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author After拂晓
 * @date 2022年05月16日 10:25
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket adminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }
    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder().title("P2P后台管理系统")
                .description("P2P后台管理系统各个模块接口")
                .version("2.1.0")
                .contact(new Contact("After拂晓", "https://space.bilibili.com/456719983","1810732817@qq.com"))
                .build();
    }
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }
    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder().title("P2P前台管理系统")
                .description("P2P前台用户系统各个模块接口")
                .version("2.1.0")
                .contact(new Contact("After拂晓", "https://space.bilibili.com/456719983","1810732817@qq.com"))
                .build();
    }
}
