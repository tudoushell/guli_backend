package com.elliot.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.elliot.acl.dto.PermissionDto;
import com.elliot.acl.entity.Permission;
import com.elliot.acl.entity.RolePermission;
import com.elliot.acl.mapper.PermissionMapper;
import com.elliot.acl.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.acl.service.RolePermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

  @Resource
  private RolePermissionService rolePermissionService;

  @Override
  public List<PermissionDto> listPermissionByRoleId(String roleId) {
    List<RolePermission> rolePermissionList = rolePermissionService.listPermissionByRole(roleId);
    List<String> permissionIdList = rolePermissionList.stream().map(rolePermission -> rolePermission.getPermissionId()).collect(Collectors.toList());
    LambdaQueryWrapper<Permission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.in(Permission::getId, permissionIdList);
    List<Permission> permissions = baseMapper.selectList(lambdaQueryWrapper);
    List<PermissionDto> permissionDtoList = parsePermissionToTree("0", permissions);
    return permissionDtoList;
  }

  @Override
  public Permission getPermission(String id) {
    return baseMapper.selectById(id);
  }

  @Override
  public void updatePermission(Permission permission) {
    baseMapper.updateById(permission);
  }

  @Override
  public void deletePermission(String id) {
    //todo 逻辑
    baseMapper.deleteById(id);
  }

  @Override
  public void addPermission(Permission permission) {
    baseMapper.insert(permission);
  }

  /**
   * 将菜单解析成树状结构
   *
   * @param parentId
   * @param permissionList
   * @return
   */
  private List<PermissionDto> parsePermissionToTree(String parentId, List<Permission> permissionList) {
    List<PermissionDto> permissionDtoList = new ArrayList<>();
    permissionList.forEach(permission -> {
      if (parentId.equals(permission.getPid())) {
        PermissionDto permissionDto = new PermissionDto();
        BeanUtils.copyProperties(permission, permissionDto);
        permissionDto.setChildren(parsePermissionToTree(permission.getId(), permissionList));
        permissionDtoList.add(permissionDto);
      }
    });
    return permissionDtoList;
  }
}
