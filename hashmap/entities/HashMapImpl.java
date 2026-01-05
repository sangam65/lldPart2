package hashmap.entities;

import java.util.ArrayList;
import java.util.List;

import hashmap.exception.KeyException;

public class HashMapImpl<K, V> {
    private class Pair {
        private final K k;
        private V v;
        private Pair next;

        public K getK() {
            return k;
        }

        public Pair getNext() {
            return next;
        }

        public void setNext(Pair next) {
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

    private int capacity;
    private int size;
    private List<Pair> arrays;

    public HashMapImpl() {
        System.out.println("object creation through constructor");
        this.capacity = 4;
        this.size = 0;
        this.arrays = new ArrayList<>(1 << this.capacity);

    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return 1 << capacity;
    }
    // private

    public void put(K key, V value) {
        Pair oldPair = indexOfkeyExists(key);
        if (oldPair != null) {

            oldPair.setV(value);
            return;
        } else {

            Pair newPair = new Pair(key, value);
            int newInd = calculateKey(key);

            if(arrays.get(newInd)==null){
                arrays.set(newInd, newPair);
            }
            else{
                Pair curPair=arrays.get(newInd);
                while(curPair.getNext()!=null){
                    curPair=curPair.next;
                }
                curPair.setNext(newPair);
            }
            this.size++;

        }
    }

    public V getValue(K key) {
        Pair pair = indexOfkeyExists(key);
        if (pair == null) {
            throw new KeyException("key does not exist");
        }

        return pair.getV();
    }

    public void removeKey(K key) {
        Pair pair = indexOfkeyExists(key);
        
        if (pair == null) {
            throw new KeyException("key does not exist");
        }
        int ind=calculateKey(key);
        Pair prevPair=arrays.get(ind);
        if(prevPair==pair){
            arrays.set(ind, null);
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
        return ind;
    }

    private Pair indexOfkeyExists(K key) {
        int ind = calculateKey(key);

        Pair pair = arrays.get(ind);
        while (pair != null) {
            if (pair.getK().equals(key)) {
                return pair;
            }
            pair = pair.getNext();

        }
        return null;
    }

}
