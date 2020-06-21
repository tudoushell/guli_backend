package com.elliot.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.entity.EduSubject;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-06-21
 */
public interface EduSubjectService extends IService<EduSubject> {

  /**
   * 添加课程分类
   *
   * @param file excel 文件
   * @param eduSubjectService
   * @return
   */
  CommonResult addSubjectCategory(MultipartFile file, EduSubjectService eduSubjectService);
}
