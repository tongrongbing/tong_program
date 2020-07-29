package com.wenba.service;


import com.wenba.ServerApplication;
import com.wenba.mapper.SysDeptMapper;
import com.wenba.model.SysDept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-10 14:33
 **/
@RunWith(SpringRunner.class)
//启动Spring
@SpringBootTest(classes = ServerApplication.class)
public class SysDeptServiceTest {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Test
    public void add() {
        SysDept sysDept = new SysDept();
        sysDept.setName("k");
        sysDept.setLevel("0");
        sysDept.setParentId(0);
        sysDept.setOperateIp("22");
        sysDept.setSeq(1);
        sysDept.setOperator("ss");
        sysDept.setOperateTime(LocalDateTime.now());
        sysDept.setRemark("背书");
        sysDeptMapper.insert(sysDept);
    }
}