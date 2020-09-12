package com.elliot.eduorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elliot.eduorder.entity.PayLog;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-09-07
 */
public interface PayLogService extends IService<PayLog> {

  /**
   * 订单支付成功后保存支付日志
   *
   * @param orderNo
   * @return
   */
  Map<String, String> orderSuccessToSaveLog(String orderNo);


  /**
   * 获取微信交易状态
   *
   * @param orderNo
   * @return
   */
  Map<String, String> getOrderStatus(String orderNo);


  /**
   * 获取微信支付二维码
   *
   * @param orderNo
   * @return
   */
  Map<String, Object> createNative(String orderNo);

}
