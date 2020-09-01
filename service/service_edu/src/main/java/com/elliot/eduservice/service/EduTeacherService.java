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

  /**
   * 获取老师详情信息及课程信息
   *
   * @param id
   * @return
   */
  Map<String, Object> getTeacherDetail(String id);

  CommonResult<Map<String, Object>> listAndQueryTeacher(Integer page, Integer row, QueryTeacher queryTeacher);

}
