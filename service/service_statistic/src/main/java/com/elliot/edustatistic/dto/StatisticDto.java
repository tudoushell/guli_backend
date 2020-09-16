package com.elliot.edustatistic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticDto {

  private Type type;

  private String startTime;

  private String endTime;

  public enum Type {
    login_num,
    register_num,
    video_view_num,
    course_num;
  }
}
