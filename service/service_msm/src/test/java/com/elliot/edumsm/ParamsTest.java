package com.elliot.edumsm;

import com.elliot.edumsm.consts.AliParams;
import com.elliot.edumsm.service.MsmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MsmApplication.class)
@RunWith(SpringRunner.class)
public class ParamsTest {

  @Autowired
  private MsmService msmService;

  @Test
  public void test() {
    System.out.println(AliParams.ACCESS_KEY_ID);
  }

}
