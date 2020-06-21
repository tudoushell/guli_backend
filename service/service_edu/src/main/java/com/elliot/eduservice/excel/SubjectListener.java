package com.elliot.eduservice.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.elliot.common.exception.ApiException;
import com.elliot.eduservice.entity.EduSubject;
import com.elliot.eduservice.service.EduSubjectService;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author elliot
 */
@Slf4j
public class SubjectListener extends AnalysisEventListener<SubjectExcel> {

  private EduSubjectService eduSubjectService;

  public SubjectListener() {
  }

  public SubjectListener(EduSubjectService eduSubjectService) {
    this.eduSubjectService = eduSubjectService;
  }

  @Override
  public void invoke(SubjectExcel subjectExcel, AnalysisContext analysisContext) {
    if (Objects.isNull(subjectExcel)) {
      log.error("excel为空");
      throw new ApiException("文件内容不能为空");
    }
    EduSubject oneCategory = getOneCategory(subjectExcel.getOneCategory());
    if (Objects.isNull(oneCategory)) {
      log.info("添加一级分类");
      oneCategory = EduSubject.builder()
              .title(subjectExcel.getOneCategory())
              .parentId("0")
              .build();
      eduSubjectService.save(oneCategory);
    }

    EduSubject twoCategory = getTwoCategory(subjectExcel.getTwoCategory(), oneCategory.getId());
    if (Objects.isNull(twoCategory)) {
      log.info("添加二级分类");
      twoCategory = EduSubject.builder()
              .title(subjectExcel.getTwoCategory())
              .parentId(oneCategory.getId())
              .build();
      eduSubjectService.save(twoCategory);
    }
  }

  /**
   * 获取二级分类
   *
   * @param title
   * @param parentId
   * @return
   */
  public EduSubject getTwoCategory(String title, String parentId) {
    LambdaQueryWrapper<EduSubject> eduSubjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
    eduSubjectLambdaQueryWrapper.eq(EduSubject::getTitle, title);
    eduSubjectLambdaQueryWrapper.eq(EduSubject::getParentId, parentId);
    return eduSubjectService.getOne(eduSubjectLambdaQueryWrapper);
  }

  /**
   * 获取一级分类
   *
   * @param parentTitle 父类标题
   */
  public EduSubject getOneCategory(String parentTitle) {
    LambdaQueryWrapper<EduSubject> eduSubjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
    eduSubjectLambdaQueryWrapper.eq(EduSubject::getTitle, parentTitle);
    eduSubjectLambdaQueryWrapper.eq(EduSubject::getParentId, "0");
    return eduSubjectService.getOne(eduSubjectLambdaQueryWrapper);
  }




  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {

  }
}
