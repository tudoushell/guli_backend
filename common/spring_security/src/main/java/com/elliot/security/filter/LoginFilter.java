package com.elliot.security.filter;

import com.elliot.common.result.CommonResult;
import com.elliot.common.utils.JwtUtil;
import com.elliot.security.entity.SecurityUser;
import com.elliot.security.entity.User;
import com.elliot.security.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author elliot
 * @since 2020-09-24
 */
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  public LoginFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
    this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/acl/user/login", "POST"));
  }

  @SneakyThrows
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
    return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
  }

  /**
   * 登录成功
   *
   * @param request
   * @param response
   * @param chain
   * @param authResult
   * @throws IOException
   * @throws ServletException
   */
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    SecurityUser securityUser = (SecurityUser)authResult.getPrincipal();
    String jwtToken = JwtUtil.getJwtToken(securityUser.getUser().getId(), securityUser.getUser().getNickName(), securityUser.getUser().getSalt());
    ResponseUtil.out(response, CommonResult.success(jwtToken));
  }

  /**
   * 登录失败
   *
   * @param request
   * @param response
   * @param failed
   * @throws IOException
   * @throws ServletException
   */
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    ResponseUtil.out(response, CommonResult.failed(failed.getMessage()));
  }
}
