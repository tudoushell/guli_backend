package com.elliot.ucenter.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MemberDto {

  @NotNull(message = "昵称不能为空")
  @ApiModelProperty(value = "昵称")
  private String nickname;

  @NotNull(message = "手机号不能为空")
  @ApiModelProperty(value = "手机号")
  private String mobile;

  @NotNull(message = "密码不能为空")
  @ApiModelProperty(value = "密码")
  private String password;

  @NotNull(message = "验证码不能为空")
  @ApiModelProperty(value = "验证码")
  private String code;
}
