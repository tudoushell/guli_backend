package com.elliot.eduservice.controller;


import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.entity.EduVideo;
import com.elliot.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-07-02
 */
@RestController
@RequestMapping("/api/edu-service/edu-video")
@CrossOrigin
@Api(tags = "课程章节小节接口")
public class EduVideoController {

  @Resource
  private EduVideoService eduVideoService;

  @ApiOperation("添加章节小节")
  @PostMapping("")
  public CommonResult addEduVideo(@Validated @RequestBody EduVideo eduVideo) {
    eduVideoService.save(eduVideo);
    return CommonResult.success(null);
  }
}
