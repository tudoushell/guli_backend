package com.elliot.edustatistic.service;

import com.elliot.common.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-ucenter")
@Component
public interface UcenterService {

  @GetMapping("/api/ucenter/register/count")
  CommonResult<Long> getRegisterCountByDate(@RequestParam("dateStr") String dateStr);
}
