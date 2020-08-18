package com.elliot.ucenter.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
  @TableId(type = IdType.ASSIGN_UUID)
  private String id;

  @TableField(value = "gmt_create", fill = FieldFill.INSERT)
  @ApiModelProperty(value = "创建时间")
  private Date gmtCreate;

  @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
  @ApiModelProperty(value = "更新时间")
  private Date gmtModified;
}
