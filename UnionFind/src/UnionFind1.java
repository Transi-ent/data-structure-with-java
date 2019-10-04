public class UnionFind1 implements UF {

    private int[] id;

    public UnionFind1(int size){
        id = new int[size];

        for(int i=0; i<id.length; i++){
            id[i] = i;
        }
    }

    @Override
    public int getSize(){
        return id.length;
    }

    private int find(int p){
        if(p<0 || p>=id.length){
            throw new IllegalArgumentException("Illegal index.");
        }
        return id[p];
    }

    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    public void unionElements(int p, int q){

        int pID = find(p);
        int qID = find(q);

        if(pID == qID){ //if p and q are already connected.
            return ;
        }
        for(int i=0; i<id.length; i++){
            if(id[i]==pID){
                id[i] = qID;
            }
        }
    }
}

/*
老是吞口水确实很尴尬；

 */