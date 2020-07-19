package com.elliot.eduservice.mapper;

import com.elliot.eduservice.dto.CoursePublishDto;
import com.elliot.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author elliot
 * @since 2020-07-02
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
  /**
   * 获取最终发布的课程信息
   * @param id 课程ID
   * @return
   */
  CoursePublishDto getCoursePublishInfo(String id);
}
