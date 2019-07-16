package TestShangXueTang.TestOverrideMethods;

class Person {
    private String name;
    private int age;
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getInfo() {
        return "Name : " + name + "\n" + "age : " + age;
    }
}

class Student extends Person {
    private String school;
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getInfo() {
        return "Name : " + getName() + "\n" + "age : " + getAge() + "\n" + "school : " + school;
    }
}

public class TestOverride {
    public static void main(String[] args) {
        Student student = new Student();
        Person person = new Person();
        person.setName("none");
        person.setAge(1000);
        student.setName("joe");
        student.setAge(18);
        student.setSchool("scu");
        System.out.println(person.getInfo());
        System.out.println(student.getInfo());
    }
}
