package com.elliot.ucenter;

import com.elliot.ucenter.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = {UcenterApplication.class})
@RunWith(SpringRunner.class)
public class MemberServiceTest {
  @Resource
  private MemberService memberService;

  @Test
  public void test() {
    System.out.println(memberService.countRegisterUsers("2020-08-23"));
  }
}
