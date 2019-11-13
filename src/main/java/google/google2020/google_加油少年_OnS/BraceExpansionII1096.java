package google.google2020.google_加油少年_OnS;

/*
Under a grammar given below, strings can represent a set of lowercase words.  Let's use R(expr) to denote the set of words the expression represents.

Grammar can best be understood through simple examples:

Single letters represent a singleton set containing that word.
R("a") = {"a"}
R("w") = {"w"}
When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.
R("{a,b,c}") = {"a","b","c"}
R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
Formally, the 3 rules for our grammar:

For every lowercase letter x, we have R(x) = {x}
For expressions e_1, e_2, ... , e_k with k >= 2, we have R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
For expressions e_1 and e_2, we have R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}, where + denotes concatenation, and × denotes the cartesian product.
Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.



Example 1:

Input: "{a,b}{c,{d,e}}"
Output: ["ac","ad","ae","bc","bd","be"]
Example 2:

Input: "{{a,z},a{b,c},{ab,z}}"
Output: ["a","ab","ac","z"]
Explanation: Each distinct word is written only once in the final answer.


Constraints:

1 <= expression.length <= 60
expression[i] consists of '{', '}', ','or lowercase English letters.
The given expression represents a set of words based on the grammar given in the description.
 */

/**
 * Then we can try to convert current problem to the calculator problem.
 *
 * lower letter -> numbers
 * , -> +
 * * is a little tricky because it is intentionally neglected.
 * For example: "{{a,z},a{b,c},{ab,z}}" -> "" * ( "" * ( ( a + z ) + a * (b + c) + (a * b + z) ) )
 * Another tricky point is to use List of String to represent everything.
 * Here is the solution:
 */

import java.util.*;

public class BraceExpansionII1096 {
    public List<String> braceExpansionII(String expression) {
        List<String> currList = new ArrayList<>();
        currList.add("");
        Stack<List<String>> stack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        char sign = '*';
        for (char c : expression.toCharArray()) {
            stack.push(new ArrayList<>(currList));
            currList.clear();
            currList.add("");

            if (c == '{' ) {
                while (!opStack.empty() && opStack.peek() != ','  && opStack.peek() != '{')
                    doOp(stack, opStack);
                opStack.push(sign);
                opStack.push('{');
                sign = '*';
            }
            else if (c == '}') {
                while (opStack.peek() != '{')
                    doOp(stack, opStack);
                opStack.pop();
                opStack.push(sign);
                sign = '*';
            }
            else if (c == ',') {
                while (!opStack.empty() && opStack.peek() != '{')
                    doOp(stack, opStack);
                opStack.push(sign);
                sign = ',';
            }
            else {
                currList.set(0, ""+c);
                while (!opStack.empty() && opStack.peek() != ','  && opStack.peek() != '{')
                    doOp(stack, opStack);
                opStack.push(sign);
                sign = '*';
            }
        }

        stack.push(currList);
        while (!opStack.empty())
            doOp(stack, opStack);

        List<String> res = stack.pop();
        Collections.sort(res);
        return res;
    }


    private List<String> multi(List<String> list1, List<String> list2) {
        List<String> res = new ArrayList<>();
        for (String s1 : list1)
            for (String s2 : list2)
                res.add(s1 + s2);
        return res;
    }

    private List<String> plus(List<String> list1, List<String> list2) {
        Set<String> set = new HashSet<>(list1);
        set.addAll(list2);
        return new ArrayList<>(set);
    }

    private void doOp(Stack<List<String>> stack, Stack<Character> opStack) {
        List<String> list2 = stack.pop(), list1 = stack.pop();
        char c = opStack.pop();

        if (c == ',') stack.push(plus(list1, list2));
        else stack.push(multi(list1, list2));
    }
}
