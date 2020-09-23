package com.elliot.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author elliot
 * @since 2020-09-23
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {

  }

  /**
   * 配置拦截请求
   *
   * @param web
   * @throws Exception
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/swagger-ui.html/**");
  }
}
