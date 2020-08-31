package com.elliot.ucenter;

import com.elliot.ucenter.service.impl.WxServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = UcenterApplication.class)
@RunWith(SpringRunner.class)
public class WxServiceTest {

  @Test
  public void test() {
    WxServiceImpl wxService = new WxServiceImpl();
    wxService.getWxAccessToken("0819pvFa18Iovz0GcXFa13Rwf039pvFe");
  }
}
