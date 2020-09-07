package com.elliot.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.constant.ResultCode;
import com.elliot.common.exception.ApiException;
import com.elliot.common.utils.JwtUtil;
import com.elliot.ucenter.dto.LoginDto;
import com.elliot.ucenter.dto.MemberDto;
import com.elliot.ucenter.dto.UserDto;
import com.elliot.ucenter.entity.Member;
import com.elliot.ucenter.mapper.MemberMapper;
import com.elliot.ucenter.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-08-17
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

  @Resource
  private RedisTemplate<String, String> restTemplate;

  @Override
  public UserDto getUserById(String id) {
    Member member = baseMapper.selectById(id);
    UserDto userDto = new UserDto();
    if (Objects.nonNull(member)) {
      BeanUtils.copyProperties(member, userDto);
    }
    return userDto;
  }

  @Override
  public UserDto getUserInfo(HttpServletRequest request) {
    String memberIdByJwtToken = JwtUtil.getMemberIdByJwtToken(request);
    return JSON.parseObject(memberIdByJwtToken, UserDto.class);
  }

  @Override
  public void register(MemberDto memberDto) {
    String code = restTemplate.opsForValue().get(memberDto.getMobile());
    if (!memberDto.getCode().equals(code)) {
      throw new ApiException("验证码错误");
    }
    LambdaQueryWrapper<Member> memberLambdaQueryWrapper = new LambdaQueryWrapper<>();
    memberLambdaQueryWrapper.eq(Member::getMobile, memberDto.getMobile());
    Integer phoneCount = baseMapper.selectCount(memberLambdaQueryWrapper);
    if (phoneCount != 0) {
      throw new ApiException("手机号已存在");
    }
    Member member = new Member();
    BeanUtils.copyProperties(memberDto, member);
    this.save(member);
  }

  @Override
  public String login(LoginDto loginDto) {
    final String PASSWORD = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
    LambdaQueryWrapper<Member> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(Member::getMobile, loginDto.getMobile());
    Member member = baseMapper.selectOne(lambdaQueryWrapper);
    if (Objects.isNull(member)) {
      throw new ApiException(ResultCode.PASSWORDERROR);
    }
    if (!member.getPassword().equals(PASSWORD)) {
      throw new ApiException(ResultCode.PASSWORDERROR);
    }
    if (member.getIsDisabled() && member.getIsDisabled()) {
      throw new ApiException("帐号已停用");
    }
    return JwtUtil.getJwtToken(member.getId(), member.getNickname(), member.getAvatar());
  }
}
