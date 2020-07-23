package com.elliot.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * EnableFeignClients意思是当该工程在启动的时候，
 * 会进行包扫描，扫描该启动类包以下，子包中所有带
 * @FeignClient 注解的类（包括启动类所在包），并进行处理。
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.elliot"})
public class EduApplication {
  public static void main(String[] args) {
    SpringApplication.run(EduApplication.class, args);
  }
}
