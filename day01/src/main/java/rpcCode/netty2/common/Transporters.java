package rpcCode.netty2.common;

import com.mazh.nx.rpc.netty2.client.NettyRPCClient;
import com.mazh.nx.rpc.netty2.protocol.RpcRequest;
import com.mazh.nx.rpc.netty2.protocol.RpcResponse;

public class Transporters {

    public static RpcResponse send(RpcRequest request) {

        // 初始化一个客户端
        NettyRPCClient nettyRpcClient = new NettyRPCClient(NettyProperties.REMOTE_HOST, NettyProperties.PORT);

        // 链接服务器
        nettyRpcClient.connect(nettyRpcClient.getHostname(), nettyRpcClient.getPort());

        // 发送消息
        RpcResponse rpcResponse = nettyRpcClient.send(request);

        // 拿回结果
        return rpcResponse;
    }
}