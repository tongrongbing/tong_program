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
    private Integer seq;  //  各个子节点的排序顺序
    private List<TreeNode> children;

    public TreeNode(long id, String name, long parentId, int seq) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.seq = seq;
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

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
