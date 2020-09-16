package com.elliot.edustatistic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elliot.edustatistic.dto.StatisticDto;
import com.elliot.edustatistic.entity.StatisticsDaily;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-09-15
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

  /**
   * 统计某个时间段的数据
   *
   * @param statisticDto
   * @return
   */
  Map<String, Object> statisticData(StatisticDto statisticDto);

  /**
   * 获取某个时间的注册人数
   *
   * @param dateStr yyyy-MM-dd
   * @return
   */
  Long getRegisterNum(String dateStr);

  /**
   * 保存当天用户注册的人数和其他数据
   *
   * @param dateStr
   */
  void saveTodayRegisterUser(String dateStr);
}
