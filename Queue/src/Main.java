import java.util.Random;

public class Main {

    private static double testQueue(Queue<Integer> q, int opCount){

        long startTime = System.nanoTime();
        for(int i=0; i<opCount; i++){
            Random rand = new Random();
            q.enqueue(rand.nextInt(Integer.MAX_VALUE));
        }

        for(int i=0; i<opCount; i++){
            q.dequeue();
        }


        long endTime = System.nanoTime();

        return (endTime - startTime)/1.0E9;
    }

    public static void main(String[] args) {
	// write your code here
        int count = 100000;

        ArrayQueue aq = new ArrayQueue();
        double time1 = testQueue(aq, count);
        System.out.println("ArrayQueue, time:"+time1);

        Queue lq = new LoopQueue();
        double time2 = testQueue(lq, count);
        System.out.println("LoopQueue, time:"+time2);

        Queue llq = new LinkedListQueue();
        double time3 = testQueue(llq, count);
        System.out.println("LinkedListQueue, time:"+time3);
    }
}
