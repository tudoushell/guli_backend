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
   * PC端API
   *
   * @return
   */
  @Bean
  public Docket serviceACL() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("权限相关")
            .genericModelSubstitutes(DeferredResult.class)
            .useDefaultResponseMessages(false)
            .forCodeGeneration(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.elliot.acl"))
            .paths(PathSelectors.any()).build().apiInfo(acl());
  }

  /**
   * PC端API
   *
   * @return
   */
  @Bean
  public Docket serviceOrder() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("用户订单相关")
            .genericModelSubstitutes(DeferredResult.class)
            .useDefaultResponseMessages(false)
            .forCodeGeneration(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.elliot.eduorder"))
            .paths(PathSelectors.any()).build().apiInfo(order());
  }

  /**
   * PC端API
   *
   * @return
   */
  @Bean
  public Docket serviceUcenter() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("用户")
            .genericModelSubstitutes(DeferredResult.class)
            .useDefaultResponseMessages(false)
            .forCodeGeneration(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.elliot.ucenter"))
            .paths(PathSelectors.any()).build().apiInfo(uCenter());
  }


  /**
   * 阿里云短信
   *
   * @return
   */
  @Bean
  public Docket serviceMsm() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("阿里云短信")
            .genericModelSubstitutes(DeferredResult.class)
            .useDefaultResponseMessages(false)
            .forCodeGeneration(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.elliot.edumsm"))
            .paths(PathSelectors.any()).build().apiInfo(msm());
  }


  /**
   * PC端API
   *
   * @return
   */
  @Bean
  public Docket banner() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("cms相关")
            .genericModelSubstitutes(DeferredResult.class)
            .useDefaultResponseMessages(false)
            .forCodeGeneration(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.elliot.cmsservice"))
            .paths(PathSelectors.any()).build().apiInfo(cms());
  }


  /**
   * 阿里视频上传
   *
   * @return
   */
  @Bean
  public Docket vodApi() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("阿里VOD")
            .genericModelSubstitutes(DeferredResult.class)
            .useDefaultResponseMessages(false)
            .forCodeGeneration(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.elliot.vod"))
            .paths(PathSelectors.any()).build().apiInfo(vod());
  }

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

  public ApiInfo vod() {
    return new ApiInfoBuilder().title("在线教育").description("视频上传").build();
  }

  public ApiInfo cms() {
    return new ApiInfoBuilder().title("在线教育").description("PC端").build();
  }

  public ApiInfo msm() {
    return new ApiInfoBuilder().title("在线教育").description("阿里短信服务").build();
  }

  public ApiInfo uCenter() {
    return new ApiInfoBuilder().title("在线教育").description("用户中心").build();
  }

  public ApiInfo order() {
    return new ApiInfoBuilder().title("在线教育").description("用户订单").build();
  }

  public ApiInfo acl() {
    return new ApiInfoBuilder().title("在线教育").description("权限管理").build();
  }


}
