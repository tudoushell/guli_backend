package com.elliot.common.exception;

import com.elliot.common.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


  @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public CommonResult dataValidated(MethodArgumentNotValidException ex) {
    ex.printStackTrace();
    log.error(ex.getMessage(), ex.getMessage());
    return CommonResult.failed(ex.getBindingResult().getFieldError().getDefaultMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = Exception.class)
  public CommonResult exception(Exception e) {
    e.printStackTrace();
    log.error(e.getMessage(), e);
    return CommonResult.failed("server error wait please");
  }

  @ResponseBody
  @ExceptionHandler(value = ApiException.class)
  public CommonResult handler(ApiException e) {
    if (e.getErrorCode() != null) {
      return CommonResult.failed(e.getErrorCode());
    }
    return CommonResult.failed(e.getMessage());
  }
}
