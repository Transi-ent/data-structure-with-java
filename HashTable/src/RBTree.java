import com.sun.org.apache.regexp.internal.RE;

public class RBTree<K extends Comparable<K>, V>  {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED; // TODO: why?
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }


    public boolean isEmpty(){
        return size==0;
    }

    public boolean isRed(Node node){
        if(node == null){
            return BLACK;
        }
        return node.color;
    }

    private Node leftRotate(Node node){

        Node x = node.right;

        // left Rotation
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //         node                         x
    //        /    \    right rotation     / \
    //       x     T1   =============>   T2  node
    //      / \                              /  \
    //     T2  T3                           T3   T1

    private Node rightRotate(Node node){

        Node x = node.left;

        // right rotation process
        node.left = x.right;
        x.right = node;

        // maintenance the color
        x.color = node.color;
        node.color = RED;

        return x;
    }

    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void add(K key, V value){
        root = add(root, key, value);
        root.color = BLACK;
    }

    private Node add(Node node, K key, V value){
        if(node==null){
            size++;
            return new Node(key, value);// Insert Red node by default
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

        if(isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        if(isRed(node.left) && isRed(node.right)){
            flipColors(node);
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

        if(key.compareTo(node.key)<0){
            node.left = remove(node.left, key);
            return node;
        }
        if(key.compareTo(node.key)>0){
            node.right = remove(node.right, key);
            return node;
        }

        // key == node.key
        if(node.left==null){
            Node rightNode = node.right;
            size--;
            node.right = null;
            return rightNode;
        }else if(node.right==null){
            Node leftNode = node.left;
            node.right = null;
            size--;
            return leftNode;
        }else{
            Node successor = minimum(root);
            successor.left = node.left;
            successor.right = removeMin(node.right);
            node.left = null;
            node.right = null;
            return successor;
        }

    }

}



