package com.elliot.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elliot.eduservice.dto.CourseDetailDto;
import com.elliot.eduservice.dto.CoursePublishDto;
import com.elliot.eduservice.entity.EduCourse;

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
   * c端获取课程详情
   *
   * @param id
   * @return
   */
  CourseDetailDto getCourseInfo(String id);

  /**
   * 获取最终发布的课程信息
   * @param id 课程ID
   * @return
   */
  CoursePublishDto getCoursePublishInfo(String id);
}
