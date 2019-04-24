package Amazon.cv;

/*
print 1 -30 if it could be divided by 3 , add hello . if divided by 4 , add hello

ex:
1
2
3 hello
4 world
 */

public class print1to30 {
    public void printconsequence() {
        for (int i = 1 ; i <= 30 ; ++i) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            if (i % 3 == 0) {
                sb.append(" hello");
            }
            if (i % 4 == 0) {
                sb.append(" world");
            }
            System.out.println(sb.toString());
        }
    }

    public static void main (String[] args) {
        print1to30 pt = new print1to30();
        pt.printconsequence();
    }
}
