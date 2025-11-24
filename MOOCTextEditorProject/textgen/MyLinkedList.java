package textgen;

import java.util.AbstractList;

public class MyLinkedList<E> extends AbstractList<E> {

    LLNode<E> head;
    LLNode<E> tail;
    int size;

    public MyLinkedList() {
        head = new LLNode<>(null);
        tail = new LLNode<>(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
        LLNode<E> current = head.next;
        for(int i = 0; i < index; i++) current = current.next;
        return current.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E element) {
        if(element == null) throw new NullPointerException();
        LLNode<E> newNode = new LLNode<>(element);
        LLNode<E> prevNode = tail.prev;

        prevNode.next = newNode;
        newNode.prev = prevNode;
        newNode.next = tail;
        tail.prev = newNode;

        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if(element == null) throw new NullPointerException();
        if(index < 0 || index > size) throw new IndexOutOfBoundsException();
        LLNode<E> newNode = new LLNode<>(element);
        LLNode<E> current = head;
        for(int i = 0; i < index; i++) current = current.next;
        LLNode<E> nextNode = current.next;

        current.next = newNode;
        newNode.prev = current;
        newNode.next = nextNode;
        nextNode.prev = newNode;

        size++;
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
        LLNode<E> current = head.next;
        for(int i = 0; i < index; i++) current = current.next;
        E removedData = current.data;

        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return removedData;
    }

    @Override
    public E set(int index, E element) {
        if(element == null) throw new NullPointerException();
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
        LLNode<E> current = head.next;
        for(int i = 0; i < index; i++) current = current.next;
        E old = current.data;
        current.data = element;
        return old;
    }
}
