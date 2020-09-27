package com.elliot.security.filter;

import com.elliot.common.dto.User;
import com.elliot.common.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author: zhou
 * @date: 2020/9/25
 */
@Slf4j
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

  public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  /**
   * token 校验
   *
   * @param request
   * @param response
   * @param chain
   * @throws IOException
   * @throws ServletException
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    logger.info("URL(in) ========> " + request.getRequestURI());
    final String AUTHENTICATION_TOKEN = request.getHeader("authentication-token");
    if (StringUtils.isEmpty(AUTHENTICATION_TOKEN)) {
      SecurityContextHolder.clearContext();
    } else {
      UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(AUTHENTICATION_TOKEN);
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    chain.doFilter(request, response);
  }

  /**
   * 获取token中用户数据
   *
   * @param authorization
   * @return
   */
  private UsernamePasswordAuthenticationToken getAuthentication(String authorization) {
    ObjectMapper objectMapper = new ObjectMapper();
    logger.info("validate token");
    try {
      String userJson = JwtUtil.getUserByToken(authorization);
      User user = objectMapper.readValue(userJson, User.class);
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getId(), user, new ArrayList<>());
      return usernamePasswordAuthenticationToken;
    } catch (Exception e) {
      logger.error("Request to parse JWT have error: " + e.getMessage(), e);
      return null;
    }
  }
}
