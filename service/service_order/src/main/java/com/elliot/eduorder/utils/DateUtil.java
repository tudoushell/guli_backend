package com.elliot.eduorder.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
  private DateUtil() {

  }

  /**
   * 字符串时间转data
   *
   * @param pattern 时间格式
   * @param timeStr
   * @return
   */
  public static Date stringToDate(String pattern, String timeStr) {
    DateTimeFormatter dft = DateTimeFormatter.ofPattern(pattern);
    return Date.from(LocalDateTime.parse(timeStr, dft).atZone(ZoneId.systemDefault()).toInstant());
  }
}
