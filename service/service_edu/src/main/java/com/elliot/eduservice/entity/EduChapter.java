package com.elliot.eduservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author elliot
 * @since 2020-07-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="EduChapter对象", description="课程")
public class EduChapter extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "课程ID不能为空")
    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @NotNull(message = "章节名称不能为空")
    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

}
