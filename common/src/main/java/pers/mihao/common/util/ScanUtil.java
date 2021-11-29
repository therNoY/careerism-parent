package pers.mihao.common.util;

import java.util.Scanner;
import pers.mihao.common.util.log.Log;

public class ScanUtil {

    public static void main(String[] args) {
        ScanUtil.runInThread(s -> {
            if (s.startsWith("w")) {
                synchronized (ScanUtil.class) {
                    Log.info("wait");
                    try {
                        ScanUtil.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                synchronized (ScanUtil.class) {
                    ScanUtil.class.notifyAll();
                }
            }
            Log.info("done");
        });
    }


    public static void run(MyRun runnable) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入数据....");

        String s = scanner.nextLine();
        while (!s.equals("-1")) {
            try {
                runnable.run(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("请输入数据....");
            s = scanner.nextLine();
        }
    }

    public static void runFor(MyRun runnable) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入数据....");

        String s = scanner.nextLine();
        while (!s.equals("-1")) {
            try {
                if (s.contains(" ")) {
                    String[] infos = s.split(" ");
                    for (int i = 0; i < Integer.valueOf(infos[0]); i++) {
                        runnable.run(infos[1] + "_" + i);
                    }
                } else {
                    runnable.run(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("请输入数据....");
            s = scanner.nextLine();
        }
    }

    public static void runInThread(MyRun runnable) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入数据....");

        String s = scanner.nextLine();
        while (!s.equals("-1")) {
            if (s.length() > 0) {
                String finalS = s;
                new Thread(() -> {
                    try {
                        runnable.run(finalS);
                    } catch (Exception e) {
                        Log.error(e);
                    }
                }, "Thread_" + finalS).start();
                System.out.println("请输入数据....");
            }
            s = scanner.nextLine();
        }
    }

    public interface MyRun {

        void run(String s) throws Exception;
    }

}




