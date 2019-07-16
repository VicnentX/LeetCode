package TestShangXueTang.TestOverrideMethods;

public class TestEquals {
    public static void main(String[] args) {
        Cat c1 = new Cat(1, 2);
        Cat c2 = new Cat(1, 2);
        System.out.println(c1 == c2);
        System.out.println(c1.equals(c2));
    }
}

class Cat {
    int color, weight;

    public Cat(int color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        else {
            if (obj instanceof Cat) {
                Cat c = (Cat) obj;
                if (c.color == this.color
                && c.weight == this.weight) {
                    return true;
                }
            }
        }
        return false;
    }
}
