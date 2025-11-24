package textgen;

public class LLNode<E> {
    protected E data;
    protected LLNode<E> prev;
    protected LLNode<E> next;

    public LLNode(E data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
