package com.wenba.common.util;
import com.wenba.common.model.TreeNode;

import java.util.*;

/**
 * @description: 数据封装成树工具类
 * @author: tongrongbing
 * @date: 2020-07-07 16:09
 **/
public class TreeNodeUtil {

    /**
     * @author: tongrongbing
     * @description:  根据指定的ID，获取该ID下所有的子节点
     * @time: 2020/7/7 6:59 下午
     * @param id
     * @param nodeList
     * @return java.util.List<com.wenba.common.model.TreeNode>
     */
    public static List<TreeNode> queryTreeData(long id,List<TreeNode> nodeList){
        List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
        for (TreeNode node: nodeList) {
            if(id == node.getParentId()){
                node.setChildren(queryTreeData(node.getId(),nodeList));
                treeNodeList.add(node);
            }
        }
        // 对根节点进行降序
        treeNodeList.sort(Comparator.comparing(TreeNode::getSeq).reversed());
        return treeNodeList;
    }
}
