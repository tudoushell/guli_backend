package com.elliot.cmsservice.controller;


import com.elliot.cmsservice.entity.CrmBanner;
import com.elliot.cmsservice.service.CrmBannerService;
import com.elliot.common.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author elliot
 * @since 2020-07-30
 */
@CrossOrigin
@RestController
@RequestMapping("/api/cms-service/crm-banner")
@Api(tags = "banner接口")
public class CrmBannerController {

  @Resource
  private CrmBannerService crmBannerService;

  @ApiOperation("删除banner")
  @DeleteMapping("/{id}")
  public CommonResult deleteBanner(@PathVariable String id) {
    crmBannerService.deleteBannerById(id);
    return CommonResult.success(null);
  }

  @ApiOperation("获取banner")
  @GetMapping("/{id}")
  public CommonResult<CrmBanner> getBanner(@PathVariable String id) {
    CrmBanner banner = crmBannerService.getBanner(id);
    return CommonResult.success(banner);
  }

  @ApiOperation("列出所有banner")
  @GetMapping("")
  public CommonResult<List<CrmBanner>> listBanner() {
    List<CrmBanner> crmBannerList = crmBannerService.listBanner();
    return CommonResult.success(crmBannerList);
  }

  @ApiOperation("修改banner")
  @PutMapping("")
  public CommonResult updateBanner(@RequestBody CrmBanner crmBanner) {
    crmBannerService.updateBanner(crmBanner);
    return CommonResult.success(null);
  }

  @ApiOperation("添加Banner")
  @PostMapping("")
  public CommonResult addBanner(@RequestBody CrmBanner crmBanner) {
    crmBannerService.save(crmBanner);
    return CommonResult.success(null);
  }

}
