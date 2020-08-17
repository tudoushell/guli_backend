package com.elliot.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.elliot")
@MapperScan("com.elliot.ucenter.mapper")
public class UcenterApplication {
  public static void main(String[] args) {
    SpringApplication.run(UcenterApplication.class, args);
  }
}
