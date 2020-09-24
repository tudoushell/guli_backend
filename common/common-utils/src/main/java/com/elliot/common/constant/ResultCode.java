package com.elliot.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCode implements IErrorCode{
  SUCCESS(200, "操作成功"),
  FAILED(500,"操作出错"),
  UNAUTHORIZED(401, "请登录"),
  PASSWORDERROR(400, "帐号或密码错误");

  private long code;

  private String message;

}
