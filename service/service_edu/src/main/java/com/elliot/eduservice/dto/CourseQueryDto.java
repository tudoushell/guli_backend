package com.elliot.eduservice.dto;

import lombok.Data;

@Data
public class CourseQueryDto {
  private String title;
  private String status;
  private String subjectParentId;
  private String subjectId;
  private boolean orderByPrice;
  private boolean orderByViewCount;
  private boolean orderByGmtCreate;
}
