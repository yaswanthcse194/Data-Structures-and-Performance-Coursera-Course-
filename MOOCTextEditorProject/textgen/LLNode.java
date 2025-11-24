package textgen;

class LLNode<E> {
    E data;
    LLNode<E> prev;
    LLNode<E> next;

    public LLNode(E e) {
        data = e;
        prev = null;
        next = null;
    }
}

