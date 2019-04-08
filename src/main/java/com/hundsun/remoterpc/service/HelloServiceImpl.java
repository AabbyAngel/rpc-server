package com.hundsun.remoterpc.service;

import com.hundsun.remoterpc.entity.User;

/**
 * 类职责：
 * <p>
 * Title: HelloServiceImpl.java
 * Description:
 * Copyright: Copyright (c) 2018 杭州云赢网络科技有限公司
 * Company: 杭州云赢网络科技有限公司
 *
 * @author zhanggl10620@hundsun.com
 * @date 2019.04.08 15:36
 */
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String content) {
        return "Hello " + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("user = " + user.toString());
        return "success";
    }
}