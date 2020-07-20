package com.elliot.eduservice.service.impl;

import com.elliot.common.exception.ApiException;
import com.elliot.eduservice.entity.EduCourseDescription;
import com.elliot.eduservice.mapper.EduCourseDescriptionMapper;
import com.elliot.eduservice.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-07-02
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void deleteCourseDescription(String id) {
    int isDelete = baseMapper.deleteById(id);
    if (isDelete < 1) {
      throw new ApiException("删除有误");
    }
  }
}
