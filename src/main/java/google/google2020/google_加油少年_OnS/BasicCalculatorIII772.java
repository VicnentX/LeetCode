package google.google2020.google_加油少年_OnS;

/*
there is + - * / ( )
并且这个程序可以处理数字为负数的情况 "-1+4*3/3/3"


这题可以借鉴到 1096. Brace Expansion II

 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BasicCalculatorIII772 {
    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        int i = 0;
        int num = 0;
        int sign = 1;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                numStack.push(sign * num);
                sign = 1;
                num = 0;
                i--;
            } else if (s.charAt(i) == '(') {
                opStack.push('(');
            } else if (s.charAt(i) == ')') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    doOperation(numStack, opStack);
                }
                opStack.pop();
            } else if (s.charAt(i) != ' '){
                if (s.charAt(i) == '-' && (i == 0 || s.charAt(i - 1) == '(')) {
                    sign = -1;
                    i++;
                    continue;
                }
                while (!opStack.isEmpty() && opStack.peek() != '(' && map.get(opStack.peek()) >= map.get(s.charAt(i))) {
                    doOperation(numStack, opStack);
                }
                opStack.push(s.charAt(i));
            }
            i++;
        }
        while (!opStack.isEmpty()) {
            doOperation(numStack, opStack);
        }
        return numStack.pop();
    }

    private void doOperation(Stack<Integer> numStack, Stack<Character> opStack) {
        int b = numStack.pop();
        int a = numStack.pop();
        char op = opStack.pop();
        if (op == '+') {
            numStack.push(a + b);
        } else if (op == '-') {
            numStack.push(a - b);
        } else if (op == '*') {
            numStack.push(a * b);
        } else {
            numStack.push(a / b);
        }
    }

    public static void main(String[] args) {
        BasicCalculatorIII772 basicCalculatorIII772 = new BasicCalculatorIII772();
        System.out.println(basicCalculatorIII772.calculate("-1+4*3/3/3"));
    }
}
