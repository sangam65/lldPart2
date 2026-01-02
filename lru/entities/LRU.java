package lru.entities;

import java.util.HashMap;

import lru.exception.KeyNotFoundException;

public class LRU<K, V> {
    private final DoublyLinkedList<K, V> doublyLinkedList;
    private HashMap<K, Node<K, V>> hashMap;
    private int capacity;

    public LRU(int capacity) {
        this.doublyLinkedList = new DoublyLinkedList<>();
        this.capacity = capacity;
        this.hashMap = new HashMap<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public synchronized V getValue(K key) {

        Node<K, V> node = getNodeFromKey(key);
        doublyLinkedList.moveToFront(node);
        return node.getValue();

    }

    private Node<K, V> getNodeFromKey(K key) throws KeyNotFoundException {
        if (!hashMap.containsKey(key)) {
            throw new KeyNotFoundException();
        }
        Node<K, V> node = hashMap.get(key);
        return node;
    }

    public void addValue(K key, V value) {
        if (hashMap.containsKey(key)) {
            removeKey(key);
        }
        if (hashMap.size() == capacity) {
            Node<K, V> last = doublyLinkedList.getLast();
            removeKey(last.getKey());

        }

        Node<K, V> newNode = doublyLinkedList.addFirst(key, value);
        hashMap.put(key, newNode);
    }

    public synchronized void removeKey(K key) {

        Node<K, V> node = getNodeFromKey(key);
        hashMap.remove(key);
        System.out.println("Key removed " + key);
        doublyLinkedList.remove(node);

    }

}
