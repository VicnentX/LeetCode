package TestShangXueTang.TestExtends;

class Person1 {
    private String name;
    private String location;

    Person1(String name) {
        this.name = name;
        location = "BJ";
    }

    Person1(String name , String location) {
        this.name = name;
        this.location = location;
    }

    public String info() {
        return "name: " + name + " location: " + location;
    }
}

class Teacher1 extends Person1 {
    private String capital;

    Teacher1(String n , String capital) {
        super(n);
        this.capital = capital;
    }

    public String info() {
        return super.info() + "\n" + "capital: " + capital;
    }
}

public class PersonTeacher {
    public static void main(String[] args) {
        Teacher1 t1 = new Teacher1("D", "professor");
        System.out.println(t1.info());
    }
}
