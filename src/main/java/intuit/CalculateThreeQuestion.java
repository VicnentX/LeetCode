package intuit;

import java.util.*;

/*
use rpn method reverse polish notation
it is the mechanism of computer to calculate the expression
for example
4 + 5 - 6
first use a list and a stack to create tokens(the list is the tokens)
then evaluate the tokens to get answer;

demonstration now
number :
stack :
list :  4   5   +   6   -           this is the tokens i need
then evaluate
stack : 3       stack.pop() is the answer
 */

public class CalculateThreeQuestion {
    public int noParenthesis(String s){
        List<String> tokens = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        char[] ch = s.toCharArray();
        String num = "";

        //scan the input and create tokens list
        for(char c : ch){
            if(Character.isDigit(c)){
                num += c;
            }
            //c is + or -
            else{
                if(!num.isEmpty()){
                    tokens.add(num);
                    num = "";
                }
                if(!stack.isEmpty()) tokens.add(stack.pop());
                stack.add(String.valueOf(c));
            }
        }
        if(!num.isEmpty()){
            tokens.add(num);
        }
        if(!stack.isEmpty()){
            tokens.add(stack.pop());
        }
        //evaluate the tokens
        //use the empty stack again
        String op = "+-";
        for(String k : tokens){
            if(!op.contains(k)){
                stack.push(k);
            }else{//k is a op
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                switch(k){
                    case "+" :
                        stack.push(String.valueOf(b + a));
                        break;
                    case "-" :
                        stack.push(String.valueOf(b - a));
                        break;
                }
            }
        }
        return Integer.valueOf(stack.pop());
    }

    //================================================================================================

    public int Parenthesis(String s){
        List<String> tokens = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        char[] ch = s.toCharArray();
        String num = "";
        String op = "+-";
        //scan the input and create tokens list
        for(char c : ch){
            if(Character.isDigit(c)){
                num += c;
            }
            //c is + or -
            else{
                if(!num.isEmpty()){
                    tokens.add(num);
                    num = "";
                }
                if(op.contains(String.valueOf(c))){//it means c = + or -
                    if(!stack.isEmpty() && !stack.peek().equals("(")){
                        tokens.add(stack.pop());
                    }
                    stack.add(String.valueOf(c));
                }
                else if(c == '('){
                    stack.push(String.valueOf(c));
                }else if(c == ')'){
                    if(!stack.isEmpty() && !stack.peek().equals("(")){
                        tokens.add(stack.pop());
                    }
                    stack.pop();
                }
            }
        }
        if(!num.isEmpty()){
            tokens.add(num);
        }
        if(!stack.isEmpty()){
            tokens.add(stack.pop());
        }
        //evaluate the tokens
        //use the empty stack again
        for(String k : tokens){
            if(!op.contains(k)){//k is a number
                stack.push(k);
            }else{//k is op
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                switch(k){
                    case "+" :
                        stack.push(String.valueOf(b + a));
                        break;
                    case "-" :
                        stack.push(String.valueOf(b - a));
                        break;
                }
            }
        }
        return Integer.valueOf(stack.pop());
    }

    //==============================================================================================================
    //there are some letter in the s

    String op = "+-";

    public String LetterAndParenthesis(String s , Map<String , Integer> map) {


        List<String> tokens = toRPN(s);

        return evaluateRPN(tokens , map);

    }

    private List<String> toRPN(String s) {
        List<String> tokens = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        char[] ch = s.toCharArray();
        String num = "";
        //scan the input and create tokens list

        for (char c : ch) {
            //c is the number
            if (Character.isLetterOrDigit(c)) {
                num += c;
            }
            //c is the letter in the map

            //c is + or -
            else {
                if (!num.isEmpty()) {
                    tokens.add(num);
                    num = "";
                }
                if (op.contains(String.valueOf(c))) {
                    if (!stack.isEmpty() && !stack.peek().equals("(")) {
                        tokens.add(stack.pop());
                    }
                    stack.add(String.valueOf(c));
                } else if (c == '(') {
                    stack.push(String.valueOf(c));
                } else if (c == ')') {
                    if (!stack.isEmpty() && !stack.peek().equals("(")) {
                        tokens.add(stack.pop());
                    }
                    stack.pop();
                }
            }
        }
        if (!num.isEmpty()) {
            tokens.add(num);
        }
        if (!stack.isEmpty()) {
            tokens.add(stack.pop());
        }
        return tokens;
    }


