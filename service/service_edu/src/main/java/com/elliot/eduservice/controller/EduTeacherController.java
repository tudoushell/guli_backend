package com.elliot.eduservice.controller;


import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.entity.EduTeacher;
import com.elliot.eduservice.entity.QueryTeacher;
import com.elliot.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-05-24
 */
@RestController
@RequestMapping("/api/edu-service/edu-teacher")
@Api(tags = "讲师管理接口", consumes = "application/json")
@CrossOrigin
public class EduTeacherController {

  @Autowired
  private EduTeacherService eduTeacherService;

  @GetMapping("/detail/{id}")
  public CommonResult<Map<String, Object>> getTeacherAndCourse(@PathVariable String id) {
    return CommonResult.success(eduTeacherService.getTeacherDetail(id));
  }

  @ApiOperation("获取所有的讲师")
  @GetMapping("")
  public CommonResult listAllTeacher() {
    List<EduTeacher> eduTeacherList = eduTeacherService.list();
    return CommonResult.success(eduTeacherList);
  }


  @ApiOperation("修改讲师")
  @PutMapping("")
  public CommonResult updateTeacher(@RequestBody EduTeacher eduTeacher) {
      if (StringUtils.isEmpty(eduTeacher.getId())) {
        return CommonResult.failed("更新失败");
      } else {
        eduTeacherService.updateById(eduTeacher);
        return CommonResult.success(null);
      }
  }


  @ApiOperation("获取讲师信息")
  @GetMapping("/{id}")
  public CommonResult getTeacher(@PathVariable("id") String id) {
    EduTeacher eduTeacher = eduTeacherService.getById(id);
    return CommonResult.success(eduTeacher);
  }


  @ApiOperation("删除讲师")
  @DeleteMapping("/{id}")
  public CommonResult deleteTeacher(@PathVariable("id") String id) {
    eduTeacherService.removeById(id);
    return CommonResult.success(null);
  }


  @ApiOperation("分页查询讲师")
  @PostMapping("/{page}/{row}")
  public CommonResult<Map<String, Object>> listTeacher(@PathVariable("page") Integer page, @PathVariable("row") Integer row, @RequestBody(required = false) QueryTeacher queryTeacher) {
    return eduTeacherService.listAndQueryTeacher(page, row, queryTeacher);
  }

  @ApiOperation("添加讲师")
  @PostMapping("")
  public CommonResult addTeacher(@RequestBody EduTeacher eduTeacher) {
    boolean result = eduTeacherService.save(eduTeacher);
    if (result) {
       return CommonResult.success(null);
    } else {
      return CommonResult.failed();
    }

  }
}
