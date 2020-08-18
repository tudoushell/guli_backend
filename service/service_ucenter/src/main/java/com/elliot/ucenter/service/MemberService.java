package com.elliot.ucenter.service;

import com.elliot.ucenter.dto.LoginDto;
import com.elliot.ucenter.dto.MemberDto;
import com.elliot.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

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
