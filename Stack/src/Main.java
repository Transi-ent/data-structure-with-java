import java.util.Random;

public class Main {

    public static double testStack(Stack<Integer> stack, int count){

        long startTime = System.nanoTime();
        Random random = new Random();
        for(int i=0; i<count; i++){
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }

        for(int i=0; i<count; i++){
            stack.pop();
        }
        long endTime = System.nanoTime();

        double t = (endTime-startTime)/1.0E9;

        return t;
    }

    public static void main(String[] args) {
	// write your code here

        int count = 10000000;
        Stack<Integer> Liststack = new LinkedListStack<>();
        System.out.println("LinkedListStack Running Time: "+testStack(Liststack, count));

        Stack<Integer> arraystack = new ArrayStack<>();
        System.out.println("ArrayStack Running Time: "+testStack(arraystack, count));

    }
}
