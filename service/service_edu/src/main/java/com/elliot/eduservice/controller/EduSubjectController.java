package com.elliot.eduservice.controller;


import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-06-21
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
@Api(tags = "课程管理接口", consumes = "application/json")
public class EduSubjectController {

  @Resource
  private EduSubjectService eduSubjectService;

  @ApiOperation("")
  @PostMapping("/file")
  public CommonResult addSubjectCategory(MultipartFile file) {
    return eduSubjectService.addSubjectCategory(file, eduSubjectService);
  }
}
