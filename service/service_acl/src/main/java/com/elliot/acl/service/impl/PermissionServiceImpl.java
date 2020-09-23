package com.elliot.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.acl.dto.PermissionDto;
import com.elliot.acl.entity.Permission;
import com.elliot.acl.entity.RolePermission;
import com.elliot.acl.mapper.PermissionMapper;
import com.elliot.acl.service.PermissionService;
import com.elliot.acl.service.RolePermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  private final static String PARENT_ID = "0";

  @Override
  public List<PermissionDto> listAllPermission() {
    List<Permission> permissions = baseMapper.selectList(null);
    List<PermissionDto> permissionDtoList = parsePermissionToTree(PARENT_ID, permissions);
    return permissionDtoList;
  }

  @Override
  public List<PermissionDto> listPermissionByRoleId(String roleId) {
    List<RolePermission> rolePermissionList = rolePermissionService.listPermissionByRole(roleId);
    List<String> permissionIdList = rolePermissionList.stream().map(rolePermission -> rolePermission.getPermissionId()).collect(Collectors.toList());
    LambdaQueryWrapper<Permission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.in(Permission::getId, permissionIdList);
    List<Permission> permissions = baseMapper.selectList(lambdaQueryWrapper);
    List<PermissionDto> permissionDtoList = parsePermissionToTree(PARENT_ID, permissions);
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

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void deletePermission(String id) {
    List<String> idList = new ArrayList<>();
    listChildrenIds(id, idList);
    idList.add(id);
    baseMapper.deleteBatchIds(idList);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void addPermission(Permission permission) {
    baseMapper.insert(permission);
  }

  /**
   * 获取子菜单id
   *
   * @param id
   * @param idList
   */
  private void listChildrenIds(String id, List<String> idList) {
    LambdaQueryWrapper<Permission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(Permission::getPid, id);
    lambdaQueryWrapper.select(Permission::getId);
    List<Permission> permissionList = baseMapper.selectList(lambdaQueryWrapper);
    permissionList.forEach(permission -> {
      idList.add(permission.getId());
      listChildrenIds(permission.getId(), idList);
    });
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
