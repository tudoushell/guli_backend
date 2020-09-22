package com.elliot.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.acl.entity.RolePermission;
import com.elliot.acl.mapper.RolePermissionMapper;
import com.elliot.acl.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

  @Override
  public List<RolePermission> listPermissionByRole(String roleId) {
    LambdaQueryWrapper<RolePermission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(RolePermission::getRoleId, roleId);
    List<RolePermission> rolePermissionList = baseMapper.selectList(lambdaQueryWrapper);
    return rolePermissionList;
  }

  @Override
  public void addPermissionForRole(String roleId, List<String> permissionIdList) {
    
  }
}
