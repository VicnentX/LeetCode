package NextJump;

/*
intial String is all zeros
flip at i : all index >= i switch between 0 and 1
return an integer that denotes minimum number of flips needed
 */

public class FinalNumber {
    public int totalFlipCnt(String s) {
        int cnt = 0;
        char pre = '0';
        for (char c: s.toCharArray()) {
            if (c != pre) {
                cnt++;
            }
            pre = c;
        }
        return cnt;
    }

    public static void main(String[] args) {
        FinalNumber finalNumber = new FinalNumber();
        System.out.println(finalNumber.totalFlipCnt("01011"));
    }


    /**
     * final problem我的思路就是异或差分
     * 区间取反在差分数组上的意义就是两端点取反，左闭右开
     *
     * 假设原数组是a，那么他的异或差分数组diff=a^a[i-1]
     * 比如数组 01011，那么他的差分数组就是01110
     * 如果你要对第一个数组2-4位取反，变成00101，那么就取反差分数组的第2位和第5位，变成00111
     * 然后怎么用差分数组还原成原数组呢，就是取前缀异或和，就是原数组第i位就等于前i位差分数组全部异或起来，可以算出00111→00101，发现跟原数组a一样
     *
     * 而题目说每次修改只能修改某个端点以及他右边的位，那么就很简单了。因为每次修改差分数组时，右端点都是n+1，跟原数组造不成影响，所以每次修改只会把前n个某一位1改成0
     *
     * 所以结论就是，求出原数组的差分数组，然后统计下差分数组中1的个数，就是答案
     * [C++] 纯文本查看 复制代码
     * ?
     * 01
     * 02
     * 03
     * 04
     * 05
     * ans = 0;
     * for(int i = 1; i <= n; i++){
     *     if(a[i]^a[i-1] == 1) ans++;
     * }
     * return ans;
     */
}
