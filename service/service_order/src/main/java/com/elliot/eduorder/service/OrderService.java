package com.elliot.eduorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elliot.eduorder.consts.OrderPayStatus;
import com.elliot.eduorder.entity.Order;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-09-07
 */
public interface OrderService extends IService<Order> {

  /**
   * 修改订单支付状态
   *
   * @param orderNo
   * @param orderPayStatus
   */
  void updateOrderStatusByOrderNo(String orderNo, OrderPayStatus orderPayStatus);

  /**
   * 根据订单编号获取信息
   *
   * @param orderNo
   * @return
   */
  Order getOrderByOrderNo(String orderNo);


  /**
   * 生成一个订单
   *
   * @param courseId 课程id
   * @param userId 用户id
   * @return
   */
  String addOrder(String courseId, String userId);
}
