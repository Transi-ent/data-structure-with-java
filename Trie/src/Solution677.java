import java.util.TreeMap;

public class Solution677 {


    private class Node{

        public int value;
        public TreeMap<Character, Node> next;

        public Node(int val){
            this.value = val;
            next = new TreeMap<>();
        }

        public Node(){
            this(0);
        }
    }

    private Node root;

    public Solution677(){
        root = new Node();
    }

    public void insert(String word, int val){
        Node cur = root;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            if(!cur.next.keySet().contains(ch)){
                cur.next.put(ch, new Node());
            }
            cur = cur.next.get(ch);
        }
        cur.value = val;
    }

    public int sum(String prefix){
        Node cur = root;
        for(int i=0; i<prefix.length(); i++){
            char ch = prefix.charAt(i);
            if(!cur.next.keySet().contains(ch)){
                return 0;
            }
            cur = cur.next.get(ch);
        }

        return sum(cur);
    }

    private int sum(Node node){

        int res = node.value;
        for(char nextChar: node.next.keySet()){
            res += sum(node.next.get(nextChar));
        }

        return res;
    }





}
