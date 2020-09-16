package com.elliot.edustatistic.controller;


import com.elliot.common.result.CommonResult;
import com.elliot.edustatistic.dto.StatisticDto;
import com.elliot.edustatistic.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-09-15
 */
@CrossOrigin
@RestController
@RequestMapping("/api/edu-statistic/statistics-daily")
@Api(tags = "网站统计接口")
public class StatisticsDailyController {

  @Resource
  private StatisticsDailyService statisticsDailyService;

  @ApiOperation("统计某个时段的数据")
  @PostMapping("")
  public CommonResult<Map<String, Object>> getDataStatistic(@RequestBody StatisticDto statisticDto) {
    return CommonResult.success(statisticsDailyService.statisticData(statisticDto));
  }

  @ApiOperation("生成某天的注册人数")
  @GetMapping("/save")
  public CommonResult saveRegisterNum(@RequestParam String dateStr) {
    statisticsDailyService.saveTodayRegisterUser(dateStr);
    return CommonResult.success(null);
  }

  @ApiOperation("获取某个时间的注册人数")
  @GetMapping("/register/count")
  public CommonResult<Long> getRegisterNum(@RequestParam String dateStr) {
    return CommonResult.success(statisticsDailyService.getRegisterNum(dateStr));
  }

}
