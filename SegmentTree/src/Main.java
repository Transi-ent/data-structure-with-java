public class Main {

    public static void main(String[] args) {
	// write your code here

        Integer[] nums ={1,3,5,7,9,11};
//        SegmentTree<Integer> st = new SegmentTree<Integer>(nums, new Merger<Integer>() {
//            @Override
//            public Integer merge(Integer a, Integer b) {
//                return a+b;
//            }
//        });// lambda class is USED to simplify the Codes
        // Lambda expression can be used to simplify the codes further.
        SegmentTree<Integer> st = new SegmentTree<Integer>(nums, (a, b) -> a+b);
        System.out.println(st);
    }
}
