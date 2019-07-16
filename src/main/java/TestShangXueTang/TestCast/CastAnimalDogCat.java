package TestShangXueTang.TestCast;

class Animal {
    public String name;
    Animal(String name) {
        this.name = name;
    }
}

class Cat2 extends Animal {
    public String eyesColor;
    Cat2(String n, String c) {
        super(n);
        eyesColor = c;
    }
}

class Dog2 extends Animal {
    public String furColor;
    Dog2(String n, String c) {
        super(n);
        furColor = c;
    }
}

public class CastAnimalDogCat {
    public static void main(String[] args) {
        Animal a = new Animal("name");
        Cat2 c = new Cat2("catName", "blue");
        Dog2 d = new Dog2("dogName", "black");

        System.out.println(a instanceof Animal);
        System.out.println(c instanceof Animal);
        System.out.println(d instanceof Animal);
        System.out.println(a instanceof Cat2);

        a = new Dog2("bigyellow", "yellow");
        System.out.println(a instanceof Animal);
        System.out.println(a instanceof Dog2);
        //System.out.println(a.furcolor); a是狗 但是只能看到animal的属性
        Dog2 d1 = (Dog2)a;
        System.out.println(d1.furColor);
    }
}
