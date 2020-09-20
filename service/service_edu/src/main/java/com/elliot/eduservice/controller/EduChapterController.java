package com.elliot.eduservice.controller;


import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.dto.ChapterDto;
import com.elliot.eduservice.entity.EduChapter;
import com.elliot.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-07-02
 */
@RestController
@RequestMapping("/api/edu-service/edu-chapter")
//@CrossOrigin
@Api(tags = "课程章节接口")
public class EduChapterController {

  @Resource
  private EduChapterService eduChapterService;

  @ApiOperation("删除章节")
  @DeleteMapping("/{chapterId}")
  public CommonResult deleteChapter(@PathVariable String chapterId) {
    eduChapterService.deleteChapter(chapterId);
    return CommonResult.success(null);
  }

  @ApiOperation("列出章节和小节信息")
  @GetMapping("/{courseId}")
  public CommonResult<List<ChapterDto>> listChapter(@PathVariable String courseId) {
    return CommonResult.success(eduChapterService.listChapter(courseId));
  }


  @ApiOperation("获取章节信息")
  @GetMapping("/chapter/{chapterId}")
  public CommonResult<EduChapter> getChapter(@PathVariable String chapterId) {
    EduChapter eduChapter = eduChapterService.getChapter(chapterId);
    return CommonResult.success(eduChapter);
  }
  

  @ApiOperation("更新章节")
  @PutMapping("")
  public CommonResult updateChapter(@Validated @RequestBody EduChapter eduChapter) {
    boolean isUpdate = eduChapterService.updateChapter(eduChapter);
    if (isUpdate) {
      return CommonResult.success(null);
    } else {
      return CommonResult.failed();
    }
  }

  @ApiOperation("添加章节")
  @PostMapping("")
  public CommonResult addChapter(@Validated @RequestBody EduChapter eduChapter) {
    boolean isSave = eduChapterService.addChapter(eduChapter);
    if (isSave) {
     return CommonResult.success(null);
    } else {
     return CommonResult.failed();
    }
  }

}
