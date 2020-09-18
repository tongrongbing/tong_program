package com.wenba.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.wenba.model.SysDept;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: 部门传输对象
 * @author: tongrongbing
 * @date: 2020-07-10 16:11
 **/
public class SysDeptVO implements Serializable {

    private Integer id;

    private Integer parentId;

    private String deptName;

    private Integer orderBy;

    private List<SysDeptVO> childrenList = Lists.newArrayList();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date operateTime;

    public  static SysDeptVO convert(SysDept sysDept) {
        SysDeptVO sysDeptVO = new SysDeptVO();
        try {
            BeanUtils.copyProperties(sysDept,sysDeptVO);
            sysDeptVO.setDeptName(sysDept.getName());
            sysDeptVO.setOrderBy(sysDept.getSeq());
        }catch (Exception e){
            e.printStackTrace();
        }
        return sysDeptVO;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public List<SysDeptVO> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<SysDeptVO> childrenList) {
        this.childrenList = childrenList;
    }
}
