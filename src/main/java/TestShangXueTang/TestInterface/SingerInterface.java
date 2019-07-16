package TestShangXueTang.TestInterface;

interface Singer {
    public void sing();
    public void sleep();
}

interface Painter {
    public void paint();
    public void eat();
}

class Student implements Singer {
    private String name;
    Student(String name) {
        this.name = name;
    }
    public void study() {
        System.out.println("student studying");
    }
    public String getName() {
        return name;
    }
    public void sing() {
        System.out.println("a student is singing");
    }
    public void sleep() {
        System.out.println("a student is sleeping");
    }
}

class Teacher implements Singer, Painter {
    private String name;
    public String getName() {
        return name;
    }
    Teacher(String name) {
        this.name = name;
    }
    public void teach() {
        System.out.println("a teacher is teaching");
    }
    public void sing() {
        System.out.println("a teacher is singing");
    }
    public void sleep() {
        System.out.println("a teacher is sleeping");
    }
    public void paint() {
        System.out.println("a teacher is painting");
    }
    public void eat() {
        System.out.println("a teacher is eating");
    }
}

public class SingerInterface {

    public static void f(Singer s) {
        s.sing();
    }

    public static void main(String[] args) {
        Singer s1 = new Student("xia");
        s1.sing();
        s1.sleep();
        Singer s2 = new Teacher("wang");
        s2.sleep();
        s2.sing();
        ((Teacher) s2).teach();
        ((Teacher) s2).sleep();
        System.out.println("--------");
        f(s1);
        f(s2);
    }
}
