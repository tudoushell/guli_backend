package com.elliot.acl.service.impl;

import com.elliot.acl.entity.User;
import com.elliot.acl.service.PermissionService;
import com.elliot.acl.service.UserService;
import com.elliot.security.entity.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author elliot
 * @since 2020-09-20
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

  @Resource
  private UserService userService;

  @Resource
  private PermissionService permissionService;

  /**
   * 登录检验
   *
   * @param userName
   * @return
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = userService.getUserByUsername(userName);
    if (user == null) {
      log.error("账号或密码错误");
      throw new UsernameNotFoundException("账号或密码错误");
    }
    com.elliot.security.entity.User sUser = new com.elliot.security.entity.User();
    BeanUtils.copyProperties(user, sUser);
    SecurityUser securityUser = new SecurityUser(sUser, new ArrayList<>());
    return securityUser;
  }
}
