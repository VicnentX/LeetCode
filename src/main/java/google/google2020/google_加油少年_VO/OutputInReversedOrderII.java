package google.google2020.google_加油少年_VO;

/*
给了一个函数read_from_file_at_pos(pos)可以调用，
it reads from a file at pos in byte,
and returns N bytes of the data contained in the file
还是generate an output file that contains the same number
of text lines but in a reversed order.
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputInReversedOrderII {

    public static byte[] read_from_file_at_pos(int pos) {
        return new byte[]{};
    }

    public static void main(String[] args) throws IOException {
        byte[] text = read_from_file_at_pos(7);
        int n = text.length;
        StringBuffer sb = new StringBuffer();

        //Get the file reference
        Path path = Paths.get("output.txt");
        BufferedWriter writer = Files.newBufferedWriter(path);


        //write lines in reversed order into output file
        for (int i = n - 1; i >= 0; --i) {
            String temp = new String(Byte.toString(text[i]));
            if (temp == "\n") {
                writer.write(sb.toString());
                sb = new StringBuffer();
            } else {
                sb.insert(0, temp);
            }
        }
    }
}
