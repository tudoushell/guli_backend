package com.elliot.eduorder.controller;


import com.elliot.common.result.CommonResult;
import com.elliot.eduorder.service.PayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-09-07
 */
@RestController
@RequestMapping("/api/edu-order/pay-log")
@Api(tags = "订单支付接口")
@CrossOrigin
public class PayLogController {

  @Resource
  private PayLogService payLogService;

  @ApiOperation("获取订单支付状态")
  @GetMapping("/status/{orderNo}")
  public CommonResult<Map<String, String>> getOrderStatus(@PathVariable String orderNo) {
    return CommonResult.success(payLogService.orderSuccessToSaveLog(orderNo));
  }

  @ApiOperation("获取微信支付二维码")
  @GetMapping("/wx-native/{orderNo}")
  public CommonResult<Map<String, Object>> getPayNative(@PathVariable String orderNo) {
    return CommonResult.success(payLogService.createNative(orderNo));
  }

}
