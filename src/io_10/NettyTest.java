package io_10;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName NettyTest
 * @Description Netty 测试
 * @Author 贺楚翔
 * @Date 2020-08-31 17:04
 * @Version 1.0
 **/
public class NettyTest {
    public void serve(int port) throws InterruptedException {
        final ByteBuf byteBuf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi\r\n", StandardCharsets.UTF_8));
        //第一步，创建线程池
        final NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        final NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            //第二步，创建启动类
            final ServerBootstrap bootstrap = new ServerBootstrap();
            //第三步，配置各组件
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(byteBuf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });

            //第四步，开启监听
            final ChannelFuture future = bootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully().sync();
            workGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final NettyTest nettyTest = new NettyTest();
        nettyTest.serve(5555);
    }
}
