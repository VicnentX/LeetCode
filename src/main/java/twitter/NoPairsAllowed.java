package twitter;

public class NoPairsAllowed {
    public int[] replacepairs(int n , String[] s){
        int[] ret = new int[n];
        int index = 0;
        for(String k : s){
            int cnt = 0;
            for(int i = 1 ; i < k.length() ; ++i){
                if(k.charAt(i) == k.charAt(i - 1)){
                    ++cnt;
                    ++i;
                }
            }
            ret[index] = cnt;
            ++index;
        }
        return ret;
    }
    public static void main(String[] args){
        NoPairsAllowed np = new NoPairsAllowed();
        int[] ret = np.replacepairs(5, new String[]{"ab" , "aab" , "abb" , "abab" , "abaaaba"});
        for(int k : ret){
            System.out.println(k);
        }
    }
}
