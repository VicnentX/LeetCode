package TestShangXueTang.TestAbstract;

abstract class Animal{
    private String name;
    Animal(String name) {
        this.name = name;
    }
    public abstract void enjoy();
}

class Cat extends Animal {
    private String eyecol;
    Cat(String name, String eyecol) {
        super(name);
        this.eyecol = eyecol;
    }
    public void enjoy() {
        System.out.println("cat miao miao");
    }
}

class lady {
    private String name;
    private Animal pet;
    lady(String n , Animal pet) {
        this.name = n;
        this.pet = pet;
    }
    public void myPetEnjoy() {
        pet.enjoy();
    }
}

public class AbstractClassImplement {
    public static void main(String[] args) {
        Cat c = new Cat("nvnv", "blue");
        lady l = new lady("prince", c);
        l.myPetEnjoy();
    }
}
