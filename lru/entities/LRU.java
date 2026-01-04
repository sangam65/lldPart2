package lru.entities;

import java.util.HashMap;

import lru.exception.CapacityConstrainException;
import lru.exception.KeyNotFoundException;

public class LRU<K, V> {
    private final DoublyLinkedList<K, V> doublyLinkedList;
    private final HashMap<K, Node<K, V>> hashMap;
    private int capacity;

    public LRU(int capacity) {
        if (capacity <= 0) {
            throw new CapacityConstrainException("Capacity can't be zero or less than 0");
        }
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

    public synchronized void addValue(K key, V value) {
        if (hashMap.containsKey(key)) {
            removeKey(key);
        }
        if (hashMap.size() == capacity) {
            Node<K, V> last = doublyLinkedList.getLast();
            if (last != null) {
                doublyLinkedList.remove(last);
                hashMap.remove(last.getKey());
            }
            else{
                throw new CapacityConstrainException("can't add new node capacity has reached");
            }

        }

        Node<K, V> newNode = doublyLinkedList.createNode(key, value);
        doublyLinkedList.addFirst(newNode);
        hashMap.put(key, newNode);
    }

    public synchronized void removeKey(K key) {

        Node<K, V> node = getNodeFromKey(key);
        hashMap.remove(key);
        System.out.println("Key removed " + key);
        doublyLinkedList.remove(node);

    }

}
