package com.elliot.eduservice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryTeacher {
  @ApiModelProperty(value = "讲师姓名")
  private String name;

  @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
  private Integer level;
  
  @ApiModelProperty(value = "开始时间")
  private String startTime;

  @ApiModelProperty(value = "结束时间")
  private String endTime;


}
