package com.elliot.eduorder.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-ucenter")
@Component
public interface UcenterService {

  /**
   * 获取用户信息
   *
   * @param id
   * @return
   */
  @GetMapping("/api/ucenter/user/{id}")
  String getUserById(@PathVariable("id") String id);

}
