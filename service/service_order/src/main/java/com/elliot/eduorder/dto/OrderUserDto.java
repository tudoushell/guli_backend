package com.elliot.eduorder.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderUserDto {
  
  private String id;

  @ApiModelProperty(value = "昵称")
  private String nickname;

  @ApiModelProperty(value = "手机号")
  private String mobile;
}
