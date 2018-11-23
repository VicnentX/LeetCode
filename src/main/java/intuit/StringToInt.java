package intuit;

import java.util.*;

public class StringToInt {
    //evaluate with reverse polish notation
    public int calculate(String s){
        //build tokens
        List<String> tokens = toRPN(s);
        //evaluate the tokens
        return evaluate(tokens);
    }

    //scan the input string and convert it to reverse polish notation
    //this mechanism is when i get a number , i add it to the tokens list
    //                  when i get a operator, i check the stack and while the top operator's priority is higher than this operator ,
    //                          add the stack.pop() to the tokens list , at last , add this operator to stack
    //                  when i get a ( , add it to stack
    //                  when i get a ) ,add stack.pop() to the tokens list while it is not ( , then pop ( out of the stack
    //                  when i finish the scan , if number is not empty , add it to the tokens list
    //                                           if stack is not empty , add it to the tokens list with pop() till it is empty
    //now i have the tokens list and then i will evaluate it
    //                  when i get a number push it into stack
    //                  when i get a operator and i get stack.pop() as int a AND then get stack.pop() as int b(should convert string into int)
    //                              if this operator is +  -> b + a
    //                                                  -  -> b - a
    //                                                  *  -> b * a
    //                                                  /  -> b / a
    //                              then i push the result to the stack (convert int to string)
    //                  at last the output is the stack.pop()

    private List<String> toRPN(String s){
        //create data container
        HashMap<String , Integer> map = new HashMap<String, Integer>(){{
            put("*" , 2);
            put("/" , 2);
            put("+" , 1);
            put("-" , 1);
            put("(" , 0);
            put(")" , 0);
        }};
        String op = "+-*/";
        List<String> tokens = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        char[] ch = s.toCharArray();
        String num = "";

        //scan input string and create tokens list
        for(char c : ch){
            if(Character.isDigit(c)){
                num += c;
            }
            else{
                if(!num.isEmpty()){
                    tokens.add(num);
                    num = "";
                }
                if(op.contains(String.valueOf(c))){
                    while(!stack.isEmpty() && map.get(stack.peek()) >= map.get(String.valueOf(c))){
                        tokens.add(stack.pop());
                    }
                    stack.add(String.valueOf(c));
                }else if(c == '('){
                    stack.add(String.valueOf(c));
                }else if(c == ')'){
                    while(!stack.isEmpty() && !stack.peek().equals("(")){
                        tokens.add(stack.pop());
                    }
                    stack.pop();
                }
            }
        }
        if(!num.isEmpty()){
            tokens.add(num);
        }
        while(!stack.isEmpty()){
            tokens.add(stack.pop());
        }
        return tokens;
    }

    private int evaluate(List<String> tokens) {
        Stack<String> stack = new Stack<>();
        String op = "+-*/";
        for(String k : tokens){
            // if k equals numbers
            if(!op.contains(k)){
                stack.push(k);
            }else{
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                switch(k){
                    case "+" :
                        stack.push(String.valueOf(b + a));
                        break;
                    case "-" :
                        stack.push(String.valueOf(b - a));
                        break;
                    case "*" :
                        stack.push(String.valueOf(b * a));
                        break;
                    case "/" :
                        stack.push(String.valueOf(b / a));
                        break;
                }
            }
        }
        return Integer.valueOf(stack.pop());
    }

    public static void main(String[] args){
        StringToInt si = new StringToInt();
        System.out.println(si.calculate("4-(2*3-5)"));
    }
}
