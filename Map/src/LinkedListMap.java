public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {

        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key){
            this(key,null, null);
        }

        public Node(){
            this(null, null, null);
        }

        @Override
        public String toString(){
            return key.toString()+" : "+value.toString();
        }
    }

    private int size;
    private Node dummyHead;

    public LinkedListMap(){
        this.size=0;
        dummyHead = new Node();
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size==0;
    }

    // Auxilary function
    public Node getNode(K key){
        Node cur = dummyHead.next;
        while(cur!=null){
            if(key.equals(cur.key)){
                return cur;
            }
            cur = cur.next;
        }

        return null;
    }

    @Override
    public boolean contains(K key){
        return getNode(key)!=null;
    }

    @Override
    public V get(K key){
        Node node = getNode(key);
        return node==null?null:node.value;
    }

    @Override
    public void add(K key, V value){
        Node node = getNode(key);
        if(node==null){
            dummyHead.next = new Node(key, value, dummyHead.next);
        }
        else{
            node.value = value;
        }
    }

    @Override
    public void set(K key, V value){
        Node node = getNode(key);
        if(node==null){
            throw new IllegalArgumentException("Key doesn't exist.");
        }
        node.value = value;
    }

    @Override
    public V remove(K key){

        Node prev = dummyHead;
        while(prev.next!=null){
            if(key.equals(prev.next.key)){
                break;
            }
            prev = prev.next;
        }

        if(prev.next.key!=null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

}
