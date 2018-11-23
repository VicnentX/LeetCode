package leetcode.Number;

public class RecursionSubsets {

    static int cnt;

    public void RecSubsets(String soFar , String rest){
            if(rest.isEmpty()){
                System.out.println(soFar);
                cnt++;
            }else{
                //add to subset, remove from rest , recur
                RecSubsets(soFar + rest.substring(0 , 1), rest.substring(1));
                //don't add to subset , remove from rest , recur
                RecSubsets(soFar , rest.substring(1));
            }
    }

    public static void main(String[] args){
        RecursionSubsets rs = new RecursionSubsets();
//        rs.RecSubsets("" , "");
//        System.out.println(cnt);
        rs.RecSubsets("" , "abc");
        System.out.println(cnt);
    }

}
