package Amazon.full_time2020.final_round_加油少年_coding;

public class ImplementQueueWithArray {

    int[] nums;
    final int N;
    int head;
    int tail;
    int cnt;

    ImplementQueueWithArray(int cap) {
        nums = new int[cap];
        N = cap;
        head = 0;
        tail = 0;
        cnt = 0;
    }

    public int pop() {
        if (N <= 0 || cnt == 0) return Integer.MIN_VALUE;
        int ret = nums[head % N];
        head = head % N + 1;
        cnt--;
        return ret;
    }

    public int push(int num) {
        if (N <= 0) return Integer.MIN_VALUE;

        if (cnt < N) {
            nums[tail % N] = num;
            tail++;
            cnt++;
        } else {
            nums[tail % N]  = num;
            tail++;
            head++;
        }
        return num;
    }

    public static void main(String[] args) {
        ImplementQueueWithArray implementQueueWithArray = new ImplementQueueWithArray(2);
        System.out.println(implementQueueWithArray.pop());
        System.out.println(implementQueueWithArray.push(1));
        System.out.println(implementQueueWithArray.push(2));
        System.out.println(implementQueueWithArray.push(3));
        System.out.println(implementQueueWithArray.pop());
        System.out.println(implementQueueWithArray.pop());
        System.out.println(implementQueueWithArray.pop());
        System.out.println(implementQueueWithArray.pop());
    }

}
