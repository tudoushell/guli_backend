package com.elliot.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elliot.ucenter.dto.LoginDto;
import com.elliot.ucenter.dto.MemberDto;
import com.elliot.ucenter.dto.UserDto;
import com.elliot.ucenter.entity.Member;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-08-17
 */
public interface MemberService extends IService<Member> {

  /**
   * 统计当天注册的人数
   *
   * @param dateStr
   * @return
   */
  long countRegisterUsers(String dateStr);


  /**
   * 根据ID获取用户信息
   *
   * @param id 用户ID
   * @return
   */
  UserDto getUserById(String id);

  /**
   * 获取用户信息
   *
   * @param request
   * @return
   */
  UserDto getUserInfo(HttpServletRequest request);

  /**
   * 用户注册
   *
   * @param memberDto
   */
  void register(MemberDto memberDto);

  /**
   * 用户登录
   *
   * @param loginDto
   * @return
   */
  String login(LoginDto loginDto);
}
