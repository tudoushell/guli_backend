package com.elliot.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elliot.eduservice.dto.ChapterDto;
import com.elliot.eduservice.entity.EduChapter;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-07-02
 */
public interface EduChapterService extends IService<EduChapter> {

  /**
   * 展示课程章节和小节信息
   *
   * @param courseId 课程ID
   * @return
   */
  List<ChapterDto> listChapter(String courseId);

  /**
   * 获取章节
   *
   * @param chapterId 章节ID
   * @return
   */
  EduChapter getChapter(String chapterId);

  /**
   * 删除章节
   *
   * @param chapterId 章节ID
   * @return
   */
  void deleteChapter(String chapterId);

  /**
   * 修改章节
   *
   * @param eduChapter
   * @return
   */
  boolean updateChapter(EduChapter eduChapter);

  /**
   * 添加章节
   *
   * @param eduChapter
   * @return
   */
  boolean addChapter(EduChapter eduChapter);

}
