package com.elliot.ucenter.controller;


import com.elliot.common.result.CommonResult;
import com.elliot.ucenter.dto.LoginDto;
import com.elliot.ucenter.dto.MemberDto;
import com.elliot.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/edu/u-center")
@Api(tags = "用户接口")
public class MemberController {

  @Resource
  private MemberService memberService;

  @ApiOperation("用户注册")
  @PostMapping("/sign-up")
  public CommonResult register(@Validated @RequestBody MemberDto memberDto) {
    memberService.register(memberDto);
    return CommonResult.success(null);
  }

  @ApiOperation("用户登录")
  @PostMapping("/login")
  public CommonResult login(@Validated @RequestBody LoginDto loginDto) {
    return CommonResult.success(memberService.login(loginDto));
  }

}
