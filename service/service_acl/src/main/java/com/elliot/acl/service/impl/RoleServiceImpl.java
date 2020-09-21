package com.elliot.acl.service.impl;

import com.elliot.acl.entity.Role;
import com.elliot.acl.mapper.RoleMapper;
import com.elliot.acl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
