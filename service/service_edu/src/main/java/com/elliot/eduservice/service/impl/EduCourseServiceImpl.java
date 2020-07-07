package com.elliot.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.dto.CourseDto;
import com.elliot.eduservice.entity.EduCourse;
import com.elliot.eduservice.entity.EduCourseDescription;
import com.elliot.eduservice.mapper.EduCourseMapper;
import com.elliot.eduservice.service.EduCourseDescriptionService;
import com.elliot.eduservice.service.EduCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-07-02
 */
@Service
@Slf4j
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

  @Resource
  private EduCourseDescriptionService eduCourseDescriptionService;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public CommonResult addCourse(CourseDto courseDto) {
    log.info("开始保存课程信息");
    EduCourse eduCourse = new EduCourse();
    BeanUtils.copyProperties(courseDto, eduCourse);
    save(eduCourse);
    log.info("保存课程描述");
    EduCourseDescription eduCourseDescription = new EduCourseDescription();
    eduCourseDescription.setId(eduCourse.getId());
    eduCourseDescription.setDescription(courseDto.getDescription());
    eduCourseDescriptionService.save(eduCourseDescription);
    return CommonResult.success(eduCourse.getId());
  }
}
