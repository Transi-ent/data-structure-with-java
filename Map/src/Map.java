public interface Map<K, V> {

    void add(K key, V value);
    V get(K key);
    V remove(K key);
    void set(K key, V newValue);
    boolean contains(K key);
    boolean isEmpty();
    int getSize();
}
