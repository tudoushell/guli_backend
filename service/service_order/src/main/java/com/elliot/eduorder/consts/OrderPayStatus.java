package com.elliot.eduorder.consts;

public enum OrderPayStatus {
  unpaid(0),
  paid(1);
  private Integer payStatus;

  OrderPayStatus(Integer payStatus) {
    this.payStatus = payStatus;
  }

  public Integer getPayStatus() {
    return payStatus;
  }
}
