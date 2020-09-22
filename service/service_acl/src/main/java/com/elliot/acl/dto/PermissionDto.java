package com.elliot.acl.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author elliot
 * @since 2020-09-20
 */
@Getter
@Setter
public class PermissionDto {

  private String id;

  @ApiModelProperty(value = "所属上级")
  private String pid;

  @ApiModelProperty(value = "名称")
  private String name;

  @ApiModelProperty(value = "类型(1:菜单,2:按钮)")
  private Integer type;

  @ApiModelProperty(value = "权限值")
  private String permissionValue;

  @ApiModelProperty(value = "访问路径")
  private String path;

  @ApiModelProperty(value = "组件路径")
  private String component;

  @ApiModelProperty(value = "图标")
  private String icon;

  @ApiModelProperty(value = "状态(0:禁止,1:正常)")
  private Integer status;

  private List<PermissionDto> children;

}
