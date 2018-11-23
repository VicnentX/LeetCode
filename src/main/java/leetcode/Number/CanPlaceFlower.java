package leetcode.Number;

public class CanPlaceFlower {

//    public boolean canPlaceFlowers(int[] f, int n) {
//        if(n == 0) return true;
//
//        if(f.length == 1){
//            if(f[0] == 1 )
//                return false;
//            else if(n > 1)
//                return false;
//            else
//                return true;
//        }
//
//        if(f[0] == 0 && f[1] == 0){
//            --n;
//            f[0] = 1;
//        }
//
//        int i ;
//        for(i = 1; i < f.length - 1; ++i){
//            if(f[i - 1] == 0 && f[i + 1] == 0 && f[i] == 0){
//                --n;
//                f[i] = 1;
//            }
//        }
//
//        if(f[i] == 0 && f[i - 1] == 0){
//            --n;
//            f[i] = 1;
//        }
//
//        return n<=0? true : false;
//    }

    public boolean canPlaceFlowers(int[] bed, int n) {
        int ret = 0;
        int len = bed.length;
        int[] flow = new int[len + 2];
        flow[0] = bed[0];
        for(int i = 0 ; i < len ; ++i){
            flow[i + 1] = bed[i];
        }
        flow[len + 1] = bed[len - 1];
        int cnt = 0;
        for(int i = 0 ; i < len + 2 ; ++i){
            if(flow[i] == 0){
                ++cnt;
            }else{
                if(cnt > 0){
                    ret += (cnt - 1) / 2;
                    cnt = 0;
                }
            }
        }
        if(cnt > 0){
            ret += (cnt - 1) / 2;
        }
        return ret >= n;
    }

    public static void main(String[] args){
        CanPlaceFlower cpf = new CanPlaceFlower();
        //System.out.println(cpf.canPlaceFlowers(new int[]{1,0,1,0,1,0,1} , 1));
        System.out.println(cpf.canPlaceFlowers(new int[]{1,0,0,0,1} , 1));
    }

}
