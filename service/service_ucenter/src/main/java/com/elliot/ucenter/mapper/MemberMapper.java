package com.elliot.ucenter.mapper;

import com.elliot.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author elliot
 * @since 2020-08-17
 */
public interface MemberMapper extends BaseMapper<Member> {

  /**
   * 统计当天注册的人数
   *
   * @param dateStr
   * @return
   */
  long countRegisterUserByToday(@Param("dateStr") String dateStr);
}
