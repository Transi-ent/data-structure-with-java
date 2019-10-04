import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("pride-and-prejudice");
        ArrayList<String> list = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt",list)){
            System.out.println("Total size: "+list.size());

            Set<String> bstSet = new BSTSet<>();
            for(String word: list){
                bstSet.add(word);
            }
            System.out.println("Unique size: "+bstSet.getSize());
        }
    }
}
