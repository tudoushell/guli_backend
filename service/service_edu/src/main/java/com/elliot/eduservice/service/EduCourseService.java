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
   * 获取课程信息
   *
   * @param id
   * @return
   */
  CommonResult getCourse(String id);

  /**
   * 修改课程
   *
   * @param courseDto
   * @return
   */
  CommonResult updateCourse(CourseDto courseDto);

  /**
   * 添加课程
   *
   * @param courseDto
   * @return
   */
  CommonResult addCourse(CourseDto courseDto);
}