        //evaluate the tokens
        //now i need a new data structure as container
    private String evaluateRPN(List<String> tokens, Map<String, Integer> map) {
        //这里我要建立一个stack and put object[]的array in this stack, 这个array每个element放的是int 和 map的组合
        Stack<Object[]> stack = new Stack<>();
        for(String k : tokens) {
            if(op.contains(k)) {
                Object[] op2 = stack.pop();
                Object[] op1 = stack.pop();
                stack.push(evaluateItem(op1 , op2 , k));
            }
            else {
                if(Character.isDigit(k.charAt(0))){
                    stack.push(new Object[]{Integer.valueOf(k) , new HashMap<>()});
                }
                else{
                    stack.push(new Object[]{0 , new HashMap<String , Integer>(){{put(k , 1);}}});
                }
            }
        }
        //generate the result string

        StringBuilder sb = new StringBuilder();
        int rNumber = 0;
        if((int)stack.peek()[0] != 0){
            rNumber = ((int)stack.peek()[0]);
        }
        Map<String , Integer> rMap = (HashMap<String , Integer>) stack.peek()[1];
        //print rMap
        System.out.println(rMap);

        if(!map.isEmpty()){
            for(String k : rMap.keySet()){
                int value = rMap.get(k);
                if(map.containsKey(k)){
                    rNumber += map.get(k)*value;
                }
                else{
                    if(value != 0){
                        if(value > 1 || value  < -1){
                            sb.append(value);
                        }
                        else if(value == -1){
                            sb.append("-");
                        }
                        sb.append(k);
                    }
                }
            }
        }
        //print rNumber
        if(rNumber != 0){
            if(rNumber > 0 && !sb.toString().isEmpty()){
                sb.append("+");
            }
            sb.append(rNumber);
        }
        return sb.toString();

    }

    private Object[] evaluateItem(Object[] x, Object[] y , String op){
        int xNumber = (int)x[0];
        Map<String , Integer> xMap = (HashMap<String , Integer>)x[1];
        int yNumber = (int)y[0];
        Map<String , Integer> yMap = (HashMap<String , Integer>)y[1];
        //deal with number
        int rNumber = 0;
        if(op.equals("+")){
            rNumber = xNumber + yNumber;
        }
        else{
            rNumber = xNumber - yNumber;
        }
        //deal with map
        if(op.equals("-")){
            for(String k : yMap.keySet()){
                yMap.put(k , -yMap.get(k));
            }
        }
        //merge two maps into xMap;
        for(String k : yMap.keySet()){
            if(xMap.containsKey(k)){
                xMap.put(k , yMap.get(k) + xMap.get(k));
            }
            else{
                xMap.put(k , yMap.get(k));
            }
        }
        return new Object[]{rNumber , xMap};
    }



    public static void main(String[] args){
        CalculateThreeQuestion cq = new CalculateThreeQuestion();
        System.out.println("q1");
        System.out.println(cq.noParenthesis("1+2+3-4-5"));
        System.out.println("q2");
        System.out.println(cq.Parenthesis("(1+2-3)-(4-3+1)"));
        System.out.println("q3");
        System.out.println(cq.LetterAndParenthesis("(aa+b)-(c+d)+1" , new HashMap<String , Integer>(){{
            put("aa" , 1);
            put("b" , 2);
            put("c" , 3);
        }}));
        System.out.println(cq.LetterAndParenthesis("(a-b)+1" , new HashMap<String , Integer>(){{
            put("a" , 1);
            put("b" , 2);
            put("c" , 3);
        }}));
    }




}
