package com.elliot.eduservice.service;

import com.elliot.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-07-02
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {
  /**
   * 删除课程描述
   *
   * @param id 课程ID
   */
  void deleteCourseDescription(String id);

}
