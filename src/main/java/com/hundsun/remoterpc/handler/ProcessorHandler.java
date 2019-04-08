package com.hundsun.remoterpc.handler;

import com.hundsun.remoterpc.proxy.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 类职责：
 * <p>处理Socket类
 * Title: ProcessorHandler.java
 * Description:
 * Copyright: Copyright (c) 2018 杭州云赢网络科技有限公司
 * Company: 杭州云赢网络科技有限公司
 *
 * @author zhanggl10620@hundsun.com
 * @date 2019.04.08 15:51
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;

    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        System.out.println("开始处理客户端请求...");
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            try {
                //Java的反序列化
                RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
                Object result = invoke(rpcRequest);
                System.out.println("返回结果 ： " + result);
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(result);
                outputStream.flush();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("处理客户端请求结束...");
    }

    /**
     * 服务调用，Java的反射实现
     *
     * @return
     */
    private Object invoke(RpcRequest rpcRequest) {
        Object[] args = rpcRequest.getParameters();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
            System.out.println("类型 = " + types[i]);
        }
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), types);
            System.out.println("方法名 =  " + method);
            try {
                return method.invoke(service, args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
