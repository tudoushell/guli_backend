package com.elliot.ucenter.service.impl;

import com.elliot.ucenter.entity.Member;
import com.elliot.ucenter.mapper.MemberMapper;
import com.elliot.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
