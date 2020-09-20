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
//@CrossOrigin
@Api(tags = "课程章节小节接口")
public class EduVideoController {

  @Resource
  private EduVideoService eduVideoService;

  @ApiOperation("修改章节小节信息")
  @PutMapping("")
  public CommonResult updateEduVideo(@Validated @RequestBody EduVideo eduVideo) {
    eduVideoService.updateEduVideo(eduVideo);
    return CommonResult.success(null);
  }

  @ApiOperation("获取章节小节信息")
  @GetMapping("/{id}")
  public CommonResult<EduVideo> getEduVideo(@PathVariable String id) {
    EduVideo eduVideo = eduVideoService.getById(id);
    return CommonResult.success(eduVideo);
  }

  @ApiOperation("删除章节小节")
  @DeleteMapping("/{id}")
  public CommonResult deleteEduVideo(@PathVariable String id) {
    eduVideoService.deleteEduVideo(id);
    return CommonResult.success(null);
  }

  @ApiOperation("添加章节小节")
  @PostMapping("")
  public CommonResult addEduVideo(@Validated @RequestBody EduVideo eduVideo) {
    eduVideoService.addEduVideo(eduVideo);
    return CommonResult.success(null);
  }
}
