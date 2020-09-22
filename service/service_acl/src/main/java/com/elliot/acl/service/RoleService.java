package com.elliot.acl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elliot.acl.dto.RoleQueryDto;
import com.elliot.acl.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
public interface RoleService extends IService<Role> {

  /**
   * 根据条件分页查询角色
   *
   * @param page
   * @param row
   * @param roleQueryDto
   * @return
   */
  Page<Role> listRoleByPagination(int page, int row, RoleQueryDto roleQueryDto);

  /**
   * 查询角色
   *
   * @param id
   * @return
   */
  Role getRoleById(String id);

  /**
   * 更新角色
   *
   * @param role
   */
  void updateRoleById(Role role);

  /**
   * 删除角色
   *
   * @param id
   */
  void deleteRoleById(String id);

  /**
   * 添加角色
   *
   * @param role
   */
  void addRole(Role role);

}
