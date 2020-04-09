package MasterObjectJava.polymorphism;

public class Owl extends Bird implements Flyable{
    public Owl(int age) {
        super(age);
    }

    public static void main(String[] args) {
        Owl owl = new Owl(12);
        Flyable bowl1 = new Owl(1);
        bowl1.fly();
    }
}
