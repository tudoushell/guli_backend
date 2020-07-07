package com.elliot.eduservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseDto {
  
  @ApiModelProperty(value = "课程标题")
  private String title;

  @ApiModelProperty(value = "总课时")
  private String lessonNum;

  @ApiModelProperty(value = "课程简介")
  private String description;
  
  @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
  private BigDecimal price;

  @ApiModelProperty(value = "课程讲师ID")
  private String teacherId;

  @ApiModelProperty(value = "课程专业ID")
  private String subjectId;

  @ApiModelProperty(value = "课程专业父级ID")
  private String subjectParentId;

  @ApiModelProperty(value = "课程封面图片路径")
  private String cover;

}
