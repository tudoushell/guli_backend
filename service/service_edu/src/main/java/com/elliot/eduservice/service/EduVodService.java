package com.elliot.eduservice.service;

import com.elliot.common.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-vod")
@Component
public interface EduVodService {
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
