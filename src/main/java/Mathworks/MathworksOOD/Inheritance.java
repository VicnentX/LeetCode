package Mathworks.MathworksOOD;


class Person {
    private String name;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

class Student extends Person {
    private String school;

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchool() {
        return school;
    }
}

class Parent {
    private int n_private = 1;
    int n_friendly = 2;
    protected int n_protected = 3;
    public int n_public = 4;
}

class Child extends Parent {
    public void f() {
        n_protected = n_protected + 1;
        n_public = 5;
        n_friendly = n_friendly + 2;
    }
}

public class Inheritance {
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.getName());
    }

}
