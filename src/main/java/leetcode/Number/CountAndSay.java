package leetcode.Number;

public class CountAndSay {
    public String countSay(int n) {

//        if(n<=0){
//            return "";
//        }
//
//        String s="1";
//        for(int row=2;row<=n;row++){
//            StringBuffer sb=new StringBuffer();
//            int cnt=1;
//            for(int i=1;i<s.length();i++){
//                if(s.charAt(i)==s.charAt(i-1)){
//                    cnt++;
//                }else{
//                    sb.append(cnt).append(s.charAt(i-1));
//                    cnt=1;
//                }
//            }
//            sb.append(cnt).append(s.charAt(s.length()-1));
//            s=sb.toString();
//        }
//
//        return s;



        if(n == 1){
            return "1";
        }
        //递归调用，然后对字符串处理
        String str = countSay(n-1) +"*";//为了str末尾的标记，方便循环读数
        char[] c = str.toCharArray();
        int count = 1;
        String s = "";
        for(int i = 0; i < c.length - 1;i++){
            if(c[i] == c[i+1]){
                count++;//计数增加
            }else{
                s = s + count + c[i];//上面的*标记这里方便统一处理
                count = 1;//初始化
            }
        }
        return s;

    }






    public static void main(String[] args){
        CountAndSay cs=new CountAndSay();
        System.out.println(cs.countSay(5));
    }
}
