package Lintcode;

import java.util.*;

public class ChooseSeats {

    int[] ret;
    PriorityQueue<int[]> maxHeap;
    int len;
    HashMap<Integer , Integer> map;

    public int[] comeAndSit(int totalSeat , int[] personStream){
        //maxHeap = new PriorityQueue<>((a,b) -> a[1] - a[0] == b[1] - b[0] ? a[0] - b[0] : (b[1] - b[0]) - (a[1] - a[0]));
        maxHeap = new PriorityQueue<>((a,b) -> Math.abs((a[1] - a[0]) - (b[1] - b[0])) > 2 ? (b[1] - b[0]) - (a[1] - a[0]) :
                a[1] - a[0] == b[1] - b[0] ? a[0] - b[0] :
                Math.min(a[1] - a[0] , b[1] - b[0]) % 2 == 1 ? a.length - b.length : b.length - a.length);
        //initialize the maxheap
        maxHeap.add(new int[]{0 , totalSeat - 1});
        //need a map to record who is in which seat
        map = new HashMap<>();
        //
        len = personStream.length;
        ret = new int[len];
        for(int i = 0 ; i < len ; ++i){
            addPerson(i , personStream[i] , totalSeat);
        }
        return ret;
    }

    private void addPerson(int i , int k , int totalSeat){//i = index and k = value of the personStream array
        if(k > 0){
            if(maxHeap.isEmpty()){
                ret[i] = -1;//题目要求是1 但是范例给的-1
                return ;
            }

            int[] tem = maxHeap.poll();
            if(tem[0] == 0){
                ret[i] = 0;
                map.put(k , 0);
                if(tem[1] > 0){
                    maxHeap.add(new int[]{1 , tem[1]});
                }
                return ;
            }
            if(tem[1] == totalSeat - 1){
                ret[i] = totalSeat - 1;
                map.put(k , totalSeat - 1);
                if(tem[0] < totalSeat - 1){
                    maxHeap.add(new int[]{tem[0] , totalSeat - 2});
                }
                return ;
            }
            int seat = tem[0] + (tem[1] - tem[0])/2;
            ret[i] = seat;
            map.put(k , seat);
            if(seat > tem[0]){
                maxHeap.add(new int[]{tem[0] , seat - 1});
            }
            if(tem[1] > seat){
                maxHeap.add(new int[]{seat + 1 , tem[1]});
            }

        }else{//i < 0
            if(map.containsKey(-k)){
                ret[i] = 1;
                int seat = map.get(-k);
                map.remove(-k);

                int start = 0;
                int end = 0;

                PriorityQueue<int[]> copy = maxHeap;
                while(!copy.isEmpty()){
                    int[] tem = copy.poll();
                    if(tem[0] == seat + 1){
                        end = tem[1];
                        maxHeap.remove(tem);
                    }
                    else if(tem[1] == seat - 1){
                        start = tem[0];
                        maxHeap.remove(tem);
                    }
                }
                maxHeap.add(new int[]{start , end});
            }else{//set does not contain -i
                ret[i] = -1;
            }
        }
    }

    public static void main(String[] args){
        ChooseSeats cs = new ChooseSeats();

        System.out.println("example 1");
        int[] result = cs.comeAndSit(10 , new int[]{1,2,3,4,-3,5});
        for(int k : result){
            System.out.print(k + " ");
        }
        System.out.println();

        System.out.println("example 2");
        int[] result2 = cs.comeAndSit(2 , new int[]{1,2,3,-4});
        for(int k : result2){
            System.out.print(k + " ");
        }
        System.out.println();

    }

}
