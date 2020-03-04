package leetcode.concurrency.usefulVolatile;

public class VisibilityTrigger {
    int a = 1;
    int b = 2;
    int c = 3;
    volatile boolean flag = false;

    public void write() {
        a = 100;
        b = 200;
        c = 300;
        flag = true;    //当我改成true 之前abc的变化对别的线程都是可见的
    }

    public void read() {
        System.out.println("a, b, c = " + a + " " + b + " " + c);
    }

    public static void main(String[] args) {
        VisibilityTrigger vt = new VisibilityTrigger();
        new Thread(new Runnable() {
            @Override
            public void run() {
                vt.write();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                vt.read();
            }
        }).start();
    }
}
