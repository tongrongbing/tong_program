package com.wenba.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenba.model.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-10 14:00
 **/

public interface SysDeptMapper extends BaseMapper<SysDept> {

    List<SysDept> getAllDept();

    int countByNameAndParentId(@Param("name") String name,@Param("parentId") Integer parentId);

    int deleteByPrimaryKey(@Param("id") Integer id);

    void deleteUserById(int id);
}
