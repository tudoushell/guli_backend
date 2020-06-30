package com.elliot.eduservice.controller;

import com.elliot.common.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/edu-service/user")
@Api(tags = "用户管理")
@CrossOrigin
public class EduLoginController {

  @ApiOperation("用户登录")
  @PostMapping("/login")
  public CommonResult login() {
    Map<String, String> map = new HashMap<>();
    map.put("token", "123");
    return CommonResult.success(map);
  }

  @ApiOperation("获取用户信息")
  @GetMapping("/info")
  public CommonResult info() {
    Map<String, String> map = new HashMap<>();
    map.put("roles", "[admin]");
    map.put("name", "admin");
    map.put("avatar", "https://avatars0.githubusercontent.com/u/16298098?s=60&v=4");
    return CommonResult.success(map);
  }
}
