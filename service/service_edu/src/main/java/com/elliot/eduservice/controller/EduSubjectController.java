package com.elliot.eduservice.controller;


import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.dto.MainSubjectDto;
import com.elliot.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-06-21
 */
@RestController
@RequestMapping("/api/edu-service/edu-subject")
@Api(tags = "课程分类接口", consumes = "application/json")
//@CrossOrigin
public class EduSubjectController {

  @Resource
  private EduSubjectService eduSubjectService;

  @ApiOperation("列出课程分类")
  @GetMapping("")
  public CommonResult<List<MainSubjectDto>> listSubjectCategory() {
    return eduSubjectService.listSubjectCategory();
  }

  @ApiOperation("上传课程分类")
  @PostMapping("/file")
  public CommonResult addSubjectCategory(MultipartFile file) {
    return eduSubjectService.addSubjectCategory(file, eduSubjectService);
  }
}
