package Audible;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ParseRESTAPI {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String args[]) throws Exception {
// read the string filename
        String filename;
        filename = scan.nextLine();
//
        File inputFile = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String curLine;
        Set<String> validGIFFileSet = new HashSet<>();
//create output file
        String outputFileName = "gifs_" + filename;
        File outputFile = new File(outputFileName);

        while ((curLine = br.readLine()) != null) {
            String[] tokens = curLine.split(" ");
            int len = tokens.length;
            if (tokens[len - 2].equals("200")
                    && tokens[len - 5].equals("\"GET")) {
                String address = tokens[len - 4];
//System.out.println("address is " + address);
                String[] addressSplit = address.split("/");
                int splitLen = addressSplit.length;
                if (splitLen == 0) continue;
//System.out.println("splitLen is " + splitLen);
                String curFileAttribute = addressSplit[splitLen - 1];
                if (curFileAttribute.endsWith(".GIF")
                        || curFileAttribute.endsWith(".gif")) {
                    validGIFFileSet.add(curFileAttribute);
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
        for (String validGIFFileName: validGIFFileSet) {
            bw.write(validGIFFileName);
            bw.write("\n");
        }
        bw.close();
    }
}
