package code.rpc.netty2.bootstrap;

import com.mazh.nx.rpc.netty2.server.NettyRPCServer;

/**
 * 一个简单的服务端测试案例
 **/
public class NettyRpcServerBootstrap {

    public static void main(String[] args) {

        // 初始化 NettyRPCServer
        NettyRPCServer server = null;
        if(args.length == 2){
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            new NettyRPCServer(host, port);
        }else{
            server = new NettyRPCServer();
        }

        // 启动 NettyRPCServer
        server.serverStart();
    }
}
