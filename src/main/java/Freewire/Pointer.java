package Freewire;

public class Pointer {

    public static void main(String[] args) {
        String s = "ABC";
        String[] array = new String[1];
        array[0] = "ABC";
        System.out.println(s + "_" + array[0]);
        concatenate(s, array);
        System.out.println(s + "_" + array[0]);
    }

    private static void concatenate(String s, String[] array) {
        s = s + array[0];
        array[0] = s + "1";
    }

}
