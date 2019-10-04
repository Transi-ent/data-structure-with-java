public class Main {

    public static void main(String[] args) {
	// write your code here
        Array<Integer> arr = new Array<>(20);
        for(int i=0; i<10; i++){
            arr.addLast(10*i);
        }

        System.out.println(arr);

        arr.add(6, 666);
        System.out.println(arr);

        arr.set(7, 777);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);
    }
}
