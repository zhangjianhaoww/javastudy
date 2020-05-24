package bilian.tech.zhang.javastarter.stack;

import java.util.Stack;

/**
 * 括号匹配
 * 使用栈来解决
 */
public class Solution {

    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{
                if (stack.isEmpty()){
                    return false;
                }

                if (!compare(stack.pop(), c)) {
                   return false;
                }
            }
        }
//        if (! stack.isEmpty()){
//            return false;
//        }
//        return true;
        return stack.isEmpty();
    }

    private boolean compare(char stackChar, char b){
        if ((stackChar == '(' && b == ')') || (stackChar == '[' && b == ']') || (stackChar == '{' && b == '}')){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.isValid("{}{}[][[]]{[()]}{"));
    }
}
