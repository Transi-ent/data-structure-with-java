import java.util.TreeMap;
import java.util.HashSet;

public class HashTable<K, V> {

    HashSet s1 = new HashSet();

    private final int[] capacity =
            {
                    53,97,193,389,769,1543,3079,6151,12289,24593,
                    49157,98317,196613,393241,786433,1572869,3145739,
                    6291469,12582917,25165843,50331653,100663319,201326611,
                    402653189, 805306457
            };

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private int capacityIndex = 0;
    private TreeMap<K, V>[] hashtable;// seperate chaining
    private int M;// big prime number, to make hash value distribute uniformly
    private int size;

    public HashTable(){
        s1.
        this.M = capacity[capacityIndex];
        this.size = 0;
        hashtable = new TreeMap[M];
        for(int i=0; i<M; i++){
            hashtable[i] = new TreeMap<>();
        }
    }

    private int hash(K key){ // to get the index of hashtable
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize(){
        return size;
    }

    public void add(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        if(map.keySet().contains(key)){
            map.put(key, value);// update the cooresponding key-value
        }else{
            map.put(key, value);
            size++;  // put the new key--value pair.
        }

        if(size>M*upperTol && capacityIndex+1<capacity.length){
            capacityIndex ++;
            resize(capacity[capacityIndex]);
        }
    }

    public V remove(K key){
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if(map.containsKey(key)){
            ret = map.remove(key);
            size--;
        }

        if(size<M*lowerTol && capacityIndex-1>=0){
            capacityIndex--;
            resize(capacity[capacityIndex]);
        }
        return ret;
    }

    public void set(K key, V newVal){
        TreeMap<K, V> map = hashtable[hash(key)];
        if(!map.containsKey(key)){
            throw new IllegalArgumentException(key+" doesn't exist.");
        }
        map.put(key, newVal);
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashtable[hash(key)].get(key);
    }

    private void resize(int newM){

        TreeMap<K, V>[] newHashtable = new TreeMap[newM];
        for(int i=0; i<newM; i++){
            newHashtable[i] = new TreeMap<>();
        }

        int oldM = M;
        this.M = newM;
        for(int i=0; i<oldM; i++){
            TreeMap<K, V> map = hashtable[i];
            for(K key: map.keySet()){
                newHashtable[hash(key)].put(key, map.get(key));
            }
        }

        hashtable = newHashtable;
    }


}
