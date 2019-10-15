package Amazon.intern_2019;

/*
given a string , to find the amount of SubstringwWithKDistinctCharacter
input ababc , 2
result is 7 : ab , aba , abab , ba , bab , ab , bc
 */




import java.util.HashMap;
import java.util.*;

public class CountSubstringwWithKDistinctCharacter {
    public int countSubtring(String s , int k) {
        int ret = 0;
        if (k == 0 || s.length() < k) return ret;
        //position is record the last position of each Character between start and end
        int[] position = new int[26];
        Arrays.fill(position , s.length());
        Map<Character , Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        while (end < s.length()) {
            //update map and position array
            char charAdd = s.charAt(end);
            map.put(charAdd , map.getOrDefault(charAdd , 0) + 1);
            position[charAdd - 'a'] = end;
            //calculate substring that ends with charAdd;
            if (map.size() < k) {
                //do nothing
            } else if (map.size() == k){//size == k
                int heapIndex = findFirstIndexFromPositionArray(position);
                ret += heapIndex - start + 1;
            } else { //size > k
                while (start < end && map.size() > k) {
                    char charDelete = s.charAt(start);
                    ++start;
                    map.put(charDelete , map.get(charDelete) - 1);
                    if (map.get(charDelete) == 0) {
                        map.remove(charDelete);
                        position[charDelete - 'a'] = s.length();
                        int heapIndex = findFirstIndexFromPositionArray(position);
                        ret += heapIndex - start + 1;
                        break;
                    }
                }
            }
            ++end;
        }
        return ret;
    }

    private int findFirstIndexFromPositionArray(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < 26 ; ++i) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        return min;
    }

    public int countSubtringO_n(String s , int k) {
        int ret = 0;
        int n = s.length();
        int[] dict = new int[256];
        int start = 0 , i = 0 , end = 0 , size = 0;
        for (end = 0 ; end < n ; ++end) {
            char c = s.charAt(end);
            ++dict[c];
            if (dict[c] == 1) {
                ++size;
            }
            //find i position;
            if (size == k) {
                while(dict[s.charAt(i)] > 1) {
                    --dict[s.charAt(i)];
                    ++i;
                }
                //update ret
                ret += (i - start + 1);
            }
            //move start and i
            if (size > k) {
                start = i + 1;
                --dict[s.charAt(i)];
                ++i;
                while(dict[s.charAt(i)] > 1) {
                    --dict[s.charAt(i)];
                    ++i;
                }
                --size;
                ret += (i - start + 1);
            }
        }
        return ret;
    }


    public static void main (String[] args) {
        CountSubstringwWithKDistinctCharacter cs = new CountSubstringwWithKDistinctCharacter();
        System.out.println(cs.countSubtring("ababc" , 2));
        System.out.println(cs.countSubtring("aaaa" , 1));
        System.out.println("________________________");
        System.out.println(cs.countSubtringO_n("ababc" , 2));
        System.out.println(cs.countSubtringO_n("aaaa" , 1));
    }
}






/*
想不到一个数据结构存储 pair（字母 ， 最后出现的位置）
比如用heap存一个class 也没有办法找出来更新它
所以最后还是用了一个array来不断更新最后出现的位置
并且通过方法private int findFirstIndexFromPositionArray(int[] nums) 找出来这些lastPosition里面最靠前的值
 */
