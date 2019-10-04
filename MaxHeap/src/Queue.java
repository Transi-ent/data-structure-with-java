public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    E dequeue();
    void enqueue(E e);
    E getFront();
}
