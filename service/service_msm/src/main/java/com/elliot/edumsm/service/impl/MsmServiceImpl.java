package com.elliot.edumsm.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.elliot.common.exception.ApiException;
import com.elliot.edumsm.consts.AliParams;
import com.elliot.edumsm.service.MsmService;
import com.elliot.edumsm.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MsmServiceImpl implements MsmService {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;


  @Override
  public boolean getValidateCode(String phone) {
    String phoneCode = redisTemplate.opsForValue().get(phone);
    if (StringUtils.isEmpty(phoneCode)) {
      Map<String, String> params = new HashMap<>();
      log.info("产生随机的6位验证码");
      String code = RandomUtil.getSixBitRandom();
      params.put("code", code);
      DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
              AliParams.ACCESS_KEY_ID, AliParams.ACCESS_KEY_SECRET);
      IAcsClient client = new DefaultAcsClient(profile);
      CommonRequest request = new CommonRequest();
      request.setSysMethod(MethodType.POST);
      request.setSysDomain(AliParams.SYS_DOMAIN);
      request.setSysVersion(AliParams.SYS_VERSION);
      request.setSysAction(AliParams.SYS_ACTION);
      request.putQueryParameter("RegionId", "cn-hangzhou");
      request.putQueryParameter("PhoneNumbers", phone);
      request.putQueryParameter("SignName", AliParams.SIGN_NAME);
      request.putQueryParameter("TemplateCode", AliParams.TEMPLATE_CODE);
      request.putQueryParameter("TemplateParam", JSON.toJSONString(params));
      try {
        client.getCommonResponse(request);
        //存入redis，设置过期时间为5分钟
        redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
        return true;
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        throw new ApiException("服务有误，稍后重试");
      }
    }
    return false;
  }
}
