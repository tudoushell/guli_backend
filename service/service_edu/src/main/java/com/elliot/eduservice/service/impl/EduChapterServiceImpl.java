package com.elliot.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.exception.ApiException;
import com.elliot.eduservice.dto.ChapterDto;
import com.elliot.eduservice.dto.ChapterSectionDto;
import com.elliot.eduservice.entity.EduChapter;
import com.elliot.eduservice.entity.EduVideo;
import com.elliot.eduservice.mapper.EduChapterMapper;
import com.elliot.eduservice.service.EduChapterService;
import com.elliot.eduservice.service.EduVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-07-02
 */
@Service
@Slf4j
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

  @Resource
  private EduVideoService eduVideoService;

  @Override
  public List<ChapterDto> listChapter(String courseId) {
    //1.查询章节
    LambdaQueryWrapper<EduChapter> eduChapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
    LambdaQueryWrapper<EduChapter> lambdaQueryWrapper = eduChapterLambdaQueryWrapper
            .eq(EduChapter::getCourseId, courseId);
    List<EduChapter> eduChapterList = list(lambdaQueryWrapper);
    //2.查小节
    List<EduVideo> eduVideoList = eduVideoService.listEduVideoByCourseId(courseId);
    //3. 进行数据筛选
    List<ChapterDto> chapterDtoList = new ArrayList<>();
    eduChapterList.forEach(eduChapter -> {
      ChapterDto chapterDto = new ChapterDto();
      List<ChapterSectionDto> chapterSectionDtoList = new ArrayList<>();
      BeanUtils.copyProperties(eduChapter, chapterDto);
      //添加小节
      eduVideoList.stream()
              .filter(eduVideo -> eduVideo.getChapterId().equals(eduChapter.getId()))
              .forEach(eduVideo -> {
                ChapterSectionDto chapterSectionDto = new ChapterSectionDto();
                chapterSectionDto.setId(eduVideo.getId());
                chapterSectionDto.setTitle(eduVideo.getTitle());
                chapterSectionDtoList.add(chapterSectionDto);
              });
      chapterDto.setChildren(chapterSectionDtoList);
      chapterDtoList.add(chapterDto);
    });
    return chapterDtoList;
  }

  @Override
  public EduChapter getChapter(String chapterId) {
    EduChapter eduChapter = getById(chapterId);
    if (Objects.isNull(eduChapter)) {
      throw new ApiException("章节不存在");
    }
    return eduChapter;
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void deleteChapter(String chapterId) {
    log.info("查寻小节");
    LambdaQueryWrapper<EduVideo> eduVideoLambdaQueryWrapper = new LambdaQueryWrapper<>();
    eduVideoLambdaQueryWrapper.eq(EduVideo::getChapterId, chapterId);
    Integer eduVideoCount = eduVideoService.getBaseMapper().selectCount(eduVideoLambdaQueryWrapper);
    if (eduVideoCount > 0) {
      throw new ApiException("该章节下有小节，请删除小节，再删除章节");
    }
    removeById(chapterId);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public boolean updateChapter(EduChapter eduChapter) {
    log.info("更新章节");
    return updateById(eduChapter);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public boolean addChapter(EduChapter eduChapter) {
    return save(eduChapter);
  }
}
