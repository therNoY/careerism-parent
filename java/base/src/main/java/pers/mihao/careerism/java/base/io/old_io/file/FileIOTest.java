package pers.mihao.careerism.java.base.io.old_io.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

/**
 * 文件流使用了使用了 装饰着模式 比如 BufferedOutputStream 的 write是对fileOutputStream的增强
 *
 * @Author mihao
 * @Date 2021/6/2 10:23
 */
public class FileIOTest {

    public static void main(String[] args) throws IOException {
        byteStreamTest();
        characterStreamTest();
    }

    /**
     * 测试字节流
     */
    private static void characterStreamTest() throws IOException {
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream("./test.txt"));
        fileWriter.write("12345\n");
        fileWriter.write("578910");

        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader("./test.txt"));
    }

    /**
     * 测试字符流
     *
     * @throws IOException
     */
    private static void byteStreamTest() throws IOException {
        // 节点流FileOutputStream直接以A.txt作为数据源操作
        FileOutputStream fileOutputStream = new FileOutputStream("./test.txt");
        // 过滤流BufferedOutputStream进一步装饰节点流，提供缓冲写
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
            fileOutputStream);
        // 过滤流DataOutputStream进一步装饰过滤流，使其提供基本数据类型的写
        DataOutputStream out = new DataOutputStream(bufferedOutputStream);
        out.writeInt(3);
        out.writeBoolean(true);
        out.flush();
        out.close();
        // 此处输入节点流，过滤流正好跟上边输出对应，读者可举一反三
        DataInputStream in = new DataInputStream(new BufferedInputStream(
            new FileInputStream("./test.txt")));
        System.out.println(in.readInt());
        System.out.println(in.readBoolean());
        in.close();
    }

}
