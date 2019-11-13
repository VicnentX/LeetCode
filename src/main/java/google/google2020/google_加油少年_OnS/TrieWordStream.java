package google.google2020.google_加油少年_OnS;

/*
高频题，一个dictionary里面有很多词
比如 ["abc","def"]，有一个stream一个一个单词进来，e,d,c,a,b,c,s,d,e,f....
如果出现了dict里面的单词就输出这个单词，刚才那个栗子里就在
读第二个c时输出abc，最后那个f那里输出def。lz用的地理的方法，
把所有的单词倒序存到trie里面，然后找出所有单词的最长的长度，
用一个linkedlist 存读过的字母，超过这个长度就在前面删掉旧的。
然后每读一个新字母就到trie里面比较一下。有的话就输出。
 */

import java.util.stream.Stream;

public class TrieWordStream {
    public void solve(Stream<Character> stream) {

    }
}
