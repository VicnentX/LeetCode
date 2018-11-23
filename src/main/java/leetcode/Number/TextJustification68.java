package leetcode.Number;
/*
Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
 */

import java.util.*;

public class TextJustification68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ret = new ArrayList<>();
        Queue<String> tem = new LinkedList<>();
        if(maxWidth == 0 || words.length == 1 && words[0].length() > maxWidth) return ret;
        int wlens = 0;
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < words.length ; ++i){
            String k = words[i];
            int len = k.length();
            if(len + cnt + wlens <= maxWidth){
                tem.add(k);
                wlens += len;
                ++cnt;
            }else{
                int space = maxWidth - wlens;
                if(tem.size() == 1){
                    sb.append(tem.poll());
                    for(int j = 0 ; j < space ; ++j){
                        sb.append(" ");
                    }
                }else{
                    int size = tem.size() - 1;
                    int a = space / size;
                    int b = space % size;

                    while(!tem.isEmpty()){
                        sb.append(tem.poll());
                        if(!tem.isEmpty()){
                            for(int j = 0 ; j < a + (b > 0 ? 1 : 0) ; ++j){
                                sb.append(" ");
                            }
                            --b;
                        }
                    }
                }
                ret.add(sb.toString());
                wlens = 0;
                cnt = 0;
                sb = new StringBuilder();
                --i;
            }
        }
        if(!tem.isEmpty()){
            int cntCopy = cnt - 1;
            while(!tem.isEmpty()){
                sb.append(tem.poll());
                if(cntCopy > 0){
                    sb.append(" ");
                    --cntCopy;
                }
            }
            for(int i = 0 ; i < maxWidth - wlens - cnt + 1 ; ++i){
                sb.append(" ");
            }
        }
        ret.add(sb.toString());
        return ret;
    }
}
