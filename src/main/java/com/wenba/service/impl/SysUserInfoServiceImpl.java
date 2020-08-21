package com.wenba.service.impl;

import com.wenba.mapper.SysDeptMapper;
import com.wenba.mapper.SysUserInfoMapper;
import com.wenba.service.SysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-23 16:02
 **/
@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public void deleteUserById(int id) {
        int i = 90;
        try {
            sysDeptMapper.deleteUserById(id);
            i = i / 0;
        }catch (Exception e){
            System.out.println("=====================");
            throw new RuntimeException("------s是生生世世生生世世");
        }

    }


}
