package com.elliot.security.config;

import com.elliot.common.exception.ApiException;
import com.elliot.security.filter.LoginFilter;
import com.elliot.security.security.PasswordEncodeMatch;
import com.elliot.security.security.UnauthorizedEntryPoint;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

/**
 * @author elliot
 * @since 2020-09-23
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  private UserDetailsService userDetailsService;

  @Resource
  private PasswordEncodeMatch passwordEncodeMatch;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.exceptionHandling()
            .authenticationEntryPoint(new UnauthorizedEntryPoint())
            .and().csrf().disable().authorizeRequests().anyRequest().authenticated()
            .and().addFilter(new LoginFilter(authenticationManager())).httpBasic();
  }

  /**
   * 密码校验配置
   *
   * @param auth
   * @throws Exception
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncodeMatch);
  }

  /**
   * 配置拦截请求
   *
   * @param web
   * @throws Exception
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers( "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/doc.html/**");
  }
}
