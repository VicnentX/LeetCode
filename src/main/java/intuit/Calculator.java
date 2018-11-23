package intuit;

import java.util.*;

public class Calculator {
	
	public static String calculate(String s, Map<String, Integer> map) {
		Deque<Integer> stack = new ArrayDeque<>();
		Deque<String> stringStack = new ArrayDeque<>();
		char[] cha = s.toCharArray();
		int number = 0;
		int sign = 1;
		int res = 0;
		StringBuilder sb = new StringBuilder();
		for (char c:cha) {
			if (Character.isDigit(c)) {
				number = number*10+(c-'0');
			}
			else if(Character.isLetter(c)) {
				sb.append(c);
			}
			else {
				if (sb.length() > 0) {
					String temp = sb.toString();
					if (map.containsKey(temp)) {
						number = map.get(temp);
					}
					else if (!map.containsKey(temp)) {
						if (sign == -1) {
							stringStack.push("-");
						}
						stringStack.push(temp);
						number = 0;
					}
					sb.setLength(0);
				}
				if (c == '+') {
					res += sign*number;
					sign = 1;
					number = 0;
				}
				else if  (c == '-') {
					res += sign*number;
					sign = -1;
					number = 0;
				}
				else if (c == '(') {
					stack.push(res);
					stack.push(sign);
					if (sign == -1) {
						stringStack.push("-");
					}
					//String ssign = sign==1? "+":"-";
					res = 0;
					//number = 0; //number is already 0 because met pre sign
					sign = 1;
				}
				else if (c == ')') {
					res += sign*number;
					number = 0;
					if (stringStack.peekLast().equals("-")) {
						stringStack.pop();
					}
					res *= stack.pop();
					res += stack.pop();
				}	
			}
		}
		if (sb.length() > 0) {
			String temp = sb.toString();
			if (map.containsKey(temp)) {
				number = map.get(temp);
			}
			else if (!map.containsKey(temp)) {
				if (sign == -1) {
					stringStack.push("-");
				}
				stringStack.push(temp);
				number = 0;
			}
			sb.setLength(0);
		}
		res += sign*number;
		StringBuilder a = new StringBuilder();
		Map<String, Integer> mapping = new HashMap<>();
		int count = 0;
		while (!stringStack.isEmpty()) {
			String ss = stringStack.pop();
			while (!stringStack.isEmpty() && stringStack.peek().equals("-")) {
				count++;
				stringStack.pop();
			}
			count %= 2; // -- is + // count = 0, +1, count = 1, -1
			int neg = count==0? 1:-1;
			mapping.put(ss, mapping.getOrDefault(ss, 0) + neg);
			count = 0;		
		}
		
		a.append(res);
		for (String key:mapping.keySet()) {
			int n = mapping.get(key);
			if (n == 0) {
				continue;
			}
			if (n > 0) {
				a.append("+");
				a.append(n);
				a.append(key);
			}
			else {
				a.append("+");
				a.append(-n);
				a.append(key);
			}
		}
		return a.toString();
	}
	
	public static void main(String[] args) {
		String s = "2+abc-(2+abc)+def+abc"; // 
		Map<String, Integer> map = new HashMap<>();
		map.put("def", 2);
		String res = calculate(s, map);
		System.out.print(res);
	}
}
