import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

class Solution {

    private class Freq implements Comparable<Freq>{

        public int e, freq;

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another){
            if(this.freq<another.freq){
                return 1;
            }else if(this.freq>another.freq){
                return -1;
            }else{
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k){

        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int num: nums){
            if(map.containsKey(num)){
                map.put(num, map.get(num)+1);
            }
            else{
                map.put(num, 1);
            }
        }

        PriorityQueue<Freq> pq = new PriorityQueue<Freq>();
        for(int key:map.keySet()){
            if(pq.getSize()<k){
                pq.enqueue(new Freq(key, map.get(key)));
            }else if(pq.getFront().freq<map.get(key)){
                pq.dequeue();
                pq.enqueue(new Freq(key, map.get(key)));
            }
        }

        LinkedList<Integer> list = new LinkedList<>();
        while(!pq.isEmpty()){
            list.add(pq.dequeue().e);
        }

        return list;
    }
}
//一些人，看得通，也看不通；
/*
1,首先，他们的确达到了他们想达到的目的，制造一些噪音，让你不爽，自己确实也因此感到了不爽
2，但是我不会跟、在一群人比谁的下限更低时，何必去跟风呢！真的去跟就中招了，我是来学习的，又不是
来跟某个人怄气的，有什么意思，到最后你可能会因此感到羞耻，自己有没有做错什么，何必会有其他的感觉
这就像小孩在比谁尿的更远，一群小偷在比谁偷的东西更值钱，
3，杀人诛心，一个人在做一些不符合原则的事情的过程中也是自己黑化的过程，这个过程很难可逆，
一旦黑化了，在洗白就更难了，阻力不仅来自自己的本心，也来自周围人对你的印象。
4，匿名表达式：
PriorityQueue<Integer> pq = new PriorityQueue<>(
        (a, b) -> map.get(a) - map.get(b)
);
5，只使用了一次，可以通过匿名类来实现
PriorityQueue<Freq> pq = new PriorityQueue<>(new Comparator<Freq>(){
@Override
public int compare(Freq a, Freq b){
return a.freq-b.freq;
}
})

 */








