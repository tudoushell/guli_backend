package com.elliot.eduorder.service;

import com.elliot.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

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
   * 生成一个订单
   *
   * @param courseId 课程id
   * @param userId 用户id
   * @return
   */
  String addOrder(String courseId, String userId);
}
