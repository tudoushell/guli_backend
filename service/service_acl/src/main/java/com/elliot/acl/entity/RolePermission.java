package com.elliot.acl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("acl_role_permission")
@ApiModel(value = "RolePermission对象", description = "角色权限")
public class RolePermission extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private String roleId;

  private String permissionId;

  @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
  private Boolean isDeleted;


}
