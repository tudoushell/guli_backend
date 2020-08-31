package com.elliot.ucenter.service;

public interface WxService {

  /**
   * 微信登录
   *
   * @param code
   * @return token
   */
  String wxLogin(String code);


  /**
   * 获取微信登录二维码
   *
   * @return
   */
  String getWxQRCode();
}
