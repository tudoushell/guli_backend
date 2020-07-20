package com.elliot.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.dto.CourseDto;
import com.elliot.eduservice.dto.CoursePublishDto;
import com.elliot.eduservice.dto.CourseQueryDto;
import com.elliot.eduservice.entity.EduCourse;

import java.util.Map;

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
   * 课程删除
   *
   * @param id 课程ID
   */
  void deleteCourse(String id);

  /**
   * 分布列出课程信息
   *
   * @param page 当前第几页
   * @param row 每页显示个数
   * @param courseQueryDto  查询条件
   * @return
   */
  Map<String, Object> listCourseByCondition(int page, int row, CourseQueryDto courseQueryDto);

  /**
   * 发布课程
   *
   * @param id
   */
  void publishCourse(String id);

  /**
   * 获取课程发布的信息
   *
   * @param id
   * @return
   */
  CoursePublishDto getPublishCourse(String id);

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
