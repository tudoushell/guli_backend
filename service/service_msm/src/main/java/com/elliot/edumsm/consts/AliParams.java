package com.elliot.edumsm.consts;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aliyun")
public class AliParams {

  private String accessKeyId;

  private String accessKeySecret;

  private String sysDomain;

  private String sysVersion;

  private String sysAction;

  private String signName;

  private String templateCode;

  public static String ACCESS_KEY_ID;

  public static String ACCESS_KEY_SECRET;

  public static String SYS_DOMAIN;

  public static String SYS_VERSION;

  public static String SYS_ACTION;

  public static String SIGN_NAME;

  public static String TEMPLATE_CODE;

  public String getAccessKeyId() {
    return accessKeyId;
  }

  public void setAccessKeyId(String accessKeyId) {
    this.accessKeyId = accessKeyId;
    ACCESS_KEY_ID = accessKeyId;
  }

  public String getAccessKeySecret() {
    return accessKeySecret;
  }

  public void setAccessKeySecret(String accessKeySecret) {
    this.accessKeySecret = accessKeySecret;
    ACCESS_KEY_SECRET = accessKeySecret;
  }

  public String getSysDomain() {
    return sysDomain;
  }

  public void setSysDomain(String sysDomain) {
    this.sysDomain = sysDomain;
    SYS_DOMAIN = sysDomain;
  }

  public String getSysVersion() {
    return sysVersion;
  }

  public void setSysVersion(String sysVersion) {
    this.sysVersion = sysVersion;
    SYS_VERSION = sysVersion;
  }

  public String getSysAction() {
    return sysAction;
  }

  public void setSysAction(String sysAction) {
    this.sysAction = sysAction;
    SYS_ACTION = sysAction;
  }

  public String getSignName() {
    return signName;
  }

  public void setSignName(String signName) {
    this.signName = signName;
    SIGN_NAME = signName;
  }

  public String getTemplateCode() {
    return templateCode;
  }

  public void setTemplateCode(String templateCode) {
    this.templateCode = templateCode;
    TEMPLATE_CODE = templateCode;
  }
}
