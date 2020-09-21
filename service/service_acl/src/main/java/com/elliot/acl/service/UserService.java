package com.elliot.acl.service;

import com.elliot.acl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
public interface UserService extends IService<User> {

  /**
   * 根据ID获取用户信息
   *
   * @param id
   * @return
   */
  User getUserById(String id);

  /**
   * 删除用户
   *
   * @param id
   */
  void deleteUserById(String id);

  /**
   * 更新用户
   *
   * @param user
   */
  void updateUser(User user);

  /**
   * 添加用户
   *
   * @param user
   */
  void saveUser(User user);
}
