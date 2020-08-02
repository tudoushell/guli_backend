package com.elliot.cmsservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elliot.cmsservice.entity.CrmBanner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author elliot
 * @since 2020-07-30
 */
public interface CrmBannerService extends IService<CrmBanner> {

  /**
   * 删除banner
   *
   * @param id
   */
  void deleteBannerById(String id);

  /**
   * 获取banner
   *
   * @param id
   * @return
   */
  CrmBanner getBanner(String id);

  /**
   * 显示banner
   *
   * @return
   */
  List<CrmBanner> listBanner();

  /**
   * 更新banner
   *
   * @param crmBanner
   */
  void updateBanner(CrmBanner crmBanner);

  /**
   * 添加banner
   *
   * @param crmBanner
   */
  void addBanner(CrmBanner crmBanner);
}
