package com.elliot.acl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("acl_user_role")
@ApiModel(value = "UserRole对象", description = "")
public class UserRole extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "角色id")
  private String roleId;

  @ApiModelProperty(value = "用户id")
  private String userId;

  @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
  private Boolean isDeleted;

}
