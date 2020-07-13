package com.elliot.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elliot.eduservice.entity.EduVideo;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-07-02
 */
public interface EduVideoService extends IService<EduVideo> {

  /**
   * 更新章节小节信息
   *
   * @param eduVideo
   */
  void updateEduVideo(EduVideo eduVideo);

  /**
   * 添加小节
   *
   * @param eduVideo
   */
  void addEduVideo(EduVideo eduVideo);

  /**
   * 列出小节信息
   *
   * @param courseId 课程ID
   * @return
   */
  List<EduVideo> listEduVideoByCourseId(String courseId);
}
