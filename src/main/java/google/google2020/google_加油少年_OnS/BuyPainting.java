package google.google2020.google_加油少年_OnS;

/*
大意就是给你一些画，每个画都有一个min price和一个quality point，
问需要多少钱才能把所有画买下来，条件是1）每幅画的成交价不能低于min price，2）
所有画的成交价必须和他们的quality point成正比。e.g. {20, 3}, {10, 1}, {15, 2}，
成交价就是30, 10, 20, 总价60。这一问比较简单，
可以O(N) time O(1) space完成。
接下来follow up问最少需要多少钱才能买下n幅画。
当时只有20分钟左右了我就说简单一点可以brute force，
把所有画里取n个的组合找出来，然后call第一问的函数算价格，
最后取global min。小姐姐说不错，你就写brute force吧，
我就写了。面完以后走回原来楼的路上我就问这题是不是有更简单的解法，
感觉有点像DP，小姐姐说是的可以DP解。

后来回家想了想可以用背包问题解
 */

public class BuyPainting {
}
