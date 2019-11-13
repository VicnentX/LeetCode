package bloomreach;

public class Modifier {
    void fun() {
        int i = 0;
        // 后面这样写是错误的static int i = 0;
        //局部变量前不能放置任何访问修饰符 (private，public，和protected)。final可以用来修饰局部变量
        //int i = 0;
        i++;
        System.out.println(i);// 分别打印输出1
    }

    public static void main(String args[]) {
        Modifier obj = new Modifier();
        obj.fun();
        obj.fun();
    }
}
