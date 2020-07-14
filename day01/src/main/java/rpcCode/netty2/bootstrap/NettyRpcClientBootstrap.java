package rpcCode.netty2.bootstrap;

import com.mazh.nx.rpc.netty2.common.ProxyFactory;
import com.mazh.nx.rpc.netty2.service.HelloService;

/**
 * 一个客户端测试案例
 **/
public class NettyRpcClientBootstrap {

    public static void main(String[] args) {

        HelloService helloServiceProxy = ProxyFactory.create(HelloService.class);

        helloServiceProxy.hello("huangbo");
    }
}
