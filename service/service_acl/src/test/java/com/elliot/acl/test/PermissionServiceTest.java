package com.elliot.acl.test;

import com.elliot.acl.ACLApplication;
import com.elliot.acl.entity.Permission;
import com.elliot.acl.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author elliot
 * @since 2020-09-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ACLApplication.class)
public class PermissionServiceTest {
  @Resource
  private PermissionService permissionService;

  @Test
  public void test() {
    permissionService.listPermissionByRoleId("1");
  }

}
