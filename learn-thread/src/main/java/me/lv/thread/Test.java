package me.lv.thread;

/**
 * @author plume
 * @date 2022/4/8 15:49
 */
public class Test {
    public static void main(String[] args) {
        Aobing aobing = new Aobing();
        aobing.start();
        for (; ; ) {
            if (aobing.isFlag()) {
                System.out.println("....");
            }
        }
    }
}

class Aobing extends Thread {
    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag = " + flag);
    }
}
