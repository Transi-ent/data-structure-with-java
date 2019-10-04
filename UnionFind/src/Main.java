import java.util.Random;

public class Main {

    private static double testUF(UF uf, int m){

        int size = uf.getSize();
        Random random = new Random();

        long start = System.nanoTime();

        for(int i=0; i<m; i++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for(int i=0; i<m; i++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long end = System.nanoTime();
        return (end-start)/1000000000.0;
    }

    public static void main(String[] args) {
	// write your code here

        int m=5000000;
        int size = 10000000;

//        UnionFind1 uf1 = new UnionFind1(size);
//        System.out.println("Running Time for UF1:"+testUF(uf1,m));
//
//        UnionFind2 uf2 = new UnionFind2(size);
//        System.out.println("Running Time for UF2:"+testUF(uf2,m));

        UnionFind3 uf3 = new UnionFind3(size);
        System.out.println("Running Time for UF3:"+testUF(uf3,m));

        UnionFind4 uf4 = new UnionFind4(size);
        System.out.println("Running Time for UF4:"+testUF(uf4,m));

        UnionFind5 uf5 = new UnionFind5(size);
        System.out.println("Running Time for UF5:"+testUF(uf5,m));

        UnionFind6 uf6 = new UnionFind6(size);
        System.out.println("Running Time for UF6:"+testUF(uf6,m));

    }
}

