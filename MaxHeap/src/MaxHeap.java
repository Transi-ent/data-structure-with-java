public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    /*
    Heapify:---将一个数组直接变成堆的一种操作，复杂度 O(n)，而直接向一个空堆中逐个添加元素
    的复杂度为O(nlogn);
    ===== Procedure =====
    1), 找到第一个非叶子节点；
    2), 从该节点出发，向堆顶，依次将所有的节点 shiftDown.

     */
    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        for(int i=parent(data.getSize()-1); i>=0; i--){
            shiftDown(i);
        }
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    private int parent(int index){
        if(index==0){
            throw new IllegalArgumentException("Index-0 doesn't have any child.");
        }
        return (index-1)/2;
    }

    private int leftChild(int index){
        return 2*index + 1;
    }

    private int rightChild(int index){
        return 2*index +2;
    }

    // Add element into the heap
    public void add(E e){
        data.addLast(e);
        shiftUp(data.getSize()-1);
    }

    /*
    shiftUp 是 Heap 的核心操作，heap是一个完全二叉树（除去最后一层，肯定是满二叉树，且叶子节
    点在最后一层会尽量靠近左侧；且该子树的每个节点都肯定小于父节点），每次添加一个新的元素都会被
    添加到该完全二叉树的最后一层最后一个叶子节点的右侧，本层放满就会放到下一层，但是为了维护HEAP的性质
    必须进行shiftUp，而且比较的时间复杂度为O(logn) instead of O(n).
     */
    private void shiftUp(int k){

        // continue the loop only if k>0 and data[k]>data[parent[k]]
        // ...
        while(k>0 && data.get(k).compareTo(data.get(parent(k)))>0){
            data.swap(k, parent(k));
            k = parent(k);//update k after swap value.
        }
    }

    public E findMax(){
        if(data.getSize()==0){
            throw new IllegalArgumentException("Heap is empty.");
        }

        return data.get(0);
    }


    public E extractMax(){
        E ret = findMax();

        data.swap(0, data.getSize()-1);
        data.removeLast();
        shiftDown(0);
        return ret;
    }

    /*
ShiftDown 是删除方法的核心操作，对于Heap（优先队列），每次出队的都是最大值，为了维护堆的性质
，同时保证较高的性能-----
1，return the fiest lement, swap(firstElment, lastElement);
2,delete the last element of heap, and maintain --- data;
3, shiftdown the INSERTED element until Heap property R reached.
===== About ShiftDown =====
1) 得确保自己有孩子，否则shiftDown后就越界了；
2) 看自己有几个孩子，跟较大的孩子shiftDown;
     */
    private void shiftDown(int k){

        while(leftChild(k)<data.getSize()){

            int j = leftChild(k);
            if(j+1<data.getSize() && data.get(j+1).compareTo(data.get(j))>0){
                j = j+1;
            }
            if(data.get(k).compareTo(data.get(j))>0){
                break;
            }

            data.swap(k, j);
            k = j;
        }
    }

    // 将堆顶元素与一个外部元素进行替换
    public E replace(E e){
        E ret = findMax();
        data.set(0, e);
        shiftDown(0);
        return ret;
    }


}
