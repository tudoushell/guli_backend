package com.elliot.acl.controller;


import com.elliot.acl.entity.User;
import com.elliot.acl.service.UserService;
import com.elliot.common.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
@RestController
@RequestMapping("/api/acl/user")
@Api(tags = "用户接口")
public class UserController {

  @Resource
  private UserService userService;

  @ApiOperation("添加用户")
  @PostMapping("")
  public CommonResult saveUser(@RequestBody User user) {
    userService.saveUser(user);
    return CommonResult.success(null);
  }

  @ApiOperation("更新用户信息")
  @PutMapping("")
  public CommonResult updateUser(@RequestBody User user) {
    userService.updateUser(user);
    return CommonResult.success(null);
  }

  @ApiOperation("删除用户信息")
  @DeleteMapping("/{id}")
  public CommonResult deleteUserById(@PathVariable String id) {
    userService.deleteUserById(id);
    return CommonResult.success(null);
  }

  @ApiOperation("获取用户信息")
  @GetMapping("/{id}")
  public CommonResult<User> getUserById(@PathVariable String id) {
    return CommonResult.success(userService.getUserById(id));
  }
}
