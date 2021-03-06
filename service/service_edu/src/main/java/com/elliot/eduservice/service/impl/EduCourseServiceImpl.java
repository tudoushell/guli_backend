package com.elliot.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.exception.ApiException;
import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.consts.CourseStatus;
import com.elliot.eduservice.dto.*;
import com.elliot.eduservice.entity.EduCourse;
import com.elliot.eduservice.entity.EduCourseDescription;
import com.elliot.eduservice.mapper.EduCourseMapper;
import com.elliot.eduservice.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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

  @Resource
  private EduCourseMapper eduCourseMapper;

  @Resource
  private EduVideoService eduVideoService;

  @Resource
  private EduChapterService eduChapterService;

  @Resource
  private EduOrderService eduOrderService;

  @Override
  public CourseDetailDto getClientCourseInfo(String id, String userId) {
    List<ChapterDto> chapterDtoList = eduChapterService.listChapter(id);
    EduCourseDescription description = eduCourseDescriptionService.getById(id);
    CourseDetailDto courseInfo = baseMapper.getCourseInfo(id);
    courseInfo.setChapterDtoList(chapterDtoList);
    courseInfo.setDescription(description.getDescription());
    boolean isPaid = eduOrderService.courseIsPaidByMemberId(id, userId);
    courseInfo.setBuy(isPaid);
    return courseInfo;
  }

  @Override
  public List<CourseDto> listCourseByTeacherId(String teacherId) {
    List<CourseDto> courseDtoList = new ArrayList<>();
    LambdaQueryWrapper<EduCourse> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(EduCourse::getTeacherId, teacherId);
    List<EduCourse> eduCourseList = baseMapper.selectList(lambdaQueryWrapper);
    eduCourseList.forEach(eduCourse -> {
      CourseDto courseDto = new CourseDto();
      BeanUtils.copyProperties(eduCourse, courseDto);
      courseDtoList.add(courseDto);
    });
    return courseDtoList;
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void deleteCourse(String id) {
    //删除课程表、课程描述、课程小节
    log.info("删除课程描述");
    eduCourseDescriptionService.deleteCourseDescription(id);
    log.info("删除小节");
    eduVideoService.deleteEduVideoByCourseId(id);
    log.info("删除课程");
    int isDelete = baseMapper.deleteById(id);
    if (isDelete < 1) {
      throw new ApiException("操作失败!");
    }
  }

  @Override
  public Map<String, Object> listCourseByCondition(int page, int row, CourseQueryDto courseQueryDto) {
    Page<EduCourse> eduCoursePage = new Page<>(page, row);
    LambdaQueryWrapper<EduCourse> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    if (Objects.nonNull(courseQueryDto)) {
      if (StringUtils.isNotEmpty(courseQueryDto.getTitle())) {
        lambdaQueryWrapper.like(EduCourse::getTitle, courseQueryDto.getTitle());
      }
      if (StringUtils.isNotEmpty(courseQueryDto.getStatus())) {
        lambdaQueryWrapper.eq(EduCourse::getStatus, courseQueryDto.getStatus());
      }
      if (StringUtils.isNotEmpty(courseQueryDto.getSubjectParentId())) {
        lambdaQueryWrapper.eq(EduCourse::getSubjectParentId, courseQueryDto.getSubjectParentId());
      }
      if (StringUtils.isNotEmpty(courseQueryDto.getSubjectId())) {
        lambdaQueryWrapper.eq(EduCourse::getSubjectId, courseQueryDto.getSubjectId());
      }
      //最新课程排序
      if (courseQueryDto.isOrderByGmtCreate()) {
        lambdaQueryWrapper.orderByDesc(EduCourse::getGmtCreate);
      }
      //价格排序
      if (courseQueryDto.isOrderByPrice()) {
        lambdaQueryWrapper.orderByDesc(EduCourse::getPrice);
      }
      //关注度
      if (courseQueryDto.isOrderByViewCount()) {
        lambdaQueryWrapper.orderByDesc(EduCourse::getViewCount);
      }
    }
    lambdaQueryWrapper.orderByDesc(EduCourse::getGmtCreate);
    page(eduCoursePage, lambdaQueryWrapper);
    Map<String, Object> map = new HashMap<>();
    map.put("total", eduCoursePage.getTotal());
    map.put("items", eduCoursePage.getRecords());
    //c端需要的数据
    map.put("hasPrevious", eduCoursePage.hasPrevious());
    map.put("current", eduCoursePage.getCurrent());
    map.put("hasNext", eduCoursePage.hasNext());
    return map;
  }

  @Override
  public void publishCourse(String id) {
    EduCourse eduCourse = getById(id);
    if (Objects.isNull(eduCourse)) {
      throw new ApiException("课程不存在");
    }
    eduCourse.setStatus(CourseStatus.Normal.name());
    updateById(eduCourse);
  }

  @Override
  public CoursePublishDto getPublishCourse(String id) {
    return eduCourseMapper.getCoursePublishInfo(id);
  }

  @Override
  public CommonResult getCourse(String id) {
    CourseDto courseDto = new CourseDto();
    EduCourse eduCourse = baseMapper.selectById(id);
    EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
    if (Objects.isNull(eduCourse) && Objects.isNull(eduCourseDescription)) {
      return CommonResult.failed("课程信息不存在");
    }
    BeanUtils.copyProperties(eduCourse, courseDto);
    courseDto.setDescription(eduCourseDescription.getDescription());
    return CommonResult.success(courseDto);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public CommonResult updateCourse(CourseDto courseDto) {
    EduCourse eduCourse = baseMapper.selectById(courseDto.getId());
    EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(eduCourse.getId());
    if (Objects.isNull(eduCourse) || Objects.isNull(eduCourseDescription)) {
      throw new ApiException("课程信息有误");
    }
    log.info("更新课程");
    BeanUtils.copyProperties(courseDto, eduCourse);
    boolean eduCourseSave = updateById(eduCourse);
    log.info("更新课程描述");
    eduCourseDescription.setDescription(courseDto.getDescription());
    boolean courseDescriptionSave = eduCourseDescriptionService.updateById(eduCourseDescription);
    if (eduCourseSave && courseDescriptionSave) {
      return CommonResult.success(null);
    } else {
      return CommonResult.failed("更新失败!");
    }
  }

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
