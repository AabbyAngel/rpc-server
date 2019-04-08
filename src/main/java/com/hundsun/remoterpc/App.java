package com.hundsun.remoterpc;

import com.hundsun.remoterpc.proxy.RpcServerProxy;
import com.hundsun.remoterpc.service.HelloServiceImpl;
import com.hundsun.remoterpc.service.IHelloService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        IHelloService iHelloService = new HelloServiceImpl();
        RpcServerProxy proxy = new RpcServerProxy();
        proxy.publisher(iHelloService, 8080);
        System.out.println("iHelloService服务发布成功！");
    }
}
