package com.elliot.eduservice.dto;

import lombok.Data;

@Data
public class ChapterSectionDto {
  private String id;
  private String title;
  private Boolean isFree;
  private String videoSourceId;
}
