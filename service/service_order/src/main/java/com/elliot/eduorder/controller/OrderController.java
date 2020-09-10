package com.elliot.eduorder.controller;


import com.alibaba.fastjson.JSON;
import com.elliot.common.result.CommonResult;
import com.elliot.common.utils.JwtUtil;
import com.elliot.eduorder.dto.OrderUserDto;
import com.elliot.eduorder.entity.Order;
import com.elliot.eduorder.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
@CrossOrigin
public class OrderController {

  @Resource
  private OrderService orderService;

  @ApiOperation("获取订单详情")
  @GetMapping("/{orderNo}")
  public CommonResult<Order> getOrderByOrderNo(@PathVariable String orderNo) {
    return CommonResult.success(orderService.getOrderByOrderNo(orderNo));
  }
  
  @ApiOperation("生成课程订单")
  @PostMapping("/{courseId}")
  public CommonResult<String> addOrder(HttpServletRequest request, @PathVariable String courseId) {
    OrderUserDto userDto = JSON.parseObject(JwtUtil.getMemberIdByJwtToken(request), OrderUserDto.class);
    return CommonResult.success(orderService.addOrder(courseId, userDto.getId()));
  }

}
