package com.elliot.eduservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-order")
@Component
public interface EduOrderService {

  /**
   * 远程调用获取当前用户是否已购买课程
   *
   * @param courseId
   * @param userId
   * @return
   */
  @GetMapping("/api/edu-order/order/course/{courseId}")
  boolean courseIsPaidByMemberId(@PathVariable("courseId") String courseId, @RequestParam("userId") String userId);
}
