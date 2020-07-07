package com.elliot.eduservice.service;

import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.dto.CourseDto;
import com.elliot.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-07-02
 */
public interface EduCourseService extends IService<EduCourse> {
  /**
   * 添加课程
   *
   * @param courseDto
   * @return
   */
  CommonResult addCourse(CourseDto courseDto);
}
