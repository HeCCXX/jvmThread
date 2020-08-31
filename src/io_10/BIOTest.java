package io_10;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName BIOTest
 * @Description 基于原始的IO和socket编写的一个简单BIO服务器
 * @Author 贺楚翔
 * @Date 2020-08-31 10:01
 * @Version 1.0
 **/
public class BIOTest {
    public void server(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);

        for (;;){
            final Socket accept = socket.accept();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("accept connect from " + accept);
            new Thread(()->{
                OutputStream out;
                try {
                    out = accept.getOutputStream();
                    out.write("Hi\r\n".getBytes(StandardCharsets.UTF_8));
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        accept.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) throws IOException {
        final BIOTest bioTest = new BIOTest();
        bioTest.server(5555);
    }
}
