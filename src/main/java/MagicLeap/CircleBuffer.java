package MagicLeap;

//设计一个sirclebuffer pop push 都是从尾巴
// pop超过存的数据 就impossible
//push 尾巴满了的话就从头开始


public class CircleBuffer {

    public Object[] elements = null;

    private int capacity;
    private int writePos = 0;
    private int usedCap = 0;

    public CircleBuffer(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    public void reset() {
        this.writePos = 0;
        usedCap = 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getUsedCap() {
        return usedCap;
    }

    public int remainingCapacity() {
        return capacity - usedCap;
    }

    public void printElements() {
        int cnt = 0;
        while (cnt < usedCap) {
            System.out.print(elements[cnt] + " ");
            cnt++;
        }
        System.out.println();
    }

    public void push(String s) {

        int n = s.length();
        for (int i = 0 ; i < n ; ++i) {
            put(s.charAt(i));
        }

        //writePos in the next push function
        writePos = usedCap % capacity;

    }

    public void put(Object element) {
        if (writePos == capacity) {
            writePos = 0;
        }
        elements[writePos] = element;
        writePos++;
        if (usedCap < capacity) {
            usedCap++;
        }
    }

    public String pop(int length) {
        if (length > usedCap) return "IMPOSSIBLE";

        //计算从那个点开始pop
        writePos = (writePos + capacity - 1) % capacity;


        StringBuilder ret = new StringBuilder();
        for (int i = 0 ; i < length ; ++i) {
            ret.insert(0, take());
        }
        ++writePos;
        return ret.toString();
    }


    public Object take() {
        Object elementPoped = elements[writePos--];
        usedCap--;
        return elementPoped;
    }



    public static void main(String[] args) {
        CircleBuffer sb = new CircleBuffer(10);
        System.out.println(sb.getCapacity());
        sb.push("123456789");
        System.out.println(sb.getUsedCap());
        sb.printElements();
        System.out.println("_________");
        sb.push("XYZ");
        sb.printElements();
        sb.pop(2);
        sb.printElements();
        sb.pop(2);
        sb.printElements();
        sb.push("54321");
        sb.printElements();
        sb.push("ZZ");
        sb.printElements();
        sb.pop(5);
        sb.printElements();
        sb.push("12345");
        sb.printElements();
        sb.push("99");
        sb.printElements();
        sb.pop(3);
        sb.printElements();
    }
}

