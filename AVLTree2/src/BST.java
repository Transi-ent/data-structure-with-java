public class BST<K extends Comparable<K>, V>  {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }


    public boolean isEmpty(){
        return size==0;
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



