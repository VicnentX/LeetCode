package leetcode.HashTable;

//这里如果map1 = map2 这样写的话  就等于两个map联动了 所以要写成 HashMap<Character , Integer> tem = new HashMap<>(map);
//但这种方法比较慢


public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) return false;
//        HashMap<Character , Integer> map = new HashMap<>();
//        for(char c : s1.toCharArray()){
//            map.put(c , map.getOrDefault(c , 0) + 1);
//        }
//        int m = s1.length();
//        int n = s2.length();
//        // for(int i = 0 ; i < m ; )
//        OUT:
//        for(int i = 0 ; i <= n - m ; ++i){
//            HashMap<Character , Integer> tem = new HashMap<>(map);
//            System.out.println(tem.equals(map));
//            for(int j = i ; j < i + m ; ++j){
//                if(tem.containsKey(s2.charAt(j))){
//                    tem.put(s2.charAt(j) , tem.get(s2.charAt(j)) - 1);
//                    if(tem.get(s2.charAt(j)) < 0) continue OUT;
//                }else{
//                    continue OUT;
//                }
//            }
//            return true;
//        }
//        return false;

        //方法二 每次修改一位
        int m = s1.length();
        int n = s2.length();

        if(s1.length() > s2.length()) return false;

        int[] array = new int[26];
        for(char ch : s1.toCharArray()){
            ++array[ch - 'a'];
        }
        for(int i = 0 ; i < m ; ++i){
            --array[s2.charAt(i) - 'a'];
        }

        for(int i = 0 ; i < n - m ; ++i){
            if(allZero(array))  return true;
            ++array[s2.charAt(i) - 'a'];
            --array[s2.charAt(i + m) - 'a'];
        }
        return allZero(array)? true : false ;
    }

    private boolean allZero(int[] a){
        for(int i = 0 ; i < a.length ; ++i){
            if(a[i] != 0) return false;
        }
        return true;
    }

    public static void main(String[] args){
        PermutationInString pi = new PermutationInString();
        System.out.println(pi.checkInclusion("ab" , "eidbaooo"));
    }
}
