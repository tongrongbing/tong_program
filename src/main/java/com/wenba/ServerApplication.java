package com.wenba;

import com.wenba.common.model.TreeNode;
import com.wenba.common.util.TreeNodeUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 项目启动类
 * @author: tongrongbing
 * @date: 2020-07-07 17:14
 **/
@SpringBootApplication
@RestController
@MapperScan(basePackages = {"com.wenba.mapper"})
@EnableCaching
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class,args);
    }

    @GetMapping("/get")
    public Object getAll(@RequestParam(defaultValue = "-1",required = false) long id){
        return TreeNodeUtil.queryTreeData(id,wrapper());
    }

    /**
     * @author: tongrongbing
     * @description: 包装文件
     * @time: 2020/7/8 1:56 下午
     * @param
     * @return java.util.List<com.wenba.common.model.TreeNode>
     */
    private  List<TreeNode> wrapper(){
        return Arrays.asList(
                new TreeNode(1,"根结点",-1,1),
                new TreeNode(2,"结点1",1,2),
                new TreeNode(3,"结点2",2,3),
                new TreeNode(4,"结点3",2,4),
                new TreeNode(5,"结点4",3,5),
                new TreeNode(6,"结点5",4,6),
                new TreeNode(7,"结点6",-1,7),
                new TreeNode(8,"结点7",7,8),
                new TreeNode(9,"开发中心",-1,2),
                new TreeNode(10,"后台研发",9,4),
                new TreeNode(11,"web研发",10,2),
                new TreeNode(12,"前端研发",9,3),
                new TreeNode(13,"Java",11,1),
                new TreeNode(14,"php",11,3),
                new TreeNode(15,".net",11,2)
        );
    }
}
