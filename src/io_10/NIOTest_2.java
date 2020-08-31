package io_10;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NIOTest_2
 * @Description NIO  CHANNEL测试
 * @Author 贺楚翔
 * @Date 2020-08-31 14:33
 * @Version 1.0
 **/
public class NIOTest_2 {
    public static void main(String[] args) throws IOException {
        String fileName = "./src/io_10/test";
        final RandomAccessFile file = new RandomAccessFile(fileName,"rw");
        final FileChannel fileChannel = file.getChannel();

        final ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        int read = fileChannel.read(byteBuffer);

        while (read != -1){
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.println((char) byteBuffer.get());
            }
            //重新读
            byteBuffer.rewind();
            //标记position
            byteBuffer.mark();
            //清除读过的数据
            byteBuffer.compact();

            //缓冲区
            byteBuffer.clear();
            read = fileChannel.read(byteBuffer);
        }
    }
}
