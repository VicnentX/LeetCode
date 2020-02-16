package leetcode.concurrency;


/**
 * 目标：研究多线程下变量访问的不可见性
 *
 * 准备内容：
 *      准备两个线程
 *      定义一个成员变量
 *      开启两个线程 一个负责修改 一个负责读取
 */


public class VisibilityDemo01 {
    //main 方法作为主线程
    public static void main (String[] args) {
        //开启一个子线程
        MyThread01 t = new MyThread01();
        t.start();

        //主线程执行

        /**
         * ！！！！！这里就是我们创建子线程 有了这个子线程，当子线程的run方法还没有修改flag的值的时候
         * 我们 if (t.isFlag()) 这句话已经被运行的 那么flag就是false，然后不管flag什么时候变成true
         * 我们在这个while里面 每次 if (t.isFlag()) 读到的都是false
         * ！！！！因为我们工作内存会拷贝一份共享变量的内存副本，然后就一直使用它（如果这个变量没有特别说明是volatile）
         */
        while (true) {
            if (t.isFlag()) {
                System.out.println("主线程进入循环执行");
            }
        }
    }
}

class MyThread01 extends Thread {
    //
    private boolean flag = false;
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 触发修改flag
        flag = true;
        System.out.println("flag=" + flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}


