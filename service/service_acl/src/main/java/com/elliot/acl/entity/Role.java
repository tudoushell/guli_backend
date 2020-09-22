package com.elliot.acl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("acl_role")
@ApiModel(value = "Role对象", description = "")
public class Role extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @NotNull(message = "角色名不能为空")
  @ApiModelProperty(value = "角色名称")
  private String roleName;

  @ApiModelProperty(value = "角色编码")
  private String roleCode;

  @ApiModelProperty(value = "备注")
  private String remark;

  @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
  private Boolean isDeleted;

}
