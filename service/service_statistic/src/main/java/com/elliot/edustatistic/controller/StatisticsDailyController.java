package com.elliot.edustatistic.controller;


import com.elliot.common.result.CommonResult;
import com.elliot.edustatistic.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-09-15
 */
@RestController
@RequestMapping("/api/edu-statistic/statistics-daily")
@Api(tags = "网站统计接口")
public class StatisticsDailyController {

  @Resource
  private StatisticsDailyService statisticsDailyService;

  @ApiOperation("获取某个时间的注册人数")
  @GetMapping("/register/count")
  public CommonResult<Long> getRegisterNum(@RequestParam String dateStr) {
    return CommonResult.success(statisticsDailyService.getRegisterNum(dateStr));
  }

}
