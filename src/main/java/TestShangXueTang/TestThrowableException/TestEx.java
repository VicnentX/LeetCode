package TestShangXueTang.TestThrowableException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestEx {

    public static void main(String[] args) {
        //this is the exception that we could catch or not.
        try{
            new TestEx().m(0);
        }catch (ArithmeticException ae) {
            ae.printStackTrace();
            System.out.println("dividend is 0, this is exception");
        }

        //io.expection must be catch!!!!!!!!!!!!!!!!!!!
        FileInputStream file = null;
        try {
            file = new FileInputStream("myfile.txt");
            int b;
            b = file.read();
            while (b != -1) {
                System.out.println((char)b);
                b = file.read();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                file.close();
                System.out.println("file is close now");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void m(int i) throws ArithmeticException {
        throw new ArithmeticException("dividend is 0");
    }
}
