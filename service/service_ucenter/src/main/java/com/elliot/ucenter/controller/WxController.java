package com.elliot.ucenter.controller;

import com.elliot.ucenter.service.WxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Slf4j
@Controller
@CrossOrigin
@RequestMapping("/api/ucenter/wx")
@Api(tags = "微信接口")
public class WxController {

  @Resource
  private WxService wxService;

  @ApiOperation("获取微信登录二维码")
  @GetMapping("/qr")
  public String getWxQRCode() {
    String wxQRCode = wxService.getWxQRCode();
    return "redirect:" + wxQRCode;
  }

  @ApiOperation("获取微信登录后的code")
  @GetMapping("/callback")
  public String getWxCode(String code, String state) {
    String token = wxService.wxLogin(code);
    return "redirect:http://localhost:3000?token=" + token;
  }
}
