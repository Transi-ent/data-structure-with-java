import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V>  {

    private class Node {
        public K key;
        public V value;
        public int height;
        public Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.height = 1;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    private int getHeight(Node node){
        if(node==null){
            return 0;
        }
        return node.height;
    }

    // 计算平衡因子
    private int getBalanceFactor(Node node){
        if(node==null){
            return 0;
        }
        return getHeight(node.left)-getHeight(node.right);
    }

    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();

        inOrder(root, keys);

        for(int i=1; i<keys.size(); i++){
            if(keys.get(i-1).compareTo(keys.get(i))>0){
                return false;
            }
        }

        return true;
    }

    private void inOrder(Node node, ArrayList keys){

        if(node == null){
            return ;
        }

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){

        if(node==null){
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor)>1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    public int getSize(){
        return size;
    }


    public boolean isEmpty(){
        return size==0;
    }

    //              y                           x
    //            /   \                       /   \
    //          x      T4   right rotate     z     y
    //        /   \         ============>   / \   / \
    //       z     T3                      T1 T2 T3  T4
    //      / \
    //     T1  T2

    private Node rightRotate(Node y){

        Node x = y.left;
        Node T3 = x.right;

        // The process of right rotation
        x.right = y;
        y.left = T3;

        // Maintenance the Height after Rotation.
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node y){

        Node x = y.right;
        Node T3 = x.left;

        // The process of right rotation
        x.left = y;
        y.right = T3;

        // Maintenance the Height after Rotation.
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    public void add(K key, V value){
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value){
        if(node==null){
            size++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key)<0){
            node.left = add(node.left, key, value);
        }
        else if(key.compareTo(node.key)>0){
            node.right = add(node.right, key, value);
        }
        else{ // key==node.key;
            node.value = value;
        }

        // for every node concerned, update its height value.
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // Compute the corresponding balance factor
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor)>1){
            System.out.println("unbalanced :"+balanceFactor);
        }

        // LL
        if(balanceFactor>1 && getBalanceFactor(node.left)>=0){
            // As balanceFactor = getHeight(node.left)-getHeight(node.right)
            // thus, when balanceFactor>1, THE node must has a longer left sub-tree
            // as getBalanceFactor(node.left)>=0, THE node.left also has a longer left sub-tree.
            return rightRotate(node);
        }
        // RR
        if(balanceFactor<-1 && getBalanceFactor(node.right)<=0){
            return leftRotate(node);
        }
        // LR
        if(balanceFactor>1 && getBalanceFactor(node.left)<0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if(balanceFactor<-1 && getBalanceFactor(node.right)>0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node getNode(Node node, K key){
        if(node==null){
            return null;
        }

        if(key.equals(node.key)){
            return node;
        }else if(key.compareTo(node.key)<0){
            return getNode(node.left, key);
        }else{
            return getNode(node.right, key);
        }
    }


    public boolean contains(K key){
        return getNode(root, key)!=null;
    }


    public V get(K key){
        Node node = getNode(root, key);
        return node==null?null:node.value;
    }


    public void set(K key, V value){
        Node node = getNode(root, key);
        if(node==null){
            throw new IllegalArgumentException("key doesn't exist.");
        }
        node.value = value;
    }

    private Node minimum(Node node){
        if(node.left==null){
            return node;
        }
        return minimum(node.left);
    }

    private Node removeMin(Node node){

        if(node.left==null){
            Node rightNode = node.right;
            size--;
            node.right = null;
            return rightNode;
        }
        node.left = removeMin(node.left);//search deeper, find a node
        //...to receive ret Node
        return node;
    }

    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }

        return null;
    }

    // delete The node whose key is KEY, then return the NODE
    private Node remove(Node node, K key){

        if(node==null){
            return null;
        }

        Node retNode;
        if(key.compareTo(node.key)<0){
            node.left = remove(node.left, key);
            retNode =  node;
        }
        if(key.compareTo(node.key)>0){
            node.right = remove(node.right, key);
            retNode = node;
        }

        // key == node.key
        if(node.left==null){
            Node rightNode = node.right;
            size--;
            node.right = null;
            retNode = rightNode;
        }else if(node.right==null){
            Node leftNode = node.left;
            node.right = null;
            size--;
            retNode = leftNode;
        }else{
            Node successor = minimum(root);
            successor.left = node.left;
            successor.right = remove(node.right, successor.key);
            node.left = null;
            node.right = null;
            retNode = successor;
        }

        if(retNode==null){
            return null;
        }

        // for every node concerned, update its height value.
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        // Compute the corresponding balance factor
        int balanceFactor = getBalanceFactor(retNode);
        if(Math.abs(balanceFactor)>1){
            System.out.println("unbalanced :"+balanceFactor);
        }

        // LL
        if(balanceFactor>1 && getBalanceFactor(retNode.left)>=0){
            // As balanceFactor = getHeight(node.left)-getHeight(node.right)
            // thus, when balanceFactor>1, THE node must has a longer left sub-tree
            // as getBalanceFactor(node.left)>=0, THE node.left also has a longer left sub-tree.
            return rightRotate(retNode);
        }
        // RR
        if(balanceFactor<-1 && getBalanceFactor(retNode.right)<=0){
            return leftRotate(retNode);
        }
        // LR
        if(balanceFactor>1 && getBalanceFactor(retNode.left)<0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // RL
        if(balanceFactor<-1 && getBalanceFactor(retNode.right)>0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

}



