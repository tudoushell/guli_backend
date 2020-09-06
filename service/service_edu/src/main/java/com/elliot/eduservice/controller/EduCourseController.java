package com.elliot.eduservice.controller;


import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.dto.CourseDetailDto;
import com.elliot.eduservice.dto.CourseDto;
import com.elliot.eduservice.dto.CourseQueryDto;
import com.elliot.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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

  @ApiOperation("c端获取课程详情")
  @GetMapping("/detail/{id}")
  public CommonResult<CourseDetailDto> getCourseDetailInfo(@PathVariable String id) {
    return CommonResult.success(eduCourseService.getClientCourseInfo(id));
  }

  @ApiOperation("删除课程")
  @DeleteMapping("/{id}")
  public CommonResult deleteCourse(@PathVariable String id) {
    eduCourseService.deleteCourse(id);
    return CommonResult.success(null);
  }

  @ApiOperation("根据条件分页显示课程")
  @PostMapping("/{page}/{row}")
  public CommonResult<Map<String, Object>> listCourse(@PathVariable int page,
                                                      @PathVariable int row,
                                                      @RequestBody(required = false) CourseQueryDto courseQueryDto) {
    return CommonResult.success(eduCourseService.listCourseByCondition(page, row, courseQueryDto));
  }

  @ApiOperation("发布课程")
  @PutMapping("/publish/{id}")
  public CommonResult publishCourse(@PathVariable String id) {
    eduCourseService.publishCourse(id);
    return CommonResult.success(null);
  }

  @ApiOperation("获取课程发布信息")
  @GetMapping("/publish/{id}")
  public CommonResult getPublishCourseInfo(@PathVariable String id) {
    return CommonResult.success(eduCourseService.getPublishCourse(id));
  }

  @ApiOperation("获取课程信息")
  @GetMapping("/{courseId}")
  public CommonResult getCourse(@PathVariable String courseId) {
    return eduCourseService.getCourse(courseId);
  }

  @ApiOperation("更新课程信息")
  @PutMapping("")
  public CommonResult updateCourse(@RequestBody CourseDto courseDto) {
    return eduCourseService.updateCourse(courseDto);
  }
  
  @ApiOperation("保存课程信息")
  @PostMapping("")
  public CommonResult addCourse(@RequestBody CourseDto courseDto) {
    return eduCourseService.addCourse(courseDto);
  }

}
