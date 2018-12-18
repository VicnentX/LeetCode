package leetcode.ReversePolishNotation;

/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */


import java.util.*;

public class BasicCalculator224 {
    public int calculate(String s) {
        List<String> tokens = new ArrayList<>();
        Stack<String> stack = new Stack<>();//stack stores the operators
        String num = "";
        String operator = "+-";

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num += c;
            } else {
                if (c == ' ') continue;

                if (!num.isEmpty()) {
                    tokens.add(num);
                    num = "";
                }

                if (operator.contains(String.valueOf(c))) {
                    if (!stack.isEmpty() && !stack.peek().equals("(")) {
                        tokens.add(stack.pop());
                    }
                    stack.push(String.valueOf(c));
                } else if (c == '(') {
                    stack.push(String.valueOf(c));
                } else {
                    if (!stack.isEmpty() && !stack.peek().equals("(")) {
                        tokens.add(stack.pop());
                    }
                    stack.pop();
                }
            }
        }

        if (!num.isEmpty()) tokens.add(num);

        if (!stack.isEmpty()) tokens.add(stack.pop());

        //check if there is no operands
        if (tokens.isEmpty()) return 0;

        for (String k : tokens) {
            if (operator.contains(k)) {
                int op1 = Integer.parseInt(stack.pop());
                int op2 = Integer.parseInt(stack.pop());
                switch (k) {
                    case "+" : stack.push(String.valueOf(op2 + op1));break;
                    case "-" : stack.push(String.valueOf(op2 - op1));break;
                }
            } else {
                stack.push(k);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main (String[] args) {
        BasicCalculator224 bc = new BasicCalculator224();
        //Input: "1 + 1"
        //Output: 2
        //Example 2:
        System.out.println(bc.calculate("1 + 1"));
        //Input: "(1+(4+5+2)-3)+(6+8)"
        //Output: 23
        //Note:
        //You may assume that the given expression is always valid.
        //Do not use the eval built-in library function.
        System.out.println(bc.calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}
