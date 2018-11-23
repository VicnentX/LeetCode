package leetcode.Number;

public class StringToInt {

    public int myAtoi(String s) {
        int i = 0 ;
        int start;
        while(i < s.length()){

            if(s.charAt(i) == ' '){
                ++i;
            }else if(s.charAt(i) == '+' || s.charAt(i) == '-' || Character.isDigit(s.charAt(i))){
                if(!Character.isDigit(s.charAt(i))){
                    ++i;
                }

                if(Character.isDigit(s.charAt(i))){
                    start = i;
                    ++i;
                }else{
                    return 0;
                }
                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    ++i;
                }
                double ret = Double.parseDouble(s.substring(start , i));
                if(start != 0 && s.charAt(start - 1) == '-'){
                    if(ret - 1 > Integer.MAX_VALUE) return Integer.MIN_VALUE;
                    else return Integer.parseInt(s.substring(start - 1,i));
                }else{// =='+' or no sign
                    if(ret > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                    else return Integer.parseInt(s.substring(start,i));
                }
            }
            else{
                return 0;
            }
        }
        return 0;
    }

    public static void main(String[] args){
        StringToInt si = new StringToInt();
        System.out.println(si.myAtoi("42"));
        System.out.println(si.myAtoi("321 23 ew "));
        System.out.println(si.myAtoi("   w3239 "));
        System.out.println(si.myAtoi("   999999993939939393939 "));
        System.out.println(si.myAtoi("   - 32 "));
        System.out.println(si.myAtoi("   -320494"));
        System.out.println(si.myAtoi("-2147483648 "));
        System.out.println(si.myAtoi("  +2147483647 "));
        System.out.println(si.myAtoi("-2147483649 "));
        System.out.println(si.myAtoi("2147483648 "));
    }

}
