package com.elliot.eduorder.service;

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
  @GetMapping("/api/edu-service/edu-course/course/{id}")
  String getPublishCourse(@PathVariable("id") String id);
}
