package com.elliot.eduservice.handler;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MetaObjectHandler implements com.baomidou.mybatisplus.core.handlers.MetaObjectHandler {
  @Override
  public void insertFill(MetaObject metaObject) {
    this.strictInsertFill(metaObject, "gmtCreate", Date.class, new Date());
    this.strictInsertFill(metaObject, "gmtModified", Date.class, new Date());
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    this.strictInsertFill(metaObject, "gmtModified", Date.class, new Date());
  }
}
