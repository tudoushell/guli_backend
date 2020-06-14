package com.elliot.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.entity.EduTeacher;
import com.elliot.eduservice.entity.QueryTeacher;
import com.elliot.eduservice.mapper.EduTeacherMapper;
import com.elliot.eduservice.service.EduTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-05-24
 */
@Service
@Slf4j
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

  /**
   * 分页条件查询
   *
   * @param page
   * @param row
   * @param queryTeacher
   * @return
   */
  @Override
  public CommonResult<Map<String, Object>> listAndQueryTeacher(Integer page, Integer row, QueryTeacher queryTeacher) {
    log.info("开始查询");
    IPage<EduTeacher> teacherPage = new Page<>(page, row);
    LambdaQueryWrapper<EduTeacher> lambdaQuery = new LambdaQueryWrapper<>();
    lambdaQuery.like(StringUtils.isNotEmpty(queryTeacher.getName()),
            EduTeacher::getName,
            queryTeacher.getName());
    lambdaQuery.eq(Objects.nonNull(queryTeacher.getLevel()),
            EduTeacher::getLevel,
            queryTeacher.getLevel());
    lambdaQuery.ge(StringUtils.isNotEmpty(queryTeacher.getStartTime()), EduTeacher::getGmtCreate, queryTeacher.getStartTime());
    lambdaQuery.le(StringUtils.isNotEmpty(queryTeacher.getEndTime()), EduTeacher::getGmtCreate, queryTeacher.getEndTime());
    lambdaQuery.orderByDesc(EduTeacher::getGmtCreate);
    //开始分页查询
    page(teacherPage, lambdaQuery);
    Map<String, Object> map = new HashMap<>();
    map.put("total", teacherPage.getTotal());
    map.put("items", teacherPage.getRecords());
    return CommonResult.success(map);
  }
}
