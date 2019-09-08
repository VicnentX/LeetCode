package twitter.twitter_2020;

/*
first parameter tablePosition
second parameter tableHeight

3. design offices
非常数学，画图做。。。
题目大概是给两个list 比如说[1,3,4,8] 和[2,3,5,7].
第一个list表示楼的位置，第二个表示楼的高度。然后往楼和楼的间隔中间插入hashtag。
hashtag的高度不能超过相邻的楼楼高+1。
比如说例子里的楼4和楼8之间可以插入三堆hashtag（5，6，7）。高度可以是6 7 8. 返回最高的hashtag为8.
另一个例子如果有一个楼1高度为1，楼10高度为7，那么应该分别插入高度为（2，3，4，5，6，7，8，7）。此时返回最高的hashtag为8.
（如有错请指出）

两个相邻桌子不考虑
 */

public class TritterNewOfficeDesign {
    public int findMaxHashHeight(int[] tablePosition , int[] tableHeight) {
        int maxHeight = 0;
        if (tablePosition.length != tableHeight.length) return maxHeight;
        final int N = tablePosition.length;

        for (int i = 0 ; i < N - 1 ; ++i) {
            int indexLeft = tablePosition[i];
            int indexRight = tablePosition[i + 1];

            if (tableHeight[i] != tableHeight[i + 1]) {
                int holderAccount = indexRight - indexLeft - 1;
                if (holderAccount <= Math.abs(tableHeight[i] - tableHeight[i + 1])) {
                    maxHeight = Math.max(maxHeight , Math.min(tableHeight[i], tableHeight[i + 1]) + holderAccount);
                } else {
                    maxHeight = Math.max(maxHeight , (tableHeight[i] + tableHeight[i + 1] + indexRight - indexLeft) / 2);
                }
            }
        }

        return maxHeight;
    }

    public static void main(String[] args) {
        TritterNewOfficeDesign tritterNewOfficeDesign = new TritterNewOfficeDesign();
        System.out.println(tritterNewOfficeDesign.findMaxHashHeight(new int[]{1,2,4,7} , new int[]{4,5,7,11}));
    }
}
