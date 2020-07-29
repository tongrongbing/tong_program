package com.wenba.common.util;
import com.wenba.common.model.TreeNode;
import com.wenba.vo.SysDeptVO;

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

    /**
     * @author: tongrongbing
     * @description:  根据指定部门ID，获取该部门下所有的部门
     * @time: 2020/7/10 5:12 下午
     * @param id
     * @param nodeList
     * @return java.util.List<com.wenba.vo.SysDeptVO>
     */
    public static List<SysDeptVO> getSysDeptTree(long id, List<SysDeptVO> nodeList){
        List<SysDeptVO> treeNodeList = new ArrayList<SysDeptVO>();
        for (SysDeptVO node: nodeList) {
            if(id == node.getParentId()){
                node.setChildrenList(getSysDeptTree(node.getId(),nodeList));
                treeNodeList.add(node);
            }
        }
        // 对根节点进行降序
        treeNodeList.sort(Comparator.comparing(SysDeptVO::getOrderBy).reversed());
        return treeNodeList;
    }
}
