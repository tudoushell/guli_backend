package com.elliot.eduservice.fallback;

import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.service.EduVodService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务降级
 *
 */
@Component
public class EduVodServiceImpl implements EduVodService {
  @Override
  public CommonResult deleteBatchVideo(List<String> videoIdList) {
    return CommonResult.failed("批量删除阿里视频失败");
  }

  @Override
  public CommonResult deleteVideo(String videoId) {
    return CommonResult.failed("删除阿里视频失败");
  }
}
