package leetcode.Number;

import java.util.*;




public class AsteroidCollision {

        public int[] asteroidCollision(int[] a) {
//方法一

//         LinkedList<Integer> s=new LinkedList<>();
//         for(int i:a){
//             if(i>0){
//                 s.add(i);  //把一个元素加入linkedlist里面用add
//             }else{
//                 while(!s.isEmpty() && s.getLast()>0 && s.getLast()<-i){//linkedlist的选取元素方法 s.getLast就能取出最后那个数字！
//                     s.pollLast();  //删掉最后一个元素
//                 }
//
//                 if(!s.isEmpty() && s.getLast()==-i){
//                     s.pollLast();
//                 }else if (s.isEmpty() || s.getLast()<0){
//                     s.add(i);
//                 }
//             }
//         }
//         return s.stream().mapToInt(i->i).toArray();    //这一段把linkedlist变成int[]  ....(i->-i)所有的数字加一个负号

// //方法二
            Stack<Integer> re=new Stack<>();

            for(int i=0;i<a.length;i++){

                if(a[i]>0){
                    re.push(a[i]);
                }else{
                    while(!re.isEmpty() && re.peek()>0 && re.peek()<-a[i]){
                        re.pop();
                    }

                    if(!re.isEmpty() && re.peek()==-a[i]){
                        re.pop();
                    }else if(re.isEmpty() || re.peek()<0){
                        re.push(a[i]);
                    }
                }
            }

            int[] out=new int[re.size()];   //一个stack里面有多少个元素 用size（）就可以。

            for(int i=out.length-1;i>=0;i--){   //因为先pop出来的是在尾巴上的 所以翻过来for
                out[i]=re.pop();
            }

            return out;

        }

    public static void main(String[] args){
        AsteroidCollision ac=new AsteroidCollision();
        System.out.println(Arrays.toString(ac.asteroidCollision(new int[] {2,6,-5,5,-7,6,-3})));
    }
}
