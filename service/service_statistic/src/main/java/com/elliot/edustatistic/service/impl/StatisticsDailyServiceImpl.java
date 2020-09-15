package com.elliot.edustatistic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.elliot.common.result.CommonResult;
import com.elliot.edustatistic.entity.StatisticsDaily;
import com.elliot.edustatistic.mapper.StatisticsDailyMapper;
import com.elliot.edustatistic.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.edustatistic.service.UcenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Random;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-09-15
 */
@Slf4j
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

  @Resource
  private UcenterService ucenterService;

  @Override
  public Long getRegisterNum(String dateStr) {
    LambdaQueryWrapper<StatisticsDaily> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(StatisticsDaily::getDateCalculated, dateStr);
    Integer registerNum = baseMapper.selectCount(lambdaQueryWrapper);
    return Long.valueOf(String.valueOf(registerNum));
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void saveTodayRegisterUser(String dateStr) {
    Random random = new Random();
    log.info("删除已存在数据");
    LambdaQueryWrapper<StatisticsDaily> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(StatisticsDaily::getDateCalculated, dateStr);
    baseMapper.delete(lambdaQueryWrapper);
    log.info("远程调用ucenter模块");
    CommonResult<Long> registerCountByDate = ucenterService.getRegisterCountByDate(dateStr);
    Long registerCount = registerCountByDate.getData();
    StatisticsDaily statisticsDaily = new StatisticsDaily();
    statisticsDaily.setDateCalculated(dateStr);
    statisticsDaily.setRegisterNum(registerCount);
    statisticsDaily.setLoginNum(random.nextInt(100));
    statisticsDaily.setCourseNum(random.nextInt(200));
    baseMapper.insert(statisticsDaily);
  }
}
