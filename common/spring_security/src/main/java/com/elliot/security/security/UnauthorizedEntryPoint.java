package com.elliot.security.security;

import com.elliot.common.result.CommonResult;
import com.elliot.security.util.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.elliot.common.constant.ResultCode.UNAUTHORIZED;

/**
 * 未授权访问
 *
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    ResponseUtil.out(response, CommonResult.failed(UNAUTHORIZED));
  }
}
