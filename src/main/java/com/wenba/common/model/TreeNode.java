package com.wenba.common.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * @description: tree 树节点封装
 * @author: tongrongbing
 * @date: 2020-07-07 16:11
 **/
public class TreeNode implements Serializable {
    private long id;
    private String name;
    private long parentId;
    private Integer level;
    private List<TreeNode> children;

    public TreeNode(long id, String name, long parentId, int level) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
