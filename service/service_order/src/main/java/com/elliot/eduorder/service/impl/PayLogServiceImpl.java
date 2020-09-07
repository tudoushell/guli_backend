package com.elliot.eduorder.service.impl;

import com.elliot.eduorder.entity.PayLog;
import com.elliot.eduorder.mapper.PayLogMapper;
import com.elliot.eduorder.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-09-07
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
