package com.elliot.eduservice.controller;


import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.entity.EduTeacher;
import com.elliot.eduservice.entity.QueryTeacher;
import com.elliot.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/edu-teacher")
@Api(tags = "讲师管理接口", consumes = "application/json")
public class EduTeacherController {

  @Autowired
  private EduTeacherService eduTeacherService;


  @ApiOperation("分页查询讲师")
  @PostMapping("/{page}/{row}")
  public CommonResult<Map<String, Object>> listTeacher(@PathVariable("page") Integer page, @PathVariable("row") Integer row, @RequestBody QueryTeacher queryTeacher) {
    return eduTeacherService.listAndQueryTeacher(page, row, queryTeacher);
  }

  @ApiOperation("添加讲师")
  @PostMapping("/")
  public CommonResult addTeacher(@RequestBody EduTeacher eduTeacher) {
    boolean result = eduTeacherService.save(eduTeacher);
    if (result) {
       return CommonResult.success(null);
    } else {
      return CommonResult.failed();
    }

  }
}
