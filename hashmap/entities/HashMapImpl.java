package hashmap.entities;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import hashmap.exception.KeyException;

public class HashMapImpl<K, V> {

    private static int capacity = 4;
    private static int maxCapacity = 1 << capacity;
    private AtomicInteger size;
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private Pair<K, V>[] arrays;

    @SuppressWarnings("unchecked")
    public HashMapImpl() {
        System.out.println("object creation through constructor");

        this.size = new AtomicInteger(0);
        this.arrays = new Pair[maxCapacity];
        System.out.println("value {}" + this.arrays[1]);

    }

    public AtomicInteger getSize() {
        return size;
    }

    public int getCapacity() {
        return 1 << capacity;
    }
    // private

    public void put(K key, V value) {
        try {
            reentrantReadWriteLock.writeLock().lock();
            ;
            Pair<K, V> oldPair = indexOfkeyExists(key);
            if (oldPair != null) {

                oldPair.setV(value);
                return;
            } else {
                capapcityReached();
                Pair<K, V> newPair = new Pair<K, V>(key, value);
                int newInd = calculateKey(key);

                if (arrays[newInd] == null) {
                    arrays[newInd] = newPair;
                } else {
                    Pair<K, V> curPair = arrays[newInd];
                    while (curPair.getNext() != null) {
                        curPair = curPair.next;
                    }
                    curPair.setNext(newPair);
                }
                this.size.incrementAndGet();

            }
        }

        finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

    }

    public V getValue(K key) {
        try {
            reentrantReadWriteLock.readLock().lock();
            Pair<K, V> pair = indexOfkeyExists(key);
            if (pair == null) {
                throw new KeyException("key does not exist");
            }

            return pair.getV();
        }

        finally {
            reentrantReadWriteLock.readLock().unlock();
        }

    }

    public void removeKey(K key) {
        try {
            reentrantReadWriteLock.writeLock().lock();
            Pair<K, V> pair = indexOfkeyExists(key);

            if (pair == null) {
                throw new KeyException("key does not exist");
            }
            int ind = calculateKey(key);
            Pair<K, V> prevPair = arrays[ind];
            if (prevPair == pair) {
                arrays[ind] = null;
                this.size.decrementAndGet();
                return;
            }
            while (prevPair.next != pair) {
                prevPair = prevPair.next;
            }
            prevPair.setNext(pair.getNext());
            this.size.decrementAndGet();

        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

        // arrays.set(ind, null);

        // have to remove this pair
    }

    private int calculateKey(K key) {

        int keyCode = Math.abs(key.hashCode());
        int ind = keyCode & (maxCapacity - 1);
        System.out.println("key " + key + " keyCode " + keyCode);
        return ind;
    }

    private Pair<K, V> indexOfkeyExists(K key) {
        int ind = calculateKey(key);

        Pair<K, V> pair = arrays[ind];
        while (pair != null) {
            if (pair.getK().equals(key)) {
                return pair;
            }
            pair = pair.getNext();

        }
        return null;
    }

    private void capapcityReached() {
        int curSize = this.size.get();
        if (maxCapacity * 3 != curSize * 4) {
            System.out.println("Not 75% filled so doing nothing");
            return;
        }
        int oldMaxCapacity = maxCapacity;
        capacity++;
        maxCapacity = 1 << capacity;
        @SuppressWarnings("unchecked")
        Pair<K, V>[] newArrays = new Pair[maxCapacity];
        for (int i = 0; i < oldMaxCapacity; i++) {
            Pair<K, V> current = arrays[i];

            while (current != null) {
                Pair<K, V> nextNode = current.getNext();

                int newInd = calculateKey(current.getK());

                current.setNext(newArrays[newInd]);
                newArrays[newInd] = current;

                current = nextNode;
            }
        }
        this.arrays = newArrays;
        System.out.println("75% filled so doubling size");

    }

    @SuppressWarnings("hiding")
    private class Pair<K, V> {
        private final K k;
        private V v;
        private Pair<K, V> next;

        public K getK() {
            return k;
        }

        public Pair<K, V> getNext() {
            return next;
        }

        public void setNext(Pair<K, V> next) {
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
