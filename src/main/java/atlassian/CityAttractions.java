package atlassian;

import java.util.*;

public class CityAttractions {


    //自定义一个类；因为直接用int[] 作为hashmap的key是不可以的
//    class IntPair{
//        private int a;
//        private int b;
//        public IntPair(int a , int b){
//            this.a = a;
//            this.b = b;
//        }
//        @Override
//        public boolean equals(Object obj){
//            if(this == obj){
//                //判断是否是本类的一个引用
//                return true;
//            }
//            if(obj == null){
//                return false;
//            }
//            IntPair pair = (IntPair)obj;
//            if(this.a != pair.a){
//                return false;
//            }
//            if(this.b != pair.b){
//                return false;
//            }
//            return true;
//        }
//        @Override
//        public int hashCode(){
//            int result = 17;
//            result = result * 31 + a;
//            result = result * 31 + b;
//            return result;
//        }
//    }


    public class KeyArray {

        Object[] internalArray;

        KeyArray(Object[] arr) {
            internalArray = arr;
        }

        @Override
        public boolean equals(Object o) {
            /* 谨慎地编写equals方法，这里的代码没有考虑所有的情况 */
            if (o != null) {
                if (o instanceof KeyArray) {
                    /* Arrays.equals会对两个数组进行基于值的比较 */
                    return Arrays.equals(internalArray, ((KeyArray) o).internalArray);
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            /* Arrays.deepHashCode 会根据数组内的元素的值计算 hashCode */
            return Arrays.deepHashCode(internalArray);
        }
    }


    //
    //全局变量 也是最后的结果
    int maxValue = 0;
    //map的key存的是一个点 value是所有和这个点链接的点的集合
    HashMap<Integer , HashSet<Integer>> map = new HashMap<>();
    //map的key是有关联的两个点 value是他们之间的距离
    HashMap<KeyArray , Integer> timeConsume = new HashMap<>();

    //u[i] vs v[i] means have path between them , timeCost[i] means its cost , and beautyVar[i] means it's beauty
    //and t is the max time limit
    public int findBestBeauty(int[] u , int[] v , int[] timeCost , int[] beautyVar ,  int totalTime){

        int m = u.length;
        //int[] 不能作为key 因为hashcode的关系
        for(int i = 0 ; i < m ; ++i){
            if(!map.containsKey(u[i])){
                map.put(u[i] , new HashSet<>());
            }
            map.get(u[i]).add(v[i]);
            if(!map.containsKey(v[i])){
                map.put(v[i] , new HashSet<>());
            }
            map.get(v[i]).add(u[i]);
            //build the timeConsume map
            timeConsume.put(new KeyArray(new Object[]{u[i],v[i]}), timeCost[i]);
            timeConsume.put(new KeyArray(new Object[]{v[i],u[i]}), timeCost[i]);
        }
        helper(beautyVar , totalTime , 0 , beautyVar[0] , new HashSet<Integer>());
        return maxValue;
    }
    private void helper(int[] beautyVar , int timeRemain , int node , int getBeauty , HashSet<Integer> visited){

        visited.add(node);

        if(node == 0 && getBeauty > maxValue){
            maxValue = getBeauty;
        }

        for(int k : map.get(node)){

            if(timeRemain - timeConsume.get(new KeyArray(new Object[]{node , k})) < 0){
                if(getBeauty > maxValue && node == 0){
                    maxValue = getBeauty;
                }
                return ;
            }
            //timeRemain > 0
            else{
                if(!visited.contains(k)){
                    helper(beautyVar , timeRemain - timeConsume.get(new KeyArray(new Object[]{node , k})) , k , getBeauty + beautyVar[k] , visited);
                    visited.remove(k);//这里退回来之后 把加如visited的再减去。
                }
                else{//visited.contains(k)
                        helper( beautyVar , timeRemain - timeConsume.get(new KeyArray(new Object[]{node , k})) , k , getBeauty , visited);
                        //这里不减的原因是此时k在里面是之前就有的 并不是上面这个循环进入后102行所做的。
                }
            }
        }
    }

    public static void main(String[] args){
        CityAttractions ca = new CityAttractions();
        System.out.println("example 1");
        int[] u = new int[]{0,1,0};
        int[] v = new int[]{1,2,3};
        int[] timeCost = new int[]{6,7,10};
        int[] beautyVar = new int[]{5 , 10 , 15 , 20};
        int totalTime = 30;
        System.out.println(ca.findBestBeauty(u , v , timeCost , beautyVar , totalTime));

        System.out.println("example 2");
        int[] u1 = new int[]{0,1,0,1};
        int[] v1 = new int[]{1,2,3,3};
        int[] timeCost1 = new int[]{6,7,10,2};
        int[] beautyVar1 = new int[]{5 , 10 , 15 , 20};
        int totalTime1 = 30;
        System.out.println(ca.findBestBeauty(u1 , v1 , timeCost1 , beautyVar1 , totalTime1));

    }
}
