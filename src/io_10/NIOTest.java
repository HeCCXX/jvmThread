package io_10;


import java.nio.ByteBuffer;

/**
 * @ClassName NIOTest
 * @Description NIO ByteBuffer类测试
 * @Author 贺楚翔
 * @Date 2020-08-31 14:15
 * @Version 1.0
 **/
public class NIOTest {
    public static void main(String[] args) {
        final ByteBuffer buffer = ByteBuffer.allocate(100);
        System.out.println(buffer);

        String value = "Netty权威指南";
        buffer.put(value.getBytes());
        System.out.println(buffer);

        buffer.flip();
        System.out.println(buffer);

        final byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);

        System.out.println(buffer);
        System.out.println(new String(bytes));
    }
}
