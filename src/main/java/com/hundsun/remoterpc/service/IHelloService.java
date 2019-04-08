package com.hundsun.remoterpc.service;

import com.hundsun.remoterpc.entity.User;

/**
 * 接口职责：
 * <p>
 * Title: IHelloService.java
 * Description:
 * Copyright: Copyright (c) 2018 杭州云赢网络科技有限公司
 * Company: 杭州云赢网络科技有限公司
 *
 * @author zhanggl10620@hundsun.com
 * @date 2019.04.08 15:28
 */
public interface IHelloService {

    public String sayHello(String content);

    public String saveUser(User user);


}
