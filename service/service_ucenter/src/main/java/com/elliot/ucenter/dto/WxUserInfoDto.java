package com.elliot.ucenter.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WxUserInfoDto {

  private String openid;

  private String nickname;

  private Integer sex;

  private String language;

  private String city;

  private String province;

  private String country;

  private String headimgurl;

  private String unionid;
}
