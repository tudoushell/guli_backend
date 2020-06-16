package com.elliot.eduservice.entity;


import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author elliot
 * @since 2020-05-24
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "EduTeacher对象", description = "讲师")
public class EduTeacher extends BaseEntity{

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "讲师姓名")
  private String name;

  @ApiModelProperty(value = "讲师简介")
  private String intro;

  @ApiModelProperty(value = "讲师资历,一句话说明讲师")
  private String career;

  @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
  private Integer level;

  @ApiModelProperty(value = "讲师头像")
  private String avatar;

  @ApiModelProperty(value = "排序")
  private Integer sort;

  @TableLogic
  @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
  private Boolean isDeleted;

}
