import java.io.ObjectOutputStream;

public class Array<E> {

    private E[] data;
    private int size;

    public Array(int capacity){
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array(){
        data = (E[])new Object[10];
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void addLast(E e){
        add(size, e);
    }

    public void addFirst(E e){
        add(0, e);
    }

    public void add(int index, E e){
        if(size>=data.length){
            resize(2*getCapacity());
        }
        if (index<0 || index>size){
            throw new IllegalArgumentException("add failed, illegal index");
        }

        for(int i=size; i>index; i--){
            data[i] = data[i-1];
        }
        data[index] = e;
        size++;
    }

    public E get(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Get failed, illegal index.");
        }
        return data[index];
    }

    public void set(int index, E e){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Set failed, illegal index.");
        }
        data[index] = e;
    }

    public boolean contains(int e){
        for (int i=0; i<size; i++){
            if(data[i].equals(e)){
                return true;
            }
        }

        return false;
    }

    // 找不到就返回一个非法的索引
    public int find(E e){
        for (int i=0; i<size; i++){
            if(data[i].equals(e)){
                return i;
            }
        }

        return -1;
    }

    public E removeLast(){
        return remove(size-1);
    }

    public E removeFirst(){
        return remove(0);
    }

    public E remove(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Remove failed, illegal index.");
        }
        E ret = data[index];
        for(int i=index+1; i<size; i++){
            data[i-1] = data[i];
        }
        size--; //TODO: 维护 size
        data[size] = null;

        if(size == data.length/4 && data.length/2!=0){
            resize(data.length/2);
        }
        return ret;
    }

    public void removeElement(E e){
        int index = find(e);
        if(index != -1){
            remove(index);
        }
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for(int i=0; i<size; i++){
            newData[i] = data[i];
        }

        data = newData;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n",size,data.length));
        res.append("[");
        for(int i=0; i<size; i++){
            res.append(data[i]);
            if(i!=size-1){
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }



}
