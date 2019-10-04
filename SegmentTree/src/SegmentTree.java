public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger; // merger function could be  defined by user

        data =(E[]) new Object[arr.length];
        for(int i=0; i<arr.length; i++){
            data[i] = arr[i];
        }

        tree = (E[])new Object[4*arr.length];
        buildSegmentTree(0, 0, data.length-1);
    }

    //在位置 treeIndex 处创建表示区间[l, r]的线段树。
    private void buildSegmentTree(int treeIndex, int l, int r){
        if(l==r){
            tree[treeIndex] = data[r];
            return ; // Recurse to the bottom already.
        }

        int mid = l + (r-l)/2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        buildSegmentTree(leftIndex, l, mid);
        buildSegmentTree(rightIndex, mid+1, r);

        tree[treeIndex] = merger.merge(tree[leftIndex], tree[rightIndex]);
    }

    public int getSIze(){
        return data.length;
    }

    public E get(int index){
        if(index<0 || index>=data.length){
            throw new IllegalArgumentException("Illegal index.");
        }
        return data[index];
    }

    private int leftChild(int index){
        if(index<0 || index>=data.length){
            throw new IllegalArgumentException("Illegal index.");
        }
        return 2*index+1;
    }

    private int rightChild(int index){
        if(index<0 || index>=data.length){
            throw new IllegalArgumentException("Illegal index.");
        }
        return 2*index+2;
    }

    // 返回区间 [queryL, queryR]之间的值
    public E query(int queryL, int queryR){
        if(queryL<0 || queryL>data.length || queryR<0
                || queryR>data.length || queryL>queryR){
            throw new IllegalArgumentException("Illegal index. ");
        }
        return query(0, 0, data.length-1, queryL, queryR);
    }

    //在以 treeIndex 为根节点线段树[l, r]的整体区间内，搜索区间[queryL, queryR]
    // ...内的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR){

        if(l==queryL && r==queryR){
            return tree[treeIndex];// query interval equals to the
            // one sub-interval of segment tree exactly.
        }

        int mid = l+(r-l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if(queryL>=mid+1){// query 区间完全落入右面的子区间；
            return query(rightTreeIndex, mid+1, r, queryL, queryR);
        }
        if(queryR<=mid){// query 区间完全落入左边的子区间；
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }
        // query 区间在左右子区间都有；
        E leftRes = query(leftTreeIndex, l, mid, queryL, mid);
        E rightRes = query(rightTreeIndex, mid+1, r, mid+1, queryR);
        return merger.merge(leftRes, rightRes);
    }

    public void set(int index, E e){
        if(index<0 || index>=data.length){
            throw new IllegalArgumentException("Index illegal.");
        }

        set(0, 0, data.length, index, e);
    }

    // update the value whose index is INDEX as e in the segmentTree
    // ...with root-treeIndex
    private void set(int treeIndex, int l, int r, int index, E e){
        if(l==r){
            tree[treeIndex] = e;
            return ;
        }

        int mid = l+(r-l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if(index>=mid+1){// search in the right half interval
            set(rightTreeIndex, mid+1, r, index, e);
        }else{ // search in the left half interval
            set(leftTreeIndex, l, mid, index, e);
        }

        // 在更新完指定的节点值后，还要对其上层所有的节点值进行更新。
        // 留意递归调用函数所在位置对函数语句执行的影响。
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("[ ");
        for(int i=0; i<tree.length; i++){
            res.append(tree[i]);
            if(i!=tree.length-1){
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }



}

/*
给自己设立过高的道德标准是自己放不开手脚的最大地方，就像是两个拳击手，对方不可以攻击
我的1个地方，但是我却不可以攻击对方的3个地方，这也是你最傻的地方。别给自己戴道德高
帽子，更别让别人给你戴，那些更是一些别有用心的人，先用甜言蜜语麻痹你，然后利用你，
压榨你。
1），对面的老阿姨不会就是昨天那个吧！
2），如果今天变得安静，不在无理取闹，就进一步证明他昨天真的在无理取闹，证明她昨天
在演戏。
3), Just caugh, not shit or fuck, stand with it, pay attention to yourself.
 */