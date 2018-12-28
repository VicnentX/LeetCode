package leetcode.HashTable;

/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
 */

import java.util.*;

public class ValidParentheses20 {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '[') {stack.push(']') ; continue;}
            if (c == '{') {stack.push('}') ; continue;}
            if (c == '(') {stack.push(')') ; continue;}
            if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }

    public static void main (String[] args) {
        ValidParentheses20 vp = new ValidParentheses20();
        System.out.println(vp.isValid("([)]"));
    }
}
