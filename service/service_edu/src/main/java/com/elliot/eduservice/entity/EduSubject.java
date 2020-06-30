package com.elliot.eduservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author elliot
 * @since 2020-06-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@ApiModel(value="EduSubject对象", description="课程科目")
public class EduSubject extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类别名称")
    private String title;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "排序字段")
    private String sort;

}
