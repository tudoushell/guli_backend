package com.elliot.acl.service.impl;

import com.elliot.acl.entity.Permission;
import com.elliot.acl.mapper.PermissionMapper;
import com.elliot.acl.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
