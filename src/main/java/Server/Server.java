package Server;

import Handler.BufferedHandler;
import Handler.MessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 15:26
 */
public class Server {

    public Server() {
    }

    public  void run() {
        EventLoopGroup pGroup = new NioEventLoopGroup(); // 一组线程负责用于处理Client端的连接请求
        EventLoopGroup cGroup = new NioEventLoopGroup(); // 另一组线程负责信息的交互
        ServerBootstrap b = new ServerBootstrap();       // 辅助工具类，用于服务器通道的一系列配置
        b.group(pGroup,cGroup)
                .channel(NioServerSocketChannel.class)           // 指定NIO的模式
//                .option(ChannelOption.SO_BACKLOG,1024)   // 设置tcp缓存区
//                .option(ChannelOption.SO_SNDBUF,32*1024) // 设置发送缓冲的大小
//                .option(ChannelOption.SO_RCVBUF,32*1024) // 设置接收缓冲的大小
//                .option(ChannelOption.SO_KEEPALIVE,true)  // 保持连接
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline()
                                .addLast(new BufferedHandler())     // 从缓冲区接收数据，组装成对象
                                .addLast(new MessageHandler());     // 对象处理流程，配置具体数据接收方法的处理
                    }
                });
        int port = 5209;
        try {
            ChannelFuture cf1 = b.bind(port).sync();
            cf1.channel().closeFuture().sync();                           // 等待关闭，异步，阻塞当前线程
            pGroup.shutdownGracefully();                                // 清空线程池
            cGroup.shutdownGracefully();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new ConditionMonitor()).start();
        new Thread(new CommandFetcher()).start();
        new Thread(new CommandExecutor()).start();
        new Server().run();

    }
}
