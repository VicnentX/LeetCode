package TestShangXueTang;

class SuperClass {
    private int n;

    SuperClass() {
        System.out.println("this is superclass");
    }

    SuperClass(int n) {
        System.out.println("this is superclass : " + n);
        this.n = n;
    }
}

class SubClass extends SuperClass {
    private int n;

    SubClass() {
        super();
        System.out.println("SubClass()");
    }

    SubClass(int n) {
        System.out.println("subclass : " + n);
        this.n = n;
    }
}

public class SuperSubClass {
    public static void main(String[] args) {
        SubClass s1 = new SubClass();
        SubClass s2 = new SubClass(20);
    }
}
