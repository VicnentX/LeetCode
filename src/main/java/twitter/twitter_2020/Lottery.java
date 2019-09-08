package twitter.twitter_2020;

/*
排队买票：

输入： 一个数组A，代表初始排队顺序，队伍头为index 0，每一项代表队伍中的一个人需要买的票数；一个数p，代表你在初始队列的index

输出：int  K，代表时间unit

问题：经过多少时间unit你能买齐所有票？

规则：按队伍顺序买票，一次只能买1张，消耗1 unit时间，任何人买完一次后如果票没买齐则继续排到队尾，如买齐则离开，队伍前进及走到队尾排队时间不计
 */

public class Lottery {
    public int WaitingTimeInLine(int[] line, int p) {
        final int MY_TICKET_ACCOUNT = line[p];
        int ret = 0;

        for (int i = 0; i <= p; ++i) {
            ret += Math.min(MY_TICKET_ACCOUNT, line[i]);
        }
        for (int i = p + 1; i < line.length; ++i) {
            ret += Math.min(MY_TICKET_ACCOUNT - 1 , line[i]);
        }

        return ret;
    }

    public static void main(String[] args) {
        Lottery lottery = new Lottery();
        System.out.println(lottery.WaitingTimeInLine(new int[] {4,5,6,1,3,4,6,7,2,1} , 4));
    }
}
