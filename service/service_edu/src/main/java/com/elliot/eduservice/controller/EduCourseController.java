package com.elliot.eduservice.controller;


import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.dto.CourseDto;
import com.elliot.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-07-02
 */
@RestController
@RequestMapping("/api/edu-service/edu-course")
@CrossOrigin
@Api(tags = "课程管理接口")
public class EduCourseController {

  @Resource
  private EduCourseService eduCourseService;

  @ApiOperation("保存课程信息")
  @PostMapping("")
  public CommonResult addCourse(@RequestBody CourseDto courseDto) {
    return eduCourseService.addCourse(courseDto);
  }

}