package com.elliot.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

  @ApiModelProperty(value = "用户ID")
  private String id;
  
  @ApiModelProperty(value = "昵称")
  private String nickname;

  @ApiModelProperty(value = "头像")
  private String avatar;
}
