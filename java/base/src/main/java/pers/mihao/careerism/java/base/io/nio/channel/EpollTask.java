package pers.mihao.careerism.java.base.io.nio.channel;

import util.log.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class EpollTask {
    private SocketChannel socketChannel;
    private SelectionKey key;
    private int state;
    private int dividend;
    private int divisor;
    private int result;
    private ByteBuffer writeBuffer;

    public EpollTask(SocketChannel socketChannel, SelectionKey key) {
        this.socketChannel = socketChannel;
        writeBuffer = ByteBuffer.allocate(64);
        this.key = key;
    }

    public void onRead(int data) {
        if (state == 0) {
            dividend = data;
            Logger.info("收到除数" + dividend);
            state = 1;
        } else if (state == 2) {
            divisor = data;
            Logger.info("收到被除数" + divisor);

            if (divisor == 0)
                result = Integer.MAX_VALUE;
            else
                result = dividend / divisor;
            state = 3;
        } else {
            throw new RuntimeException("wrong state " + state);
        }
    }

    public void onWrite() {
        try {
            if (state == 1) {
                writeBuffer.clear();
                writeBuffer.put(("收到除数:" + dividend).getBytes());
                writeBuffer.flip();
                socketChannel.write(writeBuffer);
                state = 2;
            }
            else if (state == 3) {
                writeBuffer.clear();
                writeBuffer.put((dividend + "/" + divisor + " = " + result).getBytes());
                writeBuffer.flip();
                socketChannel.write(writeBuffer);

                socketChannel.close();
                key.cancel();
                state = 4;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}