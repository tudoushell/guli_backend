package com.elliot.acl.service;

import com.elliot.acl.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
public interface RolePermissionService extends IService<RolePermission> {

  /**
   * 根据角色获取权限
   *
   * @param roleId
   * @return
   */
  List<RolePermission> listPermissionByRole(String roleId);

  /**
   * 为角色添加权限
   *
   * @param roleId
   * @param permissionIdList
   */
  void addPermissionForRole(String roleId, List<String> permissionIdList);

}
