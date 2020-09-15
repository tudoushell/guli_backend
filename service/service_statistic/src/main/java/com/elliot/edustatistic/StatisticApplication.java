package com.elliot.edustatistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.elliot"})
public class StatisticApplication {
  public static void main(String[] args) {
    SpringApplication.run(StatisticApplication.class, args);
  }
}
