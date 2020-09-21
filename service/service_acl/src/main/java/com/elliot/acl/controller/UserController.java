package com.elliot.acl.controller;


import com.elliot.acl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-09-20
 */
@RestController
@RequestMapping("/acl/user")
public class UserController {
  @Autowired
  private UserService userService;

}
