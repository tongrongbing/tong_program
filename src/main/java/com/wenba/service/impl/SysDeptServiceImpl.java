package com.wenba.service.impl;

import com.google.common.collect.Lists;
import com.wenba.common.enums.APIStatus;
import com.wenba.common.util.LevelUtil;
import com.wenba.common.util.TreeNodeUtil;
import com.wenba.convert.ConvertProcessor;
import com.wenba.convert.DeptTreeConvertProcessor;
import com.wenba.dto.SysDeptDTO;
import com.wenba.exception.CustomizedException;
import com.wenba.mapper.SysDeptMapper;
import com.wenba.mapper.SysUserInfoMapper;
import com.wenba.model.SysDept;
import com.wenba.service.SysDeptService;
import com.wenba.service.SysUserInfoService;
import com.wenba.vo.SysDeptVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-10 13:34
 **/
@Service
@Slf4j
//@CacheConfig(cacheNames = "SysDeptServiceImpl")
public class SysDeptServiceImpl implements SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysUserInfoService userInfoService;

    @Override
    //@Cacheable
    //@Transactional
    public List<SysDeptVO> getDeptTree(Integer id) {
        // 1.查询所有部门列表
        List<SysDept> sysDeptList = sysDeptMapper.getAllDept();
        //ArrayListMultimap 一个key对应   多个值list集合。
        /*Multimap<Object, Object> multimap = ArrayListMultimap.create();
        for(SysDept sysDept : sysDeptList){
            multimap.put(sysDept.getLevel(),sysDept);
        }*/

        List<SysDeptVO> sysDeptVOList = Lists.newArrayList();
        // 1。转换成传输对象VO
        if(CollectionUtils.isNotEmpty(sysDeptList)){
            sysDeptVOList = sysDeptList.stream().map(SysDeptVO::convert).collect(Collectors.toList());
        }
        List<SysDeptVO>  resultDeptList = TreeNodeUtil.getSysDeptTree(id, sysDeptVOList);
        log.info("=================================");
       // sysDeptMapper.getAllDept();
        return resultDeptList.size() > 0 ? resultDeptList : null;
    }

    @Transactional
    @Override
    public void add(SysDeptDTO sysDeptDTO) {
        //1。校验
        if(sysDeptDTO == null){
            throw new CustomizedException(APIStatus.BAD_PARAM);
        }
        //2。新增之前需要校验当前部门下有没有同名的部门
        if(checkExist(sysDeptDTO.getName(),sysDeptDTO.getParentId())){
            throw new CustomizedException(APIStatus.REPEAT_CREATED);
        }
        //3。新增部门
        SysDept sysDept =  sysDeptDTO.convertToSysDept();
        //BeanUtils.copyProperties(sysDeptDTO,sysDept);
        sysDept.setOperateTime(LocalDateTime.now());
        sysDept.setOperator("魔童");
        sysDept.setOperateIp("127.0.0.1");
        sysDept.setLevel(LevelUtil.calculateLevel(getLevel(sysDeptDTO.getParentId()),sysDeptDTO.getParentId()));
        sysDeptMapper.insert(sysDept);
        int i = 90;
        try {
            userInfoService.deleteUserById(1);
           // i = i / 0;
        }catch (Exception e){
            throw new RuntimeException("error");
        }
    }

    private boolean checkExist(String name, Integer parentId) {
        return sysDeptMapper.countByNameAndParentId(name,parentId) > 0 ? true : false;
    }

    /**
     * @author: tongrongbing
     * @description:  根据父ID查询父级部门是否存在
     * @time: 2020/7/10 3:47 下午
     * @param deptId
     * @return java.lang.String
     */
    private String getLevel(Integer deptId) {
        SysDept dept = sysDeptMapper.selectById(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }

    @Override
    @Transactional
    public void deleteDeptById(Integer id) {
        try {
            sysDeptMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            throw new CustomizedException(APIStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4);
        List<Integer> collect = list.stream().map(num -> num * 2).collect(Collectors.toList());
        System.out.println(collect);

    }

}
