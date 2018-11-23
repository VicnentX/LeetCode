package leetcode.Number;

public class BullsAndCows {

        public String getHint(String secret, String guess) {

            int[] m=new int[]{0,0,0,0,0,0,0,0,0,0};//第二个【】里面不要写数字
            int bull=0;
            int cow=0;

            for(int i=0;i<secret.length();i++){
                if(secret.charAt(i)==guess.charAt(i)){
                    bull++;
                }
                else{
                    if(m[secret.charAt(i)-'0']++<0){cow++;}
                    if(m[guess.charAt(i)-'0']-->0){cow++;}  //guess.charAt(i)-'0'就把提取出来的char变成数字了
                }
            }

            return bull+"A"+cow+"B";


        }

        public static void main(String[] args){
            BullsAndCows bc=new BullsAndCows();
            System.out.println(bc.getHint("23456","65432"));
    }
}
