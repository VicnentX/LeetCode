package TripleByte;

/*
第二题是很类似edit distance的题，
把edit distance的算法改一下下就能用，
就是如果str1 != str[j]时如果cost[i + 1][j + 1]
最后等于cost[j]，则更新值得时候cost[i + 1][j + 1] = cost[i + 1][j + 1] + 2，
因为edti distance可以替换，所以只用一步，而他这里只能删除，
所以两个字符串在这个位置都要删除，要算作2步，
 */
public class EditDistance {
}
