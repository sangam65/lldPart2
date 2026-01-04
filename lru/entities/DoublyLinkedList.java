package lru.entities;

import lru.exception.NodeNullException;

public class DoublyLinkedList<K, V> {
    private final Node<K, V> head;
    private final Node<K, V> tail;

    public DoublyLinkedList() {
        this.head = new Node<K, V>(null, null);
        this.tail = new Node<K, V>(null, null);
        this.head.setNext(tail);
        this.tail.setPrev(head);

    }

    public void addFirst(Node<K,V>node) {
        if(head==null){
            throw new NodeNullException("head node is null");
        }
        Node<K, V> headNext = head.getNext();
        head.setNext(node);
        node.setNext(headNext);
        node.setPrev(head);
        headNext.setPrev(node);
      

    }

    public void remove(Node<K, V> node) {
        node.getNext().setPrev(node.getPrev());
        node.getPrev().setNext(node.getNext());
        
    }

    public void moveToFront(Node<K, V> node) {
        remove(node);
        addFirst(node);
    }

    public Node<K, V> getLast() {
        if(tail==null){
               throw new NodeNullException("tail node is null");
        }
        if (tail.getPrev() != head) {
            return tail.getPrev();
        }
        return null;
    }

    public Node<K, V> createNode(K key, V value) {
        return new Node<K, V>(key, value);
    }
}
