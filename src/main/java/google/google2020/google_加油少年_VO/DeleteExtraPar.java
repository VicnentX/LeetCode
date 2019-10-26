package google.google2020.google_加油少年_VO;

/*
((aa)) invalid
((aa)()(aa)) invalid

(aa)(aa) valid
((aa)(aa)) valid
((aa)f(aa)) valid
aa(aa)(aa) valid
(((fd)(fd))(fd)) valid
 */

import java.util.Stack;

public class DeleteExtraPar {

    public String removeExtraPar(String s) {
        StringBuilder sb = new StringBuilder(s);
        //index i and cnt in this par (left par index is i)
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {-1,0});
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                int[] cur = new int[2];
                cur[0] = i;
                stack.push(cur);
            } else if (c == ')') {
                int[] cur = stack.pop();
                int cnt = cur[1];
                boolean isValid = cnt > 1;
                int leftParIndex = cur[0];
                if (!isValid) {
                    sb.setCharAt(leftParIndex, '#');
                    sb.setCharAt(i, '#');
                } else {
                    stack.peek()[1] += 1;
                }
            } else {
                //这里相当于在这一层加了字母，所以我+2之后他的cnt肯定大于1 就一定valid了
                //加别的数字也是一样的
                stack.peek()[1] += 2;
            }
        }
        //get ret
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < sb.length(); ++i) {
            ret.append(sb.charAt(i) == '#' ? "" : sb.charAt(i));
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        DeleteExtraPar deleteExtraPar = new DeleteExtraPar();
        //invalid
        System.out.println(deleteExtraPar.removeExtraPar("((aa))"));
        //invalid
        System.out.println(deleteExtraPar.removeExtraPar("((aa)()(aa))"));
        //valid
        System.out.println(deleteExtraPar.removeExtraPar("(aa)(aa)"));
        //valid
        System.out.println(deleteExtraPar.removeExtraPar("((aa)(aa))"));
        //valid
        System.out.println(deleteExtraPar.removeExtraPar("((aa)f(aa))"));
        //valid
        System.out.println(deleteExtraPar.removeExtraPar("aa(aa)(aa)"));
        //valid
        System.out.println(deleteExtraPar.removeExtraPar("(((fd)(fd))(fd))"));
    }

}
