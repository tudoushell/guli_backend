package com.elliot.ucenter.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
  @NotNull(message = "手机号不能为空")
  private String mobile;
  @NotNull(message = "密码不能为空")
  private String password;
}
