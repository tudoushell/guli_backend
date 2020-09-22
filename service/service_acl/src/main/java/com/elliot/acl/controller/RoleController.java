package com.elliot.acl.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elliot.acl.dto.RoleQueryDto;
import com.elliot.acl.entity.Role;
import com.elliot.acl.service.RoleService;
import com.elliot.common.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
@RestController
@RequestMapping("/api/acl/role")
@Api(tags = "用户角色接口")
public class RoleController {

  @Resource
  private RoleService roleService;

  @ApiOperation("分页查询用户角色")
  @PostMapping("/query")
  public CommonResult<Page<Role>> listRoleByPagination(@PathVariable Integer page,
                                                       @PathVariable Integer row,
                                                       @RequestBody RoleQueryDto roleQueryDto) {
    return CommonResult.success(roleService.listRoleByPagination(page, row, roleQueryDto));
  }

  @ApiOperation("获取用户角色")
  @GetMapping("/{id}")
  public CommonResult<Role> getRole(@PathVariable String id) {
    return CommonResult.success(roleService.getRoleById(id));
  }

  @ApiOperation("更新用户角色")
  @PutMapping("")
  public CommonResult updateRole(@Validated @RequestBody Role role) {
    roleService.updateRoleById(role);
    return CommonResult.success(null);
  }
  

  @ApiOperation("删除用户角色")
  @DeleteMapping("/{id}")
  public CommonResult deleteRole(@PathVariable String id) {
    roleService.deleteRoleById(id);
    return CommonResult.success(null);
  }

  @ApiOperation("保存用户角色")
  @PostMapping("")
  public CommonResult saveRole(@Validated @RequestBody Role role) {
    roleService.addRole(role);
    return CommonResult.success(null);
  }

}
