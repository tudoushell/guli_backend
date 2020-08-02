package com.elliot.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.elliot.cmsservice.entity.CrmBanner;
import com.elliot.cmsservice.mapper.CrmBannerMapper;
import com.elliot.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elliot.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author elliot
 * @since 2020-07-30
 */
@Slf4j
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

  @Override
  public void deleteBannerById(String id) {
    baseMapper.deleteById(id);
  }

  @Override
  public CrmBanner getBanner(String id) {
    CrmBanner crmBanner = getById(id);
    return crmBanner;
  }
  
  @Cacheable(value = {"bannerCache"}, key = "'bannerGroup'")
  @Override
  public List<CrmBanner> listBanner() {
    List<CrmBanner> crmBannerList = list();
    return crmBannerList;
  }

  @Override
  public void updateBanner(CrmBanner crmBanner) {
    CrmBanner oldBanner = baseMapper.selectById(crmBanner.getId());
    if (Objects.isNull(oldBanner)) {
      throw new ApiException("数据不存在");
    }
    if (!oldBanner.getTitle().equals(crmBanner.getTitle())) {
      isExistsBannerTitle(crmBanner.getTitle());
    }
    updateById(crmBanner);
  }

  @Override
  public void addBanner(CrmBanner crmBanner) {
    isExistsBannerTitle(crmBanner.getTitle());
    save(crmBanner);
  }

  private void isExistsBannerTitle(String title) {
    LambdaQueryWrapper<CrmBanner> bannerLambdaQueryWrapper = new LambdaQueryWrapper<>();
    bannerLambdaQueryWrapper.eq(CrmBanner::getTitle, title);
    CrmBanner crmBanner = baseMapper.selectOne(bannerLambdaQueryWrapper);
    if (Objects.nonNull(crmBanner)) {
      throw new ApiException("标题已存在");
    }
  }
}
