package com.elliot.acl.service;

import com.elliot.acl.dto.PermissionDto;
import com.elliot.acl.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
public interface PermissionService extends IService<Permission> {


  /**
   * 根据角色获取菜单
   *
   * @param roleId
   * @return
   */
  List<PermissionDto> listPermissionByRoleId(String roleId);

  /**
   * 获取权限
   *
   * @param id
   * @return
   */
  Permission getPermission(String id);

  /**
   * 更新权限
   *
   * @param permission
   */
  void updatePermission(Permission permission);

  /**
   * 删除权限
   *
   * @param id
   */
  void deletePermission(String id);

  /**
   * 添加权限
   *
   * @param permission
   */
  void addPermission(Permission permission);
}
