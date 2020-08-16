package com.elliot.edumsm;

import com.elliot.edumsm.consts.AliParams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties(value = {AliParams.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("com.elliot")
public class MsmApplication {
  public static void main(String[] args) {
    SpringApplication.run(MsmApplication.class, args);
  }
}
