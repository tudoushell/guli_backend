package com.elliot.edumsm.service;

public interface MsmService {
  /**
   * 获取验证码
   *
   * @param phone
   */
  boolean getValidateCode(String phone);
}
