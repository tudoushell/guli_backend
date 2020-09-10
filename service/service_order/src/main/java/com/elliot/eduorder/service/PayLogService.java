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
   * 获取微信支付二维码
   *
   * @param orderNo
   * @return
   */
  Map<String, Object> createNative(String orderNo);

}
