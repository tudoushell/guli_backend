package com.elliot.eduorder.consts;

public enum OrderPayType {
  /**
   * wx: 微信支付
   * zfb: 支付宝
   */
  wx(1),
  zfb(2);

  private Integer payType;

  OrderPayType(Integer payType) {
    this.payType = payType;
  }

  public Integer getPayType() {
    return payType;
  }
}
