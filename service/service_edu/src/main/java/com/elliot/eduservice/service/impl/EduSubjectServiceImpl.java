package com.elliot.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.exception.ApiException;
import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.entity.EduSubject;
import com.elliot.eduservice.excel.SubjectExcel;
import com.elliot.eduservice.excel.SubjectListener;
import com.elliot.eduservice.mapper.EduSubjectMapper;
import com.elliot.eduservice.service.EduSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-06-21
 */
@Service
@Slf4j
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

  @Transactional(rollbackFor = Exception.class)
  @Override
  public CommonResult addSubjectCategory(MultipartFile file, EduSubjectService eduSubjectService) {
    String fileName = file.getOriginalFilename();
    if (fileName.endsWith(".xlsx")) {
      try {
        EasyExcel.read(file.getInputStream(), SubjectExcel.class, new SubjectListener(eduSubjectService))
                .sheet().doRead();
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        throw new ApiException("文件出错");
      }
    } else {
      return CommonResult.failed("文件格式不对，请上传正确的格式");
    }
    return CommonResult.success(null);
  }
}
