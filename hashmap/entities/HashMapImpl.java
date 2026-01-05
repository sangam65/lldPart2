package hashmap.entities;



import hashmap.exception.KeyException;

public class HashMapImpl<K, V> {
   

    private int capacity;
    private int size;
    private Pair<K,V> arrays[];

    @SuppressWarnings("unchecked")
    public HashMapImpl  () {
        System.out.println("object creation through constructor");
        this.capacity = 4;
        this.size = 0;
        this.arrays = new Pair[1<<this.capacity];
        System.out.println("value {}"+this.arrays[1]);

    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return 1 << capacity;
    }
    // private

    public void put(K key, V value) {
        Pair<K,V> oldPair = indexOfkeyExists(key);
        if (oldPair != null) {

            oldPair.setV(value);
            return;
        } else {

            Pair<K,V> newPair = new Pair<K,V>(key, value);
            int newInd = calculateKey(key);

            if(arrays[newInd]==null){
                arrays[newInd]=newPair;
            }
            else{
                Pair<K,V> curPair=arrays[newInd];
                while(curPair.getNext()!=null){
                    curPair=curPair.next;
                }
                curPair.setNext(newPair);
            }
            this.size++;

        }
    }

    public V getValue(K key) {
        Pair<K,V> pair = indexOfkeyExists(key);
        if (pair == null) {
            throw new KeyException("key does not exist");
        }

        return pair.getV();
    }

    public void removeKey(K key) {
        Pair<K,V> pair = indexOfkeyExists(key);
        
        if (pair == null) {
            throw new KeyException("key does not exist");
        }
        int ind=calculateKey(key);
        Pair<K,V> prevPair=arrays[ind];
        if(prevPair==pair){
            arrays[ind]=null;
            return;
        }
        while(prevPair.next!=pair){
            prevPair=prevPair.next;
        }
        prevPair.setNext(null);

        // arrays.set(ind, null);

        // have to remove this pair
    }

    private int calculateKey(K key) {
        int totalCap = 1 << capacity;
        int keyCode = key.hashCode();
        int ind = keyCode % totalCap;
        System.out.println("key "+key+" keyCode "+keyCode);
        return ind;
    }

    private Pair<K,V> indexOfkeyExists(K key) {
        int ind = calculateKey(key);

        Pair<K,V> pair = arrays[ind];
        while (pair != null) {
            if (pair.getK().equals(key)) {
                return pair;
            }
            pair = pair.getNext();

        }
        return null;
    }


     @SuppressWarnings("hiding")
    private class Pair<K,V> {
        private final K k;
        private V v;
        private Pair<K,V> next;

        public K getK() {
            return k;
        }

        public Pair<K,V> getNext() {
            return next;
        }

        public void setNext(Pair<K,V> next) {
            this.next = next;
        }

        public Pair(K k, V v) {
            this.k = k;
            this.v = v;
            this.next = null;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }

    }
}
