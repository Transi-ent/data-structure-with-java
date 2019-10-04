public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity){
        data = (E[])new Object[capacity+1];
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length-1;
    }

    public boolean isEmpty(){
        return size==0; // head==tail
    }

    @Override
    public int getSize(){
        return size; // tail>=front:tail-front?getCapacity()-(front-tail)
    }

    @Override
    public void enqueue(E e){

        if((tail+1)%data.length==front){
            resize(2*getCapacity());
        }
        data[tail] = e;
        tail = (tail+1) % data.length;
        size++;
    }

    @Override
    public E dequeue(){
        if(isEmpty()){
            throw new IllegalArgumentException("Dequeue failed, queue is empty.");
        }
        E ret = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        size--;
        if(size== getCapacity()/4 && getCapacity()/2!=0){
            resize(getCapacity()/2);
        }
        return ret;
    }

    @Override
    public E getFront(){
        if(isEmpty()){
            throw new IllegalArgumentException("The queue is empty.");
        }
        return data[front];
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity+1];
        for(int i=0; i<size; i++){
            newData[i] = data[(i+front)%newData.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d, capacity = %d\n",size,getCapacity()));
        res.append("front [");
        for(int i=front; i!=tail; i=(i+1)%data.length){
            res.append(data[i]);
            if((i+1)%data.length!=tail){
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }


    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>(20);
        for(int i=0; i<10; i++){
            queue.enqueue(i*10);
            System.out.println(queue);
        }

        queue.dequeue();
        System.out.println(queue);
    }

}
