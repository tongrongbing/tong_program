package com.wenba.convert;

import com.wenba.model.SysDept;
import com.wenba.vo.SysDeptVO;
import org.springframework.beans.BeanUtils;

/**
 * @description: 部门树转换器
 * @author: tongrongbing
 * @date: 2020-07-10 16:17
 **/
public class DeptTreeConvertProcessor implements ConvertProcessor<SysDeptVO,SysDept>{

    /**
     * @author: tongrongbing
     * @description: 将部门实体转换成部门VO对象传给前端
     * @time: 2020/7/10 4:20 下午
     * @param sysDept
     * @return com.wenba.vo.SysDeptVO
     */
    public static SysDeptVO convert2SysDeptVO(SysDept sysDept){
        SysDeptVO sysDeptVO = new SysDeptVO();
        BeanUtils.copyProperties(sysDept,sysDeptVO);
        sysDeptVO.setDeptName(sysDept.getName());
        sysDeptVO.setOrderBy(sysDept.getSeq());
        return sysDeptVO;
    }

    @Override
    public SysDept convert(SysDeptVO sysDeptVO) {
        return null;
    }
}
