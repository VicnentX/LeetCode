package leetcode.Enum;

public class EnumTest {
    public enum MyHost {
        MY_SELF, MY_WIFE
    }

    public static void main(String[] args) {
        MyHost myHost = MyHost.MY_WIFE;
        switch (myHost) {
            case MY_SELF:
                System.out.println("this is me");
                break;
            case MY_WIFE:
                System.out.println("this is my wife");
                break;
            default:
                System.out.println("this is default");
        }
    }


}
