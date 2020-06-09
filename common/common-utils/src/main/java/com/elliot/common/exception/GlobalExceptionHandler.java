package com.elliot.common.exception;

import com.elliot.common.result.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

//  @ResponseBody
//  @ExceptionHandler(value = Exception.class)
//  public CommonResult exception(Exception e) {
//    return CommonResult.failed(e.getMessage());
//  }

  @ResponseBody
  @ExceptionHandler(value = ApiException.class)
  public CommonResult handler(ApiException e) {
    if (e.getErrorCode() != null) {
      return CommonResult.failed(e.getErrorCode());
    }
    return CommonResult.failed(e.getMessage());
  }
}
