package com.wenba.model;

import java.io.Serializable;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-23 16:04
 **/

public class SysUserInfo implements Serializable {
    private int id;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
