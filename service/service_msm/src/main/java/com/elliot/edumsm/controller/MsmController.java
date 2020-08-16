package com.elliot.edumsm.controller;

import com.elliot.common.result.CommonResult;
import com.elliot.edumsm.service.MsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/edu-msm")
@CrossOrigin
@Api(tags = "阿里云短信接口")
public class MsmController {

  @Resource
  private MsmService msmService;

  @ApiOperation("获取验证码")
  @GetMapping("/{phone}")
  public CommonResult getPhoneValidateCode(@PathVariable String phone) {
    if (msmService.getValidateCode(phone)) {
      return CommonResult.success(null);
    } else {
      return CommonResult.failed("验证码未过期，5分钟后重新发送");
    }
  }
}
