package com.elliot.eduorder.test;

import com.elliot.eduorder.OrderApplication;
import com.elliot.eduorder.service.OrderService;
import com.elliot.eduorder.service.PayLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = OrderApplication.class)
@RunWith(SpringRunner.class)
public class OrderServiceTest {
  @Resource
  private OrderService orderService;

  @Resource
  private PayLogService payLogService;

  @Test
  public void test2() {
    payLogService.getOrderStatus("20200909221607195");
  }

  @Test
  public void test() {
    orderService.addOrder("0bb1556926c3af3e621a1257459c8c55", "57c60796a8de2cd9ab5422ebdf182df7");
  }
}
