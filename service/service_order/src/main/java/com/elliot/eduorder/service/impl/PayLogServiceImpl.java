package com.elliot.eduorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.exception.ApiException;
import com.elliot.eduorder.entity.Order;
import com.elliot.eduorder.entity.PayLog;
import com.elliot.eduorder.mapper.PayLogMapper;
import com.elliot.eduorder.service.OrderService;
import com.elliot.eduorder.service.PayLogService;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
      ResponseEntity<String> response = restTemplate.postForEntity("https://api.mch.weixin.qq.com/pay/unifiedorder", requestParams, String.class);
      Map<String, String> responseMap = WXPayUtil.xmlToMap(response.getBody());
      return null;
    } catch (Exception e) {
     log.error(e.getMessage(), e);
     throw new ApiException("支付异常");
    }
  }
}
