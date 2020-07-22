package com.elliot.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.elliot.common.exception.ApiException;
import com.elliot.vod.service.VodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class VodServiceImpl implements VodService {

  @Value("${aliyun.vod.regionId}")
  private String regionId;

  @Value("${aliyun.vod.accessKeyId}")
  private String accessKeyId;

  @Value("${aliyun.vod.accessKeySecret}")
  private String accessKeySecret;

  @Override
  public void deleteAliVideo(String videoId) {
    DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
    DeleteVideoResponse response = new DeleteVideoResponse();
    DeleteVideoRequest request = new DeleteVideoRequest();
    request.setVideoIds(videoId);
    try {
      response = client.getAcsResponse(request);
    } catch (Exception e) {
      e.printStackTrace();
      log.error(e.getMessage(), e);
      throw new ApiException("删除视频失败！");
    }
    log.info("删除成功" + response.getRequestId());
  }

  @Override
  public String uploadVideo(MultipartFile file) {
    String fileName = file.getOriginalFilename();
    log.info("文件名为" + fileName);
    String title = fileName.substring(0, fileName.lastIndexOf('.'));
    InputStream inputStream = null;
    try {
      UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title,
              fileName, file.getInputStream());
      UploadVideoImpl uploader = new UploadVideoImpl();
      UploadStreamResponse response = uploader.uploadStream(request);
      return response.getVideoId();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ApiException("视频上传异常，请稍后重试");
    } finally {
      try {
        if (inputStream != null) {
          inputStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        throw new ApiException("视频文件异常");
      }
    }
  }

  private DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
    DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
    DefaultAcsClient client = new DefaultAcsClient(profile);
    return client;
  }
}
