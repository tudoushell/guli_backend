package com.elliot.eduservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CourseDetailDto {
  
  @ApiModelProperty(value = "课程ID")
  private String id;

  @ApiModelProperty(value = "课程标题")
  private String title;

  @ApiModelProperty(value = "课程封面图片路径")
  private String cover;

  @ApiModelProperty(value = "课程简介")
  private String description;

  @ApiModelProperty(value = "总课时")
  private Integer lessonNum;

  @ApiModelProperty(value = "课程一级分类")
  private String mainSubject;

  @ApiModelProperty(value = "课程二级分类")
  private String subSubject;

  @ApiModelProperty(value = "教师姓名")
  private String teacherName;

  @ApiModelProperty(value = "teacher介绍")
  private String teacherIntro;

  @ApiModelProperty(value = "teacher头像")
  private String teacherAvatar;

  @ApiModelProperty(value = "课程价格")
  private String price;

  @ApiModelProperty(value = "销售数量")
  private Long buyCount;

  @ApiModelProperty(value = "浏览数量")
  private Long viewCount;

  @ApiModelProperty(value = "章节和视频")
  private List<ChapterDto> chapterDtoList;
}
