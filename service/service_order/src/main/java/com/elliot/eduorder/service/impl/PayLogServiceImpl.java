package com.elliot.eduorder.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.exception.ApiException;
import com.elliot.eduorder.consts.OrderPayStatus;
import com.elliot.eduorder.consts.OrderPayType;
import com.elliot.eduorder.entity.Order;
import com.elliot.eduorder.entity.PayLog;
import com.elliot.eduorder.mapper.PayLogMapper;
import com.elliot.eduorder.service.OrderService;
import com.elliot.eduorder.service.PayLogService;
import com.elliot.eduorder.utils.DateUtil;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-09-07
 */
@Slf4j
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

  @Resource
  private OrderService orderService;

  @Resource
  private RestTemplate restTemplate;


  @Transactional(rollbackFor = Exception.class)
  @Override
  public Map<String, String> orderSuccessToSaveLog(String orderNo) {
    PayLog payLog = new PayLog();
    //订单
    Order orderByOrderNo = orderService.getOrderByOrderNo(orderNo);
    //获取订单的支付状态
    Map<String, String> orderStatusMap = getOrderStatus(orderNo);
    Map<String, String> resultMap = new HashMap<>();
    //查询日志
    LambdaQueryWrapper<PayLog> logLambdaQueryWrapper = new LambdaQueryWrapper<>();
    logLambdaQueryWrapper.eq(PayLog::getOrderNo, orderNo);
    PayLog oldPayLog = this.baseMapper.selectOne(logLambdaQueryWrapper);
    if (Objects.isNull(oldPayLog)) {
      if ("SUCCESS".equals(orderStatusMap.get("trade_state"))) {
        //生成交易日志
        String payFinishedTime = orderStatusMap.get("time_end");
        payLog.setOrderNo(orderNo);
        payLog.setPayTime(DateUtil.stringToDate("yyyyMMddHHmmss", payFinishedTime));
        payLog.setPayType(OrderPayType.wx.getPayType());
        payLog.setTradeState(orderStatusMap.get("trade_state"));
        payLog.setTotalFee(orderByOrderNo.getTotalFee());
        payLog.setTransactionId(orderStatusMap.get("transaction_id"));
        payLog.setAttr(JSON.toJSONString(orderStatusMap));
        this.save(payLog);
        //修改订单状态
        orderService.updateOrderStatusByOrderNo(orderNo, OrderPayStatus.paid);
        resultMap.put("isPaid", "true");
      } else {
        resultMap.put("isPaid", "false");
      }
    } else {
      resultMap.put("isPaid", "true");
    }
    return resultMap;
  }

  /**
   * 微信查询订单
   * https://api.mch.weixin.qq.com/pay/orderquery
   *
   * @param orderNo
   * @return
   */
  @Override
  public Map<String, String> getOrderStatus(String orderNo) {
    Map<String, String> wxParams = new HashMap<>();
    //公众账号ID
    wxParams.put("appid","wx74862e0dfcf69954");
    //商户号
    wxParams.put("mch_id", "1558950191");
    //随机字符串
    wxParams.put("nonce_str", WXPayUtil.generateNonceStr());
    //用户定义的流水号
    wxParams.put("out_trade_no", orderNo);
    try {
      String requestParams =  WXPayUtil.generateSignedXml(wxParams, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb");
      ResponseEntity<String> response = restTemplate.postForEntity("https://api.mch.weixin.qq.com/pay/orderquery",
              requestParams, String.class);
      Map<String, String> responseMap = WXPayUtil.xmlToMap(response.getBody());
      return responseMap;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException("订单异常");
    }
  }

  /**
   * 微信下单参考
   * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
   *
   * @param orderNo
   * @return
   */
  @Override
  public Map<String, Object> createNative(String orderNo) {
    log.info("获取订单信息");
    Order orderByOrderNo = orderService.getOrderByOrderNo(orderNo);
    log.info("生成支付二维码");
    Map<String, String> wxParams = new HashMap<>();
    //公众账号ID
    wxParams.put("appid","wx74862e0dfcf69954");
    //商户号
    wxParams.put("mch_id", "1558950191");
    //随机字符串
    wxParams.put("nonce_str", WXPayUtil.generateNonceStr());
    //课程标题
    wxParams.put("body", orderByOrderNo.getCourseTitle());
    //订单号
    wxParams.put("out_trade_no", orderNo);
    //订单总金额，单位为分，
    wxParams.put("total_fee", orderByOrderNo.getTotalFee().multiply(new BigDecimal(100)).longValue()+"");
    wxParams.put("spbill_create_ip", "127.0.0.1");
    wxParams.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify");
    //扫码支付
    wxParams.put("trade_type", "NATIVE");
    try {
      String requestParams = WXPayUtil.generateSignedXml(wxParams, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb");
      ResponseEntity<String> response = restTemplate.postForEntity("https://api.mch.weixin.qq.com/pay/unifiedorder",
              requestParams, String.class);
      Map<String, String> responseMap = WXPayUtil.xmlToMap(response.getBody());
      log.info("支付二维码返回");
      Map<String, Object> resultMap = new HashMap<>();
      resultMap.put("out_trade_no", orderNo);
      resultMap.put("total_fee", orderByOrderNo.getTotalFee());
      resultMap.put("result_code", responseMap.get("result_code"));
      //二维码链接
      resultMap.put("code_url", responseMap.get("code_url"));
      return resultMap;
    } catch (Exception e) {
     log.error(e.getMessage(), e);
     throw new ApiException("支付异常");
    }
  }
}
