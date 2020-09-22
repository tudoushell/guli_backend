package com.elliot.acl.controller;


import com.elliot.acl.dto.PermissionDto;
import com.elliot.acl.entity.Permission;
import com.elliot.acl.service.PermissionService;
import com.elliot.common.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
@RestController
@RequestMapping("/acl/permission")
@Api(tags = "权限接口")
public class PermissionController {

  @Resource
  private PermissionService permissionService;

  @ApiOperation("根据角色获取权限")
  @GetMapping("/role/{roleId}")
  public CommonResult<List<PermissionDto>> listPermissionByRoleId(@PathVariable String roleId) {
    return CommonResult.success(permissionService.listPermissionByRoleId(roleId));
  }

  @ApiOperation("获取权限")
  @GetMapping("/{id}")
  public CommonResult<Permission> getPermission(@PathVariable String id) {
    return CommonResult.success(permissionService.getPermission(id));
  }

  @ApiOperation("更新权限")
  @PutMapping("")
  public CommonResult updatePermissionById(@RequestBody Permission permission) {
    permissionService.updatePermission(permission);
    return CommonResult.success(null);
  }

  @ApiOperation("删除权限")
  @DeleteMapping("/{id}")
  public CommonResult deletePermissionById(@PathVariable String id) {
    permissionService.deletePermission(id);
    return CommonResult.success(null);
  }

  @ApiOperation("添加权限")
  @PostMapping("")
  public CommonResult addPermission(@RequestBody Permission permission) {
    permissionService.addPermission(permission);
    return CommonResult.success(null);
  }
}
