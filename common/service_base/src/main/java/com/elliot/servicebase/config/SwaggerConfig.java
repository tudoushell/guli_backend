package com.elliot.servicebase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  /**
   * oss 服务接口
   *
   * @return
   */
  @Bean
  public Docket ossApi() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("阿里OSS")
            .genericModelSubstitutes(DeferredResult.class)
            .useDefaultResponseMessages(false)
            .forCodeGeneration(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.elliot.oss"))
            .paths(PathSelectors.any()).build().apiInfo(oss());
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("讲师管理")
            .genericModelSubstitutes(DeferredResult.class)
            .useDefaultResponseMessages(false)
            .forCodeGeneration(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.elliot.eduservice"))
            .paths(PathSelectors.any()).build().apiInfo(teacher());
  }

  public ApiInfo teacher() {
    return new ApiInfoBuilder().title("在线教育").description("服务接口").build();
  }

  public ApiInfo oss() {
    return new ApiInfoBuilder().title("在线教育").description("oss文件接口").build();
  }

}
