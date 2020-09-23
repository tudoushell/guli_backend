package com.elliot.security.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author elliot
 * @since 2020-09-23
 */
@Getter
@Setter
@ApiModel(description = "用户实体类")
public class User {

  private String username;

  private String password;

  private String nickName;

  private String salt;

  private String token;
}
