package intuit;

import java.util.*;

//input[i][0] parent
//input[i][1] child
/*
the input can construct a graph with a lot of generation
map<the current number , set<this current number's parents>>
input[][0] parent
input[][1] child

input[][0] , new set;
child , add input[][0] as parent

scan the map and the value 's size <= 1 means has one or no parents         then    add to ret
done


 */

public class ParentsAndChildren {
    //time On
    //space On
    HashMap<Integer , HashSet<Integer>> map;
    //question 1
    public List<Integer> childrenWith0Or1Parents(int[][] a){
        List<Integer> ret =new ArrayList<>();
        map = new HashMap<>();
        for(int[] k : a){
            if(!map.containsKey(k[0])){
                map.put(k[0] , new HashSet<>());
            }
            if(!map.containsKey(k[1])) map.put(k[1] , new HashSet<>());
            map.get(k[1]).add(k[0]);
        }
        for(int k : map.keySet()){
            if(map.get(k).size() <= 1){
                ret.add(k);
            }
        }
        return ret;
    }

    //question 2
    //time On
    //space On
    /*
    i will scan level by level  that means
    for example
    use three set for a , b , total

    a 's parents then i will check the total set ,
    a's one parent is not in the total set ；
    if a's one parent already in the total set   then return true
    b's .........

    at last if no dulplicate

     */
    public boolean commonAncestor(int a , int b){
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        HashSet<Integer> totalSet = new HashSet<>();
        for(int k : map.get(a)){
            set1.add(k);
        }
        for(int k : map.get(b)){
            set2.add(k);
        }
        while(!set1.isEmpty() || !set2.isEmpty()){
            if(!set1.isEmpty()){
                HashSet<Integer> tem1 = new HashSet<>();
                for(int k : set1){
                    if(!totalSet.add(k)) return true;
                    tem1.addAll(map.get(k));
                }
                set1 = tem1;
            }
            if(!set2.isEmpty()){
                HashSet<Integer> tem2 = new HashSet<>();
                for(int k : set2){
                    if(!totalSet.add(k)) return true;
                    tem2.addAll(map.get(k));
                }
                set2 = tem2;
            }
        }
        return false;
    }

    //question 3
    //time On
    //space On
    public int findFarestAncestor(int a){
        HashSet<Integer> set = new HashSet<>();
        set.addAll(map.get(a));
        while(!set.isEmpty()){
            HashSet<Integer> tem = new HashSet<>();
            for(int k : set){
                tem.addAll(map.get(k));
            }
            if(tem.isEmpty()){
                return set.iterator().next();
            }
            set = tem;
        }
        return -1;//meas no ancestor
    }



    public static void main(String[] args){
        ParentsAndChildren pc = new ParentsAndChildren();
        List<Integer> result = pc.childrenWith0Or1Parents(new int[][]{{1,4},{1,5},{2,5},{3,6},{6,7},{0,1},{0,3}});
        for(int k : result){
            System.out.print(k + " ");
        }
        System.out.println();
        System.out.println(pc.commonAncestor(7,4));
        System.out.println(pc.findFarestAncestor(0));
    }
}
