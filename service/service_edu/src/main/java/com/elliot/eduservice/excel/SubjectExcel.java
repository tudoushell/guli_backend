package com.elliot.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SubjectExcel {

  @ApiModelProperty("一级分类")
  @ExcelProperty("一级分类")
  private String oneCategory;

  @ApiModelProperty("二级分类")
  @ExcelProperty("二级分类")
  private String twoCategory;
}
