package com.elliot.ucenter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx.open")
public class WxParams {

  private String appId;

  private String appSecret;

  private String redirectUrl;

  public static String APP_ID;

  public static String APP_SECRET;

  public static String REDIRECT_URL;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
    APP_ID = appId;
  }

  public String getAppSecret() {
    return appSecret;
  }

  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret;
    APP_SECRET = appSecret;
  }

  public String getRedirectUrl() {
    return redirectUrl;
  }

  public void setRedirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
    REDIRECT_URL = redirectUrl;
  }

}
