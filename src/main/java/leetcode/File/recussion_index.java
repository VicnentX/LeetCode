package leetcode.File;

import java.io.*;

public class recussion_index {
    public static void main(String[] args) {
        File f = new File("/Users/Vincent_Xia/Downloads");
        dfs(f, 0);
    }

    private static void dfs(File f, int level) {
        String preStr = "";
        for (int i = 0; i < level; ++i) {
            preStr += "_";
        }

        System.out.println(preStr + f.getName());
        File[] children = f.listFiles();
        for (File child : children) {
            if (child.isDirectory()) {
                dfs(child, level + 1);
            }
        }
    }


}
