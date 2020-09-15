package com.elliot.edustatistic.schedule;

import com.elliot.edustatistic.service.StatisticsDailyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

@Slf4j
@Component
public class StatisticUserCount {

  @Resource
  private StatisticsDailyService statisticsDailyService;

  /**
   * 每日0点执行一次
   */
  @Scheduled(cron = "0 0 0 * * ? *")
  public void registerUserToSave() {
    log.info("开始执行定时任务------> 统计用户");
    LocalDate now = LocalDate.now();
    statisticsDailyService.saveTodayRegisterUser(now.minusDays(1).toString());
  }
}
