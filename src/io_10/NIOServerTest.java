package io_10;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName NIOServerTest
 * @Description NIO服务器,基于NIO的多路复用
 * @Author 贺楚翔
 * @Date 2020-08-31 15:05
 * @Version 1.0
 **/
public class NIOServerTest {
    public void serve(int port) throws IOException {
        //创建channel，并绑定监听端口
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        final ServerSocket socket = serverSocketChannel.socket();
        final InetSocketAddress address = new InetSocketAddress(port);
        socket.bind(address);
        //创建selector，并将channel注册到selector
        final Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        final ByteBuffer msg = ByteBuffer.wrap("Hi\r\b".getBytes());

        for (;;){
            //监听事件
            selector.select();

            final Set<SelectionKey> readyKeys = selector.selectedKeys();
            final Iterator<SelectionKey> iterator = readyKeys.iterator();

            while (iterator.hasNext()){
                final SelectionKey key = iterator.next();
                iterator.remove();

                try {
                    //如果接收到事件，输出内容
                    if (key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        final SocketChannel accept = server.accept();
                        accept.configureBlocking(false);
                        accept.register(selector,SelectionKey.OP_READ | SelectionKey.OP_WRITE,msg.duplicate());
                        System.out.println("accpet connection from" + accept);
                    }

                    //接下来将处理后的数据返回
                    if (key.isWritable()){
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()){
                            if (socketChannel.write(buffer) == 0){
                                break;
                            }
                            socketChannel.close();
                        }
                    }
                } catch(IOException e){
                    key.cancel();
                    key.channel().close();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        final NIOServerTest nioServerTest = new NIOServerTest();
        nioServerTest.serve(5555);
    }
}
