package com.elliot.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.exception.ApiException;
import com.elliot.common.result.CommonResult;
import com.elliot.eduservice.dto.ChildrenSubjectDto;
import com.elliot.eduservice.dto.MainSubjectDto;
import com.elliot.eduservice.entity.EduSubject;
import com.elliot.eduservice.excel.SubjectExcel;
import com.elliot.eduservice.excel.SubjectListener;
import com.elliot.eduservice.mapper.EduSubjectMapper;
import com.elliot.eduservice.service.EduSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

  @Transactional(readOnly = true)
  @Override
  public CommonResult<List<MainSubjectDto>> listSubjectCategory() {
    log.info("列出课程类别");
    List<EduSubject> eduSubjectList = list();
    List<MainSubjectDto> mainSubjectDtoList = new ArrayList<>();
    if (!eduSubjectList.isEmpty()) {
      List<EduSubject> parentSubjectList = eduSubjectList.
              stream()
              .filter(eduSubject -> "0".equals(eduSubject.getParentId()))
              .collect(Collectors.toList());
      //遍历父课程类别
      parentSubjectList.forEach(parentSubject -> {
        List<ChildrenSubjectDto> childrenSubjectDtoList = new ArrayList<>();
        MainSubjectDto mainSubjectDto = new MainSubjectDto();
        mainSubjectDto.setId(parentSubject.getId());
        mainSubjectDto.setTitle(parentSubject.getTitle());
        //遍历子课程类别
        eduSubjectList.forEach(eduSubject -> {
            //比较子课程的parentId
            if (eduSubject.getParentId().equals(parentSubject.getId())) {
              ChildrenSubjectDto childrenSubjectDto = new ChildrenSubjectDto();
              BeanUtils.copyProperties(eduSubject, childrenSubjectDto);
              childrenSubjectDtoList.add(childrenSubjectDto);
            }
        });
        mainSubjectDto.setChildren(childrenSubjectDtoList);
        mainSubjectDtoList.add(mainSubjectDto);
      });
      return CommonResult.success(mainSubjectDtoList);
    } else {
      return CommonResult.success(null);
    }
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public CommonResult addSubjectCategory(MultipartFile file, EduSubjectService eduSubjectService) {
    String fileName = file.getOriginalFilename();
    if (file == null) {
      return CommonResult.failed("文件为空");
    }
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
