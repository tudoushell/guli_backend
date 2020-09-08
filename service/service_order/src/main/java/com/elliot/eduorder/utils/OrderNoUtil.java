package com.elliot.eduorder.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class OrderNoUtil {

  private static  Random random = new Random();

  private OrderNoUtil() {
  }

  /**
   * 生成订单编号
   *
   * @return
   */
  public static String getOrderNo() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    String dateTimeStr = dtf.format(LocalDateTime.now());
    for (int i = 0; i < 3; i++) {
      dateTimeStr += random.nextInt(10);
    }
    return dateTimeStr;
  }
}
