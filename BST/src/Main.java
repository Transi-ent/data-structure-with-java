public class Main {

    public static void main(String[] args) {
	// write your code here
        BST<Integer> bst = new BST<>();
        int[] nums={5,3,6,8,4,2};
        for(int num: nums){
            bst.add(num);
        }

        bst.preOrder();

        System.out.println();
        //bst.preOrderNR();
        bst.levelOrder();

//        bst.inOrder();
//
//        System.out.println();
//
//        bst.postOrder();

        //System.out.println(bst);

    }
}
