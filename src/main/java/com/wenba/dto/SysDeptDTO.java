package com.wenba.dto;

import com.google.common.base.Converter;
import com.wenba.model.SysDept;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-10 13:23
 **/
@Getter
@Setter
@ApiModel(value = "部门参数")
public class SysDeptDTO {
    private Integer id;

    @NotBlank(message = "部门名称不可以为空")
    @ApiModelProperty(value = "部门名称",required = false)
    private String name;

    @ApiModelProperty(value = "父部门ID",required = false)
    private Integer parentId = 0;

    @NotNull(message = "展示顺序不可以为空")
    @ApiModelProperty(value = "排序",required = false)
    private Integer seq;
    @ApiModelProperty(value = "备注",required = false)
    private String remark;

    public SysDept convertToSysDept(){
        SysDeptDTOConvert sysDeptDTOConvert = new SysDeptDTOConvert();
        SysDept sysDept = sysDeptDTOConvert.convert(this);
        return sysDept;
    }

    public static class SysDeptDTOConvert extends Converter<SysDeptDTO,SysDept> {
        @Override
        protected SysDept doForward(SysDeptDTO sysDeptDTO) {
            SysDept sysDept = new SysDept();
            BeanUtils.copyProperties(sysDeptDTO,sysDept);
            return sysDept;
        }

        @Override
        protected SysDeptDTO doBackward(SysDept sysDept) {
            SysDeptDTO sysDeptDTO = new SysDeptDTO();
            BeanUtils.copyProperties(sysDept,sysDeptDTO);
            return sysDeptDTO;
        }
    }
}
