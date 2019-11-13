package google.google2020.google_加油少年_OnS;

/*
给两个字符串 其中都包含一些/b 代表删除前一个有效字符 输出这两个字符串是否相等
 */

public class SameString {

    public boolean isSame(String s, String t) {
        return s.replace("/b","").equals(t.replace("/b",""));
    }

    public static void main(String[] args) {
        SameString sameString = new SameString();
        System.out.println(sameString.isSame("ll/bjj/bdd","lljjdd"));
    }

}
