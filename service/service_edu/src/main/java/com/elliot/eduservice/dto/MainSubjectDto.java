package com.elliot.eduservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainSubjectDto {
  private String id;
  private String title;
  private List<ChildrenSubjectDto> children;
}
