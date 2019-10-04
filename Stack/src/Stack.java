public interface Stack<E> {

    E pop();
    void push(E e);
    E peek();
    boolean isEmpty();
    int getSize();
}
