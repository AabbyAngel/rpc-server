package com.hundsun.remoterpc.proxy;

import com.hundsun.remoterpc.handler.ProcessorHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类职责：
 * <p>发布服务代理
 * Title: RpcServerProxy.java
 * Description:
 * Copyright: Copyright (c) 2018 杭州云赢网络科技有限公司
 * Company: 杭州云赢网络科技有限公司
 *
 * @author zhanggl10620@hundsun.com
 * @date 2019.04.08 15:41
 */
public class RpcServerProxy {

    ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 发布服务
     * @param service 服务名称
     * @param port 服务的端口号
     */
    public void publisher(Object service,int port){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while(true){
                //接收一个请求
                Socket socket = serverSocket.accept();
                //BIO模型是用线程池处理socket请求
                executorService.execute(new ProcessorHandler(socket,service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}