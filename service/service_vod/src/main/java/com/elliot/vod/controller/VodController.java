package com.elliot.vod.controller;

import com.elliot.common.result.CommonResult;
import com.elliot.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 *
 * @author elliot
 */
@RestController
@RequestMapping("/api/vod")
@CrossOrigin
@Api(tags = "阿里云vod服务")
public class VodController {

  @Resource
  private VodService vodService;

  @ApiOperation("上传视频")
  @PostMapping("")
  public CommonResult<String> uploadVideo(@RequestBody MultipartFile file) {
    return CommonResult.success(vodService.uploadVideo(file));
  }
}
