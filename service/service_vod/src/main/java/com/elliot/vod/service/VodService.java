package com.elliot.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
  /**
   * 上传视频到阿里云服务
   *
   * @param file
   * @return  视频ID
   */
  String uploadVideo(MultipartFile file);
}
