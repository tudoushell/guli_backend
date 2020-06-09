package com.elliot.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.entity.EduTeacher;
import com.elliot.eduservice.entity.QueryTeacher;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-05-24
 */
public interface EduTeacherService extends IService<EduTeacher> {
  public CommonResult<Map<String, Object>> listAndQueryTeacher(Integer page, Integer row, QueryTeacher queryTeacher);

}
