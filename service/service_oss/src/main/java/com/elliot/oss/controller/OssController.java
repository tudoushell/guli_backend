package com.elliot.oss.controller;

import com.elliot.common.result.CommonResult;
import com.elliot.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/oss")
//@CrossOrigin
@Api(tags = "阿里云oss服务")
public class OssController {
  
  @Autowired
  private OssService ossService;

  @ApiOperation("文件上传")
  @PostMapping("/file/upload")
  public CommonResult uploadFile(@RequestBody MultipartFile multipartFile) {
    return ossService.uploadFile(multipartFile);
  }
}
