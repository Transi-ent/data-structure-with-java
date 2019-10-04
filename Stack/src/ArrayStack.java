public class ArrayStack<E> implements Stack<E> {

    Array<E> array;

    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayStack(){
        array = new Array<>();
    }

    @Override
    public int getSize(){
        return array.getSize();
    }

    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    @Override
    public E pop(){
        return array.removeLast();
    }

    @Override
    public void push(E e){
        array.addLast(e);
    }

    @Override
    public E peek(){
        return array.get(array.getSize()-1);
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Stack "));
        res.append("[ ");
        for (int i=0; i<array.getSize(); i++){
            res.append(array.get(i));
            if(i != array.getSize()-1){
                res.append(", ");
            }
        }
        res.append(" ] top");
        return res.toString();
    }
}
