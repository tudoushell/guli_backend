package com.elliot.eduorder.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.exception.ApiException;
import com.elliot.eduorder.consts.OrderPayStatus;
import com.elliot.eduorder.consts.OrderPayType;
import com.elliot.eduorder.dto.OrderCourseDto;
import com.elliot.eduorder.dto.OrderUserDto;
import com.elliot.eduorder.entity.Order;
import com.elliot.eduorder.mapper.OrderMapper;
import com.elliot.eduorder.service.EduService;
import com.elliot.eduorder.service.OrderService;
import com.elliot.eduorder.service.UcenterService;
import com.elliot.eduorder.utils.OrderNoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-09-07
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

  @Resource
  private UcenterService ucenterService;

  @Resource
  private EduService eduService;

  @Override
  public Order getOrderByOrderNo(String orderNo) {
    LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(Order::getOrderNo, orderNo);
    Order order = baseMapper.selectOne(lambdaQueryWrapper);
    if (Objects.isNull(order)) {
      throw new ApiException("订单号不存在");
    }
    return order;
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public String addOrder(String courseId, String userId) {
    log.info("远程调用获取用户信息");
    String userById = ucenterService.getUserById(userId);
    OrderUserDto orderUserDto = JSON.parseObject(userById, OrderUserDto.class);
    log.info("远程调用获取课程信息");
    String publishCourse = eduService.getPublishCourse(courseId);
    OrderCourseDto orderCourseDto = JSON.parseObject(publishCourse, OrderCourseDto.class);
    log.info("生成订单");
    Order order = new Order();
    order.setOrderNo(OrderNoUtil.getOrderNo())
            .setStatus(OrderPayStatus.unpaid.getPayStatus())
            .setPayType(OrderPayType.wx.getPayType())
            .setCourseCover(orderCourseDto.getCover())
            .setCourseId(courseId)
            .setCourseTitle(orderCourseDto.getTitle())
            .setMemberId(userId)
            .setMobile(orderUserDto.getMobile())
            .setNickname(orderUserDto.getNickname())
            .setTeacherName(orderCourseDto.getTeacherName())
            .setTotalFee(orderCourseDto.getPrice());
    this.save(order);
    return order.getOrderNo();
  }
}
