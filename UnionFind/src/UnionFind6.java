/*
 基于路径压缩的优化--- parent(p) = parent(parent(p));
 */
public class UnionFind6 implements UF {

    private int[] parent;
    private int[] rank;

    public UnionFind6(int size){
        parent = new int[size];
        rank = new int[size];

        for(int i=0; i<parent.length; i++){
            parent[i] = i;
            rank[i] = 1;// rank[i] SF level number of the tree
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    private int find(int p){
        if(p<0 || p>=parent.length){
            throw new IllegalArgumentException("Illegal index.");
        }

        if(p != parent[p]){ // 如果p不是根节点，则向下一层递归，注意
            // 并查集是由子节点指向父节点
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot==qRoot){
            return ;
        }

        if(rank[pRoot]<rank[qRoot]) {
            parent[pRoot] = qRoot;
        }
        else if(rank[pRoot]>rank[qRoot]){
            parent[qRoot] = pRoot;
        }
        else{
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}