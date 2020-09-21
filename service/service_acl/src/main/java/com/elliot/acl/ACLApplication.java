package com.elliot.acl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ACLApplication {
  public static void main(String[] args) {
    SpringApplication.run(ACLApplication.class, args);
  }
}
