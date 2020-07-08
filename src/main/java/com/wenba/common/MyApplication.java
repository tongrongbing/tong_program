package com.wenba.common;

import com.wenba.common.model.TreeNode;
import com.wenba.common.util.TreeNodeUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-07 17:14
 **/
@SpringBootApplication
@RestController
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);
    }

    @GetMapping("/get")
    public Object getAll(@RequestParam(defaultValue = "-1",required = false) long id){
        return TreeNodeUtil.queryTreeData(id,wrapper());
    }

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
