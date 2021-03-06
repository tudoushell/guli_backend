package com.elliot.eduservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoursePublishDto {

  @ApiModelProperty(value = "课程ID")
  private String id;

  @ApiModelProperty(value = "课程标题")
  private String title;

  @ApiModelProperty(value = "课程封面图片路径")
  private String cover;

  @ApiModelProperty(value = "总课时")
  private Integer lessonNum;

  @ApiModelProperty(value = "课程一级分类")
  private String mainSubject;

  @ApiModelProperty(value = "课程二级分类")
  private String subSubject;

  @ApiModelProperty(value = "教师姓名")
  private String teacherName;

  @ApiModelProperty(value = "课程价格")
  private BigDecimal price;
}
