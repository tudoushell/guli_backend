package com.elliot.security.util;

import com.elliot.common.result.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author elliot
 * @since 2020-09-23
 */
public class ResponseUtil {
  
  private ResponseUtil() {
  }
  
  public static void out(HttpServletResponse response, CommonResult result) {
    ObjectMapper mapper = new ObjectMapper();
    response.setStatus(HttpStatus.OK.value());
    response.setCharacterEncoding("UTF-8");
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    try {
      mapper.writeValue(response.getWriter(), result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
