package io_10;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.*;

/**
 * @ClassName NettyServerHandlerTest
 * @Description Netty Handler测试  下列程序例子介绍Handler接收和发送消息的顺序（双向链表），接收是从表头开始遍历，发送是从表尾开始遍历
 * @Author 贺楚翔
 * @Date 2020-09-08 14:18
 * @Version 1.0
 **/
public class NettyServerHandlerTest {
    final static ByteBuf buffer = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi\r\n", UTF_8));

    public void serve(int port) throws InterruptedException {
        //建立线程池
        final NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        final NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            //创建服务端启动引导类
            final ServerBootstrap serverBootstrap = new ServerBootstrap();
            //注册组件
            serverBootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //添加拦截处理器，用于拦截和处理入站、出站操作
                            final ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("1",new InboundA());
                            pipeline.addLast("2",new OutboundA());
                            pipeline.addLast("3",new InboundB());
                            pipeline.addLast("4",new OutboundB());
                            pipeline.addLast("5",new OutboundC());
                            pipeline.addLast("6",new InboundC());
                        }
                    });
            //异步回调
            final ChannelFuture future = serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully().sync();
            workGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final NettyServerHandlerTest nettyServerHandlerTest = new NettyServerHandlerTest();
        nettyServerHandlerTest.serve(5555);
    }

    /**
    * @Author HCX
    * @Description //以下是三个inbound和三个outbound，在pipeline中分别注册调用，得知
     * 接收和发送消息的顺序，在双向链表中是如何调用的
    * @Date 9:21 2020-09-09
    * @param null
    * @return
    * @exception
    **/
    class InboundA extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            System.out.println("InboundA read:" + buf.toString(Charset.forName("UTF-8")));
            super.channelRead(ctx, msg);
        }
    }
    class InboundB extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            System.out.println("InboundB read:" + buf.toString(Charset.forName("UTF-8")));
            super.channelRead(ctx, msg);
            ctx.channel().writeAndFlush(buffer);
        }
    }
    class InboundC extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            System.out.println("InboundC read:" + buf.toString(Charset.forName("UTF-8")));
            super.channelRead(ctx, msg);
        }
    }

    class OutboundA extends ChannelOutboundHandlerAdapter{
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("OutboundA write");
            super.write(ctx, msg, promise);
        }
    }

    class OutboundB extends ChannelOutboundHandlerAdapter{
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("OutboundB write");
            super.write(ctx, msg, promise);
        }
    }

    class OutboundC extends ChannelOutboundHandlerAdapter{
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("OutboundC write");
            super.write(ctx, msg, promise);
        }
    }
}
