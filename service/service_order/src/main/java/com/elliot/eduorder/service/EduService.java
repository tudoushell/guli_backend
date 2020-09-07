package com.elliot.eduorder.service;

import com.elliot.common.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-edu")
@Component
public interface EduService {

  /**
   * 根据课程id获取信息
   *
   * @param id  课程id
   * @return
   */
  @GetMapping("/api/edu-service/edu-course/publish/{id}")
  CommonResult getPublishCourse(@PathVariable("id") String id);
}
