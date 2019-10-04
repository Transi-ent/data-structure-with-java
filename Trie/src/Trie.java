import java.util.TreeMap;

public class Trie {

    private class Node{

        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public void add(String word){
        Node cur = root;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            if(!cur.next.keySet().contains(ch)){
                cur.next.put(ch, new Node());
            }
            cur = cur.next.get(ch);
        }

        if(!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    public boolean contains(String word){
        Node cur = root;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            if(!cur.next.keySet().contains(ch)){
                return false;
            }
            cur = cur.next.get(ch);
        }
        return cur.isWord;
    }

    public boolean isPrefix(String prefix){
        Node cur = root;
        for(int i=0; i<prefix.length(); i++){
            char ch = prefix.charAt(i);
            if(!cur.next.keySet().contains(ch)){
                return false;
            }
            cur = cur.next.get(ch);
        }
        return true;
    }

    /*
    Search whether the WORD is in the Trie, and a '.' can match
    any character.
     */
    public boolean search(String word){

        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index){

        if(index==word.length()){ // when recurse to the end.
            return node.isWord;
        }

        // Not recurse to the end yet
        char ch = word.charAt(index);
        if(ch != '.') {
            if (!node.next.keySet().contains(ch)) {
                return false;
            }
            match(node.next.get(ch), word, index+1);
        }else{
            for(char nextChar: node.next.keySet()){
                if(match(node.next.get(nextChar), word, index+1)){
                    return true;
                }
            }
            return false;
        }
    }

}
