package lru.entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lru.exception.CapacityConstrainException;
import lru.exception.KeyNotFoundException;
import lru.exception.NodeNullException;

public class LRU<K, V> {
    private final DoublyLinkedList<K, V> doublyLinkedList;
    private final Map<K, Node<K, V>> hashMap;
    private int capacity;
    private int size;

    public int getSize() {
        return size;
    }

    public LRU(int capacity) {
        if (capacity <= 0) {
            throw new CapacityConstrainException("Capacity can't be zero or less than 0");
        }
        this.size=0;
        this.doublyLinkedList = new DoublyLinkedList<>();
        this.capacity = capacity;
        this.hashMap = new ConcurrentHashMap<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public V getValue(K key) {

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

    public void addValue(K key, V value)
            throws NodeNullException, CapacityConstrainException, KeyNotFoundException {

            // if key exists already
            if (hashMap.containsKey(key)) {
                Node<K, V> existingNode = hashMap.get(key);
                existingNode.setValue(value);
                hashMap.put(key, existingNode);
                doublyLinkedList.moveToFront(existingNode);
                this.size++;
                return;
            }
            // if capacity reached then remove last node
            if (this.size == capacity) {
                Node<K, V> last = doublyLinkedList.getLast();
                if (last != null) {
                    
                    doublyLinkedList.remove(last);
                    hashMap.remove(last.getKey());
                    this.size--;
                } else {
                    throw new CapacityConstrainException("can't add new node capacity has reached");
                }

            }

            // add the new node
            Node<K, V> newNode = doublyLinkedList.createNode(key, value);
            doublyLinkedList.addFirst(newNode);
            hashMap.put(key, newNode);
            this.size++;
        

    }

    public  void removeKey(K key) throws KeyNotFoundException{

        Node<K, V> node = getNodeFromKey(key);
        hashMap.remove(key);
        System.out.println("Key removed " + key);
        doublyLinkedList.remove(node);
        this.size--;

    }

}
