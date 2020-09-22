package com.elliot.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elliot.acl.dto.RoleQueryDto;
import com.elliot.acl.entity.Role;
import com.elliot.acl.mapper.RoleMapper;
import com.elliot.acl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.exception.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

  @Override
  public Page<Role> listRoleByPagination(int page, int row, RoleQueryDto roleQueryDto) {
    Page<Role> pages = new Page<>(page, row);
    LambdaQueryWrapper<Role> lambdaQueryWrapper = null;
    if (roleQueryDto != null) {
      if (StringUtils.isNotEmpty(roleQueryDto.getRoleName())) {
        lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Role::getRoleName, roleQueryDto.getRoleName());
      }
    }
    return baseMapper.selectPage(pages, lambdaQueryWrapper);
  }

  @Override
  public Role getRoleById(String id) {
    return baseMapper.selectById(id);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void updateRoleById(Role role) {
    Role oldRole = getRoleById(role.getId());
    if (!oldRole.getRoleName().equals(role.getRoleName())) {
      validateRoleName(role.getRoleName());
    }
    baseMapper.updateById(role);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void deleteRoleById(String id) {
    baseMapper.deleteById(id);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void addRole(Role role) {
    validateRoleName(role.getRoleName());
    baseMapper.insert(role);
  }

  /**
   * 校验角色名是否存在
   *
   * @param roleName
   */
  private void validateRoleName(String roleName) {
    LambdaQueryWrapper<Role> lambdaQuery = new LambdaQueryWrapper();
    lambdaQuery.eq(Role::getRoleName, roleName);
    Integer count = baseMapper.selectCount(lambdaQuery);
    if (count > 0) {
      throw new ApiException("role name is exists");
    }
  }
}
