import java.lang.reflect.Array;
import java.util.*;

public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        this.root = null;
        this.size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(E e){

        root = add(root, e);
    }

    public Node add(Node node, E e){

        if(node==null){
            node = new Node(e);
            size++;
            return node;
        }else{
            if(e.compareTo(node.e)<0){
                node.left = add(node.left, e);
            }else if(e.compareTo(node.e)>0){
                node.right = add(node.right, e);
            }
        }

        return node;
    }

    public boolean contains(E e){
        return contains(root, e);
    }

    private boolean contains(Node node, E e){
        if(node==null){
            return false;
        }

        if(node.e==e){
            return true;
        }else if(e.compareTo(node.e)<0){
            return contains(node.left, e);
        }else{
            return contains(node.right, e);
        }
    }

    // Preorder of binary searching tree
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node==null){
            return ;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void preOrderNR(){

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if(cur.right!=null){
                stack.push(cur.right);
            }
            if(cur.left!=null){
                stack.push(cur.left);
            }
        }
    }

    public void inOrder(){

        inOrder(root);
    }

    private void inOrder(Node node){
        if(node==null){
            return ;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder(){
        postOrder(root);
    }

    // Release memery
    private void postOrder(Node node){
        if(node==null){
            return ;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void levelOrder(){

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node cur = q.remove();

            System.out.println(cur.e);
            if(cur.left!=null){
                q.add(cur.left);
            }
            if(cur.right!=null){
                q.add(cur.right);
            }
        }
    }

    public E minimum(){
        if(isEmpty()){
            throw new IllegalArgumentException("BST is empty.");
        }

        return minimum(root).e;
    }

    private Node minimum(Node node){
        if(node.left==null){
            return node;
        }

        return minimum(node.left);
    }

    public E maximum(){
        if(isEmpty()){
            throw new IllegalArgumentException("BST is empty.");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node){
        if(node.right==null){
            return node;
        }

        return maximum(node.right);
    }

    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node){
        if(node.left==null){ //抵达最小节点
            Node rightNode = node.right; //
            node.right = null; //detach the delNode with the tree
            size--;// maintain the parameter---size
            return rightNode;// maintain the structure of the tree.
        }

        node.left = removeMin(node.left);//DFS
        return node;//maintain the structure of the tree
    }

    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }
    //The stupid primary student are watching around, what are you fucking watching?
    private Node removeMax(Node node){
        if(node.right==null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;// if the node is NOT the maximum Node, it will keep search
    }

    public void remove(E e){// delete the node with a value e
        root = remove(root, e);
    }

    public Node remove(Node node, E e){
        if(node==null){
            return null;
        }

        if(e.compareTo(node.e)<0){
            node.left = remove(node.left, e);
            return node;
        }else if(e.compareTo(node.e)>0){
            node.right = remove(node.right, e);
            return node;
        }else{
            // the leftChild of delNode == null
            if(node.left==null){
                Node right = node.right;
                size--;
                node.right = null;
                return right;
            }
            // the rightChild of delNode == null
            if(node.right==null){
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }
            // Both child of delNode are not NULL
            Node delNode = minimum(node.right);
            delNode.left = node.left;
            delNode.right = removeMin(node.right);
//            node.left = null;
//            node.right = null;
            node.left = node.right = null;
            return delNode;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res){
        if(node==null){
            res.append(generateDepthString(depth)+"null\n");
            return;
        }
        res.append(generateDepthString(depth)+node.e+"\n");
        generateBSTString(node.left, depth+1, res);
        generateBSTString(node.right, depth+1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i=0; i<depth; i++){
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args){
        BST<Integer> bst = new BST<>();

        Random random = new Random();
        for(int i=0; i<1000; i++){
            bst.add(random.nextInt(5000));
        }

        List<Integer> nums = new LinkedList<>();
        while(!bst.isEmpty()){
            nums.add(bst.removeMin());
        }

        System.out.println(nums);
        for(int i=1; i<nums.size(); i++){
            if(nums.get(i-1)>nums.get(i)){
                throw new IllegalArgumentException("Error!");
            }
        }
    }

}
