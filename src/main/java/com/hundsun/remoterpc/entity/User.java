package com.hundsun.remoterpc.entity;

import java.io.Serializable;

/**
 * 类职责：
 * <p>
 * Title: User.java
 * Description:
 * Copyright: Copyright (c) 2018 杭州云赢网络科技有限公司
 * Company: 杭州云赢网络科技有限公司
 *
 * @author zhanggl10620@hundsun.com
 * @date 2019.04.08 15:28
 */
public class User implements Serializable {

    private static final long serialVersionUID = -2924570673401159693L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}