import java.util.Stack;

public class Solution {

    public boolean isValid(String s){

        Stack<Character> stack = new Stack();
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch=='(' || ch=='[' || ch=='{'){
                stack.push(ch);
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                char topCh = stack.pop();
                if(ch==')' && topCh!='('){
                    return false;
                }
                if(ch==']' && topCh!='['){
                    return false;
                }
                if(ch=='}' && topCh!='{'){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args){
        System.out.println((new Solution()).isValid("()[]{}"));
        System.out.println((new Solution()).isValid("([][})"));
    }
}
