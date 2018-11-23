package atlassian;

public class MaximumStreak {
    public int getMaxStreak(int NumOfEmployee , int NumOfdays , String[] data){
        int cnt = 0;
        int max = 0;
        OUT:
        for(String k : data){
            for(Character c : k.toCharArray()){
                if(c.equals('N')){
                    cnt = 0;
                    continue OUT;
                }
            }
            ++cnt;
            if(cnt > max){
                max = cnt;
            }
        }
        return max;
    }

    public static void main(String[] args){
        MaximumStreak ms = new MaximumStreak();
        System.out.println(ms.getMaxStreak(3,5,new String[]{
            "YYY" , "YYY" , "YNN" , "YYN" , "YYN"
        }));
    }
}
