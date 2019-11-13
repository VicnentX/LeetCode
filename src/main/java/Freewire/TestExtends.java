package Freewire;

public class TestExtends {
    public static class A {
        private int val = 100;
        protected int getVal() {
            return val;
        }

        protected String getString() {
            return "helloA";
        }
    }

    public static class B extends A {
        public int val = 10;
        public int val_b = 20;
        //if there is not override here, getVal will call the method in class A
        public int getVal() {
            return val;
        }

        public int getValSquare() {
            return val * val;
        }

        public String getString() {
            return "helloB";
        }
    }

    public static void main(String[] args) {
        A a = new B();


        System.out.println(a.getString());


        //regard B as A so .val is 100
        System.out.println(a.val);

        //can not .val_b because B is cast to A now, but we can cast from A to B
        System.out.println(((B) a).val_b);
        //override
        System.out.println(a.getVal());

        //getValSquare is only in the B , is not override.
        // so only cast to B then it can call getValSquare()
        System.out.println(((B) a).getValSquare());
    }
}
