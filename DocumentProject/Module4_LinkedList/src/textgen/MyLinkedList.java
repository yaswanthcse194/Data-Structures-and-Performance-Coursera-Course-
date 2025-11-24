package textgen;

import java.util.AbstractList;

public class MyLinkedList<E> extends AbstractList<E> {

    /** Node class for the linked list */
    class LLNode {
        E data;
        LLNode prev;
        LLNode next;

        LLNode(E data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private LLNode head;  // sentinel head
    private LLNode tail;  // sentinel tail
    private int size;

    /** Constructor */
    public MyLinkedList() {
        head = new LLNode(null); // sentinel head
        tail = new LLNode(null); // sentinel tail
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /** Returns the size of the list */
    @Override
    public int size() {
        return size;
    }

    /** Get element at index */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        LLNode current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /** Add element at end */
    @Override
    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException("Cannot add null element");
        }
        LLNode newNode = new LLNode(element);
        LLNode prevNode = tail.prev;

        prevNode.next = newNode;
        newNode.prev = prevNode;
        newNode.next = tail;
        tail.prev = newNode;

        size++;
        return true;
    }
}
