package com.elliot.eduservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChapterDto {

  private String id;

  private String title;

  private List<ChapterSectionDto> children;

}
