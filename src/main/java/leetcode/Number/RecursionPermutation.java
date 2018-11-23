package leetcode.Number;

public class RecursionPermutation {

    static int cnt = 0;

    public void recPermute(String soFar , String rest){
        if(rest.equals("")){
            if(!soFar.isEmpty()){
                System.out.println(soFar);
                ++cnt;
            }
        }else{
            for(int i = 0; i < rest.length() ; ++i){
                String next = soFar + rest.substring(i , i + 1);
                String remain = rest.substring(0 , i) + rest.substring(i + 1);
                recPermute(next , remain);
            }
        }
    }

    public static void main(String[] args){
        RecursionPermutation rp = new RecursionPermutation();
        rp.recPermute("" , "");
        System.out.println("总的种类数量：" + cnt);
        rp.recPermute("" , "ABCDE");
        System.out.println("总的种类数量：" + cnt);
    }
}
