package google.google2020.google_加油少年_VO;

/*
Given an input file that contains multiple text lines,
generate an output file that contains the same number of text
lines but in a reversed order.
 */

/*
用文件映射放到内存的一段buffer,然后指针就在buffer中从后往前读写新文件.
看Windows核心编程的内存映射文件拿一章.你会发现其他的办法都不是啥好办法.
 */

/*
如果没有socket方面的应用的话,建议用RandomAccssFile的seek(long pose),
非常容易实现,RandomAccssFile是java公认的较好操作本地文件的IO类.
 */


/*
store the pointer for every line
 */

import java.io.*;


public class OutputFileReversedOrder {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/Vincent_Xia/IdeaProjects/LeetCode/src/main/java/google/google2020/google_加油少年_VO/outputFileReversedOrderInputFiel");
        BufferedReader in = new BufferedReader(new InputStreamReader(new ReverseLineInputStream(file)));

        while(true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            System.out.println("X:" + line);
        }
    }

    public static class ReverseLineInputStream extends InputStream {

        RandomAccessFile in;

        long currentLineStart = -1;
        long currentLineEnd = -1;
        long currentPos = -1;
        long lastPosInFile = -1;

        public ReverseLineInputStream(File file) throws FileNotFoundException {
            in = new RandomAccessFile(file, "r");
            currentLineStart = file.length();
            currentLineEnd = file.length();
            lastPosInFile = file.length() -1;
            currentPos = currentLineEnd;
        }

        public void findPrevLine() throws IOException {

            currentLineEnd = currentLineStart;

            // There are no more lines, since we are at the beginning of the file and no lines.
            if (currentLineEnd == 0) {
                currentLineEnd = -1;
                currentLineStart = -1;
                currentPos = -1;
                return;
            }

            long filePointer = currentLineStart -1;

            while ( true) {
                filePointer--;

                // we are at start of file so this is the first line in the file.
                if (filePointer < 0) {
                    break;
                }

                in.seek(filePointer);
                int readByte = in.readByte();

                // We ignore last LF in file. search back to find the previous LF.
                if (readByte == 0xA && filePointer != lastPosInFile ) {
                    break;
                }
            }
            // we want to start at pointer +1 so we are after the LF we found or at 0 the start of the file.
            currentLineStart = filePointer + 1;
            currentPos = currentLineStart;
        }

        public int read() throws IOException {

            if (currentPos < currentLineEnd ) {
                in.seek(currentPos++);
                int readByte = in.readByte();
                return readByte;

            }
            else if (currentPos < 0) {
                return -1;
            }
            else {
                findPrevLine();
                return read();
            }
        }
    }
}
