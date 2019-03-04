package leetcode;



public class TestCircle {
    static class Point {
        double x , y;
        public Point(double x1 , double y1) {
            x = x1;
            y = y1;
        }
    }

    static class Circle {
        private Point o;
        private double radius;
        Circle(Point p , double r) {
            o = p;
            radius = r;
        }
        Circle(double r) {
            o = new Point(0.0 , 0.0);
            radius = r;
        }
        boolean contains(Point p) {
            double x = p.x - o.x;
            double y = p.y - o.y;
            if (x * x + y * y <= radius * radius) return true;
            return false;
        }
    }
    public static void main (String[] args) {
        Point p1 = new TestCircle.Point(1.0,5.0);
        Circle c1 = new Circle(new Point(1,1) , 3);
        System.out.println(c1.contains(p1));
        Circle c2 = new Circle(new Point(1,1) , 7);
        System.out.println(c2.contains(p1));
//        TestCircle tc = new TestCircle();
//        tc.Point
    }
}


