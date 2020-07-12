package com.elliot.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.exception.ApiException;
import com.elliot.eduservice.entity.EduVideo;
import com.elliot.eduservice.mapper.EduVideoMapper;
import com.elliot.eduservice.service.EduVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-07-02
 */
@Service
@Slf4j
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

  @Override
  public void addEduVideo(EduVideo eduVideo) {
    boolean isSave = save(eduVideo);
    if (!isSave) {
      throw new ApiException("小节保存失败");
    }
  }

  @Override
  public List<EduVideo> listEduVideoByCourseId(String courseId) {
    LambdaQueryWrapper<EduVideo> eduVideoLambdaQueryWrapper = new LambdaQueryWrapper<>();
    LambdaQueryWrapper<EduVideo> lambdaQueryWrapper = eduVideoLambdaQueryWrapper
            .eq(EduVideo::getCourseId, courseId);
    List<EduVideo> eduVideoList = list(lambdaQueryWrapper);
    return eduVideoList;
  }
}
