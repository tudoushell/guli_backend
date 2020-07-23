package com.elliot.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.exception.ApiException;
import com.elliot.eduservice.entity.EduVideo;
import com.elliot.eduservice.mapper.EduVideoMapper;
import com.elliot.eduservice.service.EduVideoService;
import com.elliot.eduservice.service.EduVodService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

  @Resource
  private EduVodService eduVodService;

  @Override
  public void deleteEduVideo(String id) {
    //这需用到分布式事务
    //再删除小节时，同时要删除阿里服务器上的视频
    EduVideo eduVideo = getById(id);
    log.info("删除阿里视频");
    if (StringUtils.isNotEmpty(eduVideo.getVideoSourceId())) {
      eduVodService.deleteVideo(eduVideo.getVideoSourceId());
    }
    log.info("删除小节");
    baseMapper.deleteById(id);
  }

  @Override
  public void updateEduVideo(EduVideo eduVideo) {
    boolean isUpdate = updateById(eduVideo);
    if (!isUpdate) {
      throw new ApiException("更新失败");
    }
  }

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
