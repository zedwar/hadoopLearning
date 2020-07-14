package code.rpc.netty2.service.impl;

import com.mazh.nx.rpc.netty2.service.HelloService;

/**
 * 一个简单的服务实现
 **/
public class HelloServiceImpl implements HelloService {

    public HelloServiceImpl() {
    }

    @Override
    public String hello(String name) {
        return "Server: Hello " + name;
    }
}
