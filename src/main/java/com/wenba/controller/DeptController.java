package com.wenba.controller;

import com.wenba.common.http.ApiResponse;
import com.wenba.dto.SysDeptDTO;
import com.wenba.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description: 部门控制层
 * @author: tongrongbing
 * @date: 2020-07-10 11:43
 **/
@RestController
@RequestMapping("/dept")
@Api(tags = "部门控制层")
public class DeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    @ApiOperation(value = "部门添加",notes = "添加部门接口")
    public ApiResponse add(@RequestBody @Valid SysDeptDTO sysDeptDTO){
         sysDeptService.add(sysDeptDTO);
         return ApiResponse.ok();
    }

    @GetMapping("/tree")
    @ApiOperation(value = "部门查询",notes = "获取所有部门接口")
    public ApiResponse getDeptTree(@RequestParam(name = "parentId",defaultValue = "0") Integer id){
        return ApiResponse.ok(sysDeptService.getDeptTree(id));
    }

    @ApiOperation(value = "部门删除",notes = "根据部门ID删除部门接口")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        sysDeptService.deleteDeptById(id);
        return ApiResponse.ok();
    }

}
