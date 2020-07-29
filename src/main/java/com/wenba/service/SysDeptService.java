package com.wenba.service;

import com.wenba.dto.SysDeptDTO;
import com.wenba.vo.SysDeptVO;

import java.util.List;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-10 13:33
 **/

public interface SysDeptService {

    void add(SysDeptDTO sysDeptDTO);

    List<SysDeptVO> getDeptTree(Integer id);

    void deleteDeptById(Integer id);
}

