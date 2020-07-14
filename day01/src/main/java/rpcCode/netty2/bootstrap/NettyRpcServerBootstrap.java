package rpcCode.netty2.bootstrap;

import com.mazh.nx.rpc.netty2.server.NettyRPCServer;

/**
 * 一个简单的服务端测试案例
 **/
public class NettyRpcServerBootstrap {

    public static void main(String[] args) {

        NettyRPCServer server = new NettyRPCServer();

        server.serverStart();
    }
}
