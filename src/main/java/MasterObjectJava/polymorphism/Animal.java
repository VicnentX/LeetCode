package MasterObjectJava.polymorphism;

public abstract class Animal {

    public int age = 0;
    private String category = "hahaha";

    public Animal() {
        super();
        System.out.println("contruct of default");
    }

    public Animal(int age) {
        super();
        System.out.println("constrcut of age");
    }

    private void eat() {
        System.out.println("i am eating");
    }

    public void sleep() {
        eat();
        System.out.println(category);
        System.out.println("i am sleeping");
    }

    public abstract void move();
}
