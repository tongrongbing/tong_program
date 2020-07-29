package com.wenba.service.impl;

import com.wenba.mapper.SysUserInfoMapper;
import com.wenba.service.SysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-23 16:02
 **/
@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;

    @Override

    public void delete(int id) {
        sysUserInfoMapper.deleteById(1);
        int i = 0;
        i = 4 / 0;

    }
}
