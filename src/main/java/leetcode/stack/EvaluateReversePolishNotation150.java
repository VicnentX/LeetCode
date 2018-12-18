package leetcode.stack;

/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation:
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
 */

/**
 * scan the array
 *  if the elemnt is an operand , push into stack.
 *  if the element is an aoperator , pop two top element and operate them with the operator
 *      (the top is the after operator , the second top is before operator)
 *          then push the result back into the stack
 */

import java.util.*;

public class EvaluateReversePolishNotation150 {
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

    public static void main (String[] args) {
        EvaluateReversePolishNotation150 er = new EvaluateReversePolishNotation150();
        //Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
        //Output: 22
        //Explanation:
        //  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
        //= ((10 * (6 / (12 * -11))) + 17) + 5
        //= ((10 * (6 / -132)) + 17) + 5
        //= ((10 * 0) + 17) + 5
        //= (0 + 17) + 5
        //= 17 + 5
        //= 22
        System.out.println(er.evalRPN(new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }
}
