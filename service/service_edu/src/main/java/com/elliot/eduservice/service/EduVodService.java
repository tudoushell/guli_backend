package com.elliot.eduservice.service;

import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.fallback.EduVodServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-vod", fallback = EduVodServiceImpl.class)
@Component
public interface EduVodService {

  /**
   * 批量删除阿里上的视频文件
   *
   * @param videoIdList
   * @return
   */
  @DeleteMapping("/api/vod/batch-video")
  CommonResult deleteBatchVideo(@RequestParam("videoIdList") List<String> videoIdList);

  /**
   * 服务调用
   * 删除阿里上的视频文件
   *
   * @param videoId
   * @return
   */
  @DeleteMapping("/api/vod/{videoId}")
  CommonResult deleteVideo(@PathVariable("videoId") String videoId);
}
