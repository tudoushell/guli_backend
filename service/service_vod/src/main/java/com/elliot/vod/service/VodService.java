package com.elliot.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {

  /**
   * 获取阿里云视频播放凭证
   *
   * @return
   */
  String getPlayAuth(String videoId);

  /**
   * 批量删除阿里视频
   *
   * @param videoIdList
   */
  void deleteBatchVideo(List<String> videoIdList);

  /**
   * 从阿里中删除视频
   *
   * @param videoId
   */
  void deleteAliVideo(String videoId);
  
  /**
   * 上传视频到阿里云服务
   *
   * @param file
   * @return  视频ID
   */
  String uploadVideo(MultipartFile file);
}
