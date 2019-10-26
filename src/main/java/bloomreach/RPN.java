package bloomreach;

/*
1. “1+2*3*(2+8)+6" 计算结果。就是来说有加减乘数还有括号。
方法：2个STACK
 */

import java.util.Stack;

public class RPN {

    //当我有了加减乘除表达式 怎么得到结果
    public int evalRPN(String[] tokens) {
        String operators = "+-*/";
        Stack<String> operands = new Stack<>();
        for (String s : tokens) {
            if (operators.contains(s)) {
                int op2 = Integer.parseInt(operands.pop());
                int op1 = Integer.parseInt(operands.pop());
                switch (s) {
                    case "+" : operands.push(String.valueOf(op1 + op2));break;
                    case "-" : operands.push(String.valueOf(op1 - op2));break;
                    case "*" : operands.push(String.valueOf(op1 * op2));break;
                    case "/" : operands.push(String.valueOf(op1 / op2));break;
                }
            } else {
                operands.push(s);
            }
        }
        return Integer.parseInt(operands.pop());
    }

}
