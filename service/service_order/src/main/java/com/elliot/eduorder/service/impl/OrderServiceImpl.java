package com.elliot.eduorder.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.eduorder.dto.OrderUserDto;
import com.elliot.eduorder.entity.Order;
import com.elliot.eduorder.mapper.OrderMapper;
import com.elliot.eduorder.service.EduService;
import com.elliot.eduorder.service.OrderService;
import com.elliot.eduorder.service.UcenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-09-07
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

  @Resource
  private UcenterService ucenterService;

  @Resource
  private EduService eduService;

  @Override
  public String addOrder(String courseId, String userId) {
    String userById = ucenterService.getUserById(userId);
    OrderUserDto orderUserDto = JSON.parseObject(userById, OrderUserDto.class);
    return null;
  }
}
