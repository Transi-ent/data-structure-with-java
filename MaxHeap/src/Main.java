import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int n = 1000000;
        MaxHeap<Integer> heap = new MaxHeap<>();
        Random random = new Random();
        for(int i=0; i<n; i++){
            heap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];
        for(int j=0; j<n; j++){
            arr[j] = heap.extractMax();
        }

        for(int i=1; i<n; i++){
            if(arr[i-1]<arr[i]){
                System.out.println("Error!");
            }
        }

        System.out.println("Test completed!");
    }
}
