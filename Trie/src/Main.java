public class Main {

    public static void main(String[] args) {
	// write your code here

        Trie trie = new Trie();
        trie.add("ok");
        trie.add("panda");
        trie.add("shit");
        System.out.println(trie.getSize());
        System.out.println(trie.contains("pan"));
        System.out.println(trie.contains("panda"));
    }
}
