package lru.entities;

public class Node <K,V>{
    private final K key;
    public K getKey() {
        return key;
    }
    private V value;
    private Node<K,V> next;
    private Node<K,V> prev;
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
    public Node<K,V> getNext() {
        return next;
    }
    public void setNext(Node<K,V> next) {
        this.next = next;
    }
    public Node<K,V> getPrev() {
        return prev;
    }
    public void setPrev(Node<K,V> prev) {
        this.prev = prev;
    }
    public Node(K key,V value){
        this.key=key;
        this.value=value;
        this.next=null;
        this.prev=null;
    }
}
