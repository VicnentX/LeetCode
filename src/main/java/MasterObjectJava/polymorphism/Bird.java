package MasterObjectJava.polymorphism;

public class Bird extends Animal{

    public static int age = 1;

    public Bird(int age) {
        super(age);
        System.out.println(super.age);
    }

    public void move() {
        System.out.println("bird is moving");
    }

    public void fly() {
        System.out.println("i am flying");
    }

    public static void main(String[] args) {

        Animal animal = new Bird(1);

        Bird bird = new Bird(1);
        System.out.println(age);
        bird.sleep();

//        Animal animal = new Animal();
    }
}



