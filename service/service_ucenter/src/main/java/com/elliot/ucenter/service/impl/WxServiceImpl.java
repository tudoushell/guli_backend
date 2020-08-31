package com.elliot.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.utils.JwtUtil;
import com.elliot.ucenter.config.WxParams;
import com.elliot.ucenter.dto.WxUserInfoDto;
import com.elliot.ucenter.entity.Member;
import com.elliot.ucenter.mapper.MemberMapper;
import com.elliot.ucenter.service.WxService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class WxServiceImpl extends ServiceImpl<MemberMapper, Member> implements WxService {

  @Resource
  private RestTemplate restTemplate = new RestTemplate();

  @Override
  @Transactional(rollbackFor = Exception.class)
  public String wxLogin(String code) {
    Map<String, String> wxAccessToken = getWxAccessToken(code);
    WxUserInfoDto wxUserInfo = getWxUserInfo(wxAccessToken.get("access_token"), wxAccessToken.get("openid"));
    LambdaQueryWrapper<Member> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(Member::getOpenid, wxUserInfo.getOpenid());
    Member member = baseMapper.selectOne(lambdaQueryWrapper);
    //如为空，则是新用户
    if (Objects.isNull(member)) {
      Member newMember = new Member();
      newMember.setNickname(wxUserInfo.getNickname())
              .setAvatar(wxUserInfo.getHeadimgurl())
              .setOpenid(wxUserInfo.getOpenid())
              .setSex(wxUserInfo.getSex());
      this.save(newMember);
      log.info("新用户微信登录成功");
      return JwtUtil.getJwtToken(newMember.getId(), newMember.getNickname(), newMember.getAvatar());
    } else {
      log.info("微信登录成功");
      return JwtUtil.getJwtToken(member.getId(), member.getNickname(), member.getAvatar());
    }
  }


  @Override
  public String getWxQRCode() {
    String wxQR = "http://open.weixin.qq.com/connect/qrconnect?"
            + "appid=%s&"
            + "redirect_uri=%s&"
            + "response_type=code&"
            + "scope=snsapi_login&"
            + "state=hello#wechat_redirect";
    String wxQRUrl = "";
    try {
      wxQRUrl = String.format(wxQR,
              WxParams.APP_ID,
              URLEncoder.encode(WxParams.REDIRECT_URL, "UTF-8"));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return wxQRUrl;
  }

  /**
   * 2. 获取微信用户信息
   * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
   *
   * @param accessToken
   * @param openId
   */
  public WxUserInfoDto getWxUserInfo(String accessToken, String openId) {
    String userInfo = "https://api.weixin.qq.com/sns/userinfo?" +
            "access_token=" + accessToken +
            "&openid=" + openId + "";
    String userInfoJson = restTemplate.getForObject(userInfo, String.class);
    Gson gson = new Gson();
    return gson.fromJson(userInfoJson, WxUserInfoDto.class);
  }


  /**
   * 1. 通过code获取access_token
   * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
   * 36_q14VgjUDhZwQ-Y60G5OQlbJYKmN-P8Giv2enisHuUZQ-WkLBTT0m8kJwry1LSQ4pHXqfp3ZA-09qZOrh0e7UjkzWFWwqW7f2avZkOGXGo7Y
   *
   * @param code
   * @return
   */
  public Map<String, String> getWxAccessToken(String code) {
    String accessToken = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
            "appid=%s&" +
            "secret=%s&" +
            "code=%s&" +
            "grant_type=authorization_code";
    String accessTokenUrl = String.format(accessToken, WxParams.APP_ID, WxParams.APP_SECRET, code);
    String result = restTemplate.getForObject(accessTokenUrl, String.class);
    Gson gson = new Gson();
    Map<String, String> resultMap = gson.fromJson(result, HashMap.class);
    return resultMap;
  }
}
