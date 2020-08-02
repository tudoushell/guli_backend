package com.elliot.cmsservice.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 首页banner表
 * </p>
 *
 * @author elliot
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="CrmBanner对象", description="首页banner表")
public class CrmBanner extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

    @ApiModelProperty(value = "链接地址")
    private String linkUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Boolean isDeleted;
}
