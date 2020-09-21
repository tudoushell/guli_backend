package com.elliot.acl.service.impl;

import com.elliot.acl.entity.User;
import com.elliot.acl.mapper.UserMapper;
import com.elliot.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Override
  public User getUserById(String id) {
    User user = baseMapper.selectById(id);
    user.setPassword("******");
    return user;
  }

  @Override
  public void deleteUserById(String id) {
    baseMapper.deleteById(id);
  }

  @Override
  public void updateUser(User user) {
    baseMapper.updateById(user);
  }

  @Override
  public void saveUser(User user) {
    String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
    user.setPassword(md5Password);
    baseMapper.insert(user);
  }
}
