package com.wenba.common.util;

import java.util.ArrayList;
import java.util.List;
/**
 * @author: tongrongbing
 * @description: 树工具类
 * @time: 2020/7/8 2:02 下午
 * @return
 */
public class TreeNoteUtils {

    /**
     * Description 解析树形数据
     * @Auther [li.wang@jsxfedu.com]
     * @Date 2019/8/7 上午9:21
     * @param topId 
     * @param entityList
     * @return java.util.List<E>
     */
    public static <E extends TreeNoteBase<E>> List<E> getTreeList(Long topId, List<E> entityList) {
        List<E> resultList =  new ArrayList<E>();
        // 获取顶层元素集合
        Long parentId;
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (parentId == null || topId.equals(parentId)) {
                resultList.add(entity);
            }
        }
        // 获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setChildren(getSubList(entity.getId(), entityList));
        }
        return resultList;
    }

    public static <E extends TreeNoteBase<E>> E getTreeNode(Long topId, List<E> entityList) {
        List<E> resultList = new ArrayList<E>();
        // 获取顶层元素集合
        Long parentId;
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (parentId == null || topId.equals(parentId)) {
                resultList.add(entity);
            }
        }
        // 获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setChildren(getSubList(entity.getId(), entityList));
        }
        if(resultList.isEmpty()){
            return null;
        }
        return resultList.get(0);
    }

    /**
     * Description 获取子数据集合
     * @Auther [li.wang@jsxfedu.com]
     * @Date 2019/8/7 上午9:23
     * @param id 
     * @param entityList
     * @return java.util.List<E>
     */
    private static <E extends TreeNoteBase<E>> List<E> getSubList(Long id, List<E> entityList) {
        List<E> childList = new ArrayList<E>();
        Long parentId;
        // 子集的直接子对象
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (id.equals(parentId)) {
                childList.add(entity);
            }
        }
        // 子集的间接子对象
        for (E entity : childList) {
            entity.setChildren(getSubList(entity.getId(), entityList));
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return childList;
        }
        return childList;
    }
}
