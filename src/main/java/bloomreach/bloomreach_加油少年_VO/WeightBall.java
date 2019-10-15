package bloomreach.bloomreach_加油少年_VO;

/*
电面二:
在一堆小球中找到唯一一个重的小球, 有一个天平可用. 要求最少次数称出来, 并写出通项公式.
通项公式是 n = log3(K) n是次数, k是小球的总共数量.
然后写出代码. 纯递归. 以下供参考.
 */

/**
 * 我改良了下 代码 在称重这里 可以先用一个累计和算出来0-i的总重量
 */

public class WeightBall {

    private int[] sumDP;

    public int findHeavy(int[] balls_array) {
        // return index of the heavier ball
        int sum = 0;
        sumDP = new int[balls_array.length];
        for (int i = 0; i < balls_array.length; ++i) {
            sum += balls_array[i];
            sumDP[i] = sum;
        }
        return getIndex(balls_array, 0, balls_array.length - 1);
    }

    private int getIndex(int[] array, int start, int end) {
        //the function to get index
        //Time complexity: O(log3(N))
        if (start == end) {
            return start;
        }
        double len = (double) (end - start + 1) / 3;
        int size = (int) Math.ceil(len);
        int oneThird = start + size;
        int twoThird = start + size + size;
        //suppose the time complexity of weighing is O(1)
        if (getWeight(start, oneThird - 1) == getWeight(oneThird, twoThird - 1)) {
            return getIndex(array, twoThird, end);
        } else if (getWeight(start, oneThird - 1) > getWeight(oneThird, twoThird - 1)) {
            return getIndex(array, start, oneThird - 1);
        } else {
            return getIndex(array, oneThird, twoThird - 1);
        }
    }

    private int getWeight(int start, int end) {
        return sumDP[end] - (start == 0 ? 0 : sumDP[start - 1]);
    }

    public static void main(String[] args) {
        WeightBall weightBall = new WeightBall();
        //7
        System.out.println(weightBall.findHeavy(new int[] {1,1,1,1,1,1,1,2,1,1,1,1}));
        //5
        System.out.println(weightBall.findHeavy(new int[] {1,1,1,1,1,4,1,1,1,1,1,1,}));
    }
}

