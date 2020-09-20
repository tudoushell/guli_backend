package com.elliot.edugateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.elliot"})
@SpringBootApplication
public class GateWayApplication {
  public static void main(String[] args) {
    SpringApplication.run(GateWayApplication.class, args);
  }
}
