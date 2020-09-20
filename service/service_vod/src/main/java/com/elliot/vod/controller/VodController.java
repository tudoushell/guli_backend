package com.elliot.vod.controller;

import com.elliot.common.result.CommonResult;
import com.elliot.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author elliot
 */
@RestController
@RequestMapping("/api/vod")
//@CrossOrigin
@Api(tags = "阿里云vod服务")
public class VodController {

  @Resource
  private VodService vodService;

  @ApiOperation("获取视频凭证")
  @GetMapping("/auth/{videoId}")
  public CommonResult<String> getPlayAuth(@PathVariable String videoId) {
    return CommonResult.success(vodService.getPlayAuth(videoId));
  }

  @ApiOperation("批量删除阿里云视频")
  @DeleteMapping("/batch-video")
  public CommonResult deleteBatchVideo(@RequestParam("videoIdList") List<String> videoIdList) {
    vodService.deleteBatchVideo(videoIdList);
    return CommonResult.success(null);
  }

  @ApiOperation("从阿里云删除视频")
  @DeleteMapping("/{videoId}")
  public CommonResult deleteVideo(@PathVariable String videoId) {
    vodService.deleteAliVideo(videoId);
    return CommonResult.success(null);
  }

  @ApiOperation("上传视频")
  @PostMapping("")
  public CommonResult<String> uploadVideo(@RequestBody MultipartFile file) {
    return CommonResult.success(vodService.uploadVideo(file));
  }
}
