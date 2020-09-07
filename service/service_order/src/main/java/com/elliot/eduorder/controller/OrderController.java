package com.elliot.eduorder.controller;


import com.elliot.common.result.CommonResult;
import com.elliot.eduorder.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-09-07
 */
@RestController
@RequestMapping("/api/edu-order/order")
public class OrderController {

  @Resource
  private OrderService orderService;

  @GetMapping("/")
  public CommonResult<String> addOrder() {
    return CommonResult.success(orderService.addOrder("", "57c60796a8de2cd9ab5422ebdf182df7"));
  }

}
