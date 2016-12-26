import java.util.Iterator;
/**List that has sentinels that allow for more efficient lists.
 * @author jzhan127 JasonZhang
 * @param <T> takes in generic types
 */

public class SentinelList<T> implements List<T> {
    /**Creates a node.
     * @param <T> takes in generic type
     */
    private static final class Node<T> implements Position<T> {
        //linked list variables to keep track of where it is
        Node<T> next;
        Node<T> prev;
        T data;
        List<T> owner;

        @Override
        public T get() {
            return this.data;
        }

        @Override
        public void put(T t) {
            this.data = t;
        }
    }
    //variables to keep track of front and back of linked list and the length
    private Node<T> head;
    private Node<T> tail;
    private int length;
    /**Constructor to create the list.
     */

    public SentinelList() {
        this.head = new Node<T>();
        this.tail = new Node<T>();
        this.head.next = tail;
        this.tail.prev = head;
    }
    /**Creates the iterator for sentinelList.
     */

    private final class SentinelIterator implements Iterator<T> {
        //takes in variables for start last and if forward
        Node<T> start;
        boolean forward;
        Node<T> last;
        /**constructor for iterator.
         * @param f takes in boolean
         */

        SentinelIterator(boolean f) {
            this.forward = f;
            if (this.forward) {
                this.start = SentinelList.this.head.next;
                this.last = SentinelList.this.tail;
            } else {
                this.start = SentinelList.this.tail.prev;
                this.last = SentinelList.this.head;
            }
        }

        @Override
        public T next() {
            T t = this.start.get();
            if (this.forward) {
                this.start = this.start.next;
            } else {
                this.start = this.start.prev;
            }
            return t;
        }

        @Override
        public boolean hasNext() {
            return this.start != this.last;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this.forward();
    }

    /**Converts a position into a node.
     * @param p takes in position of generic type
     * @return a node of generic type
     * @throws PositionException exception when position is not possible
     */
    private Node<T> convert(Position<T> p) throws PositionException {
        try {
            Node<T> n = (Node<T>) p;
            if (n.owner != this) {
                throw new PositionException();
            }
            return n;
        } catch (NullPointerException | ClassCastException e) {
            throw new PositionException();
        }
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public boolean empty() {
        return length == 0;
    }

    @Override
    public Position<T> insertFront(T t) {
        Node<T> n = new Node<T>();
        n.owner = this;
        n.data = t;
        n.prev = head;
        n.next = this.head.next;
        this.head.next.prev = n;
        this.head.next = n;
        this.length++;
        return n;
    }

    @Override
    public Position<T> insertBack(T t) {
        Node<T> n = new Node<T>();
        n.owner = this;
        n.data = t;
        n.prev = this.tail.prev;
        n.next = this.tail;
        this.tail.prev.next = n;
        this.tail.prev = n;
        this.length++;
        return n;
    }

    @Override
    public Position<T> insertBefore(Position<T> p, T t)
            throws PositionException {
        Node<T> pos = this.convert(p);
        Node<T> n = new Node<T>();
        n.owner = this;
        n.data = t;
        n.prev = pos.prev;
        n.next = pos;
        pos.prev.next = n;
        pos.prev = n;
        this.length++;
        return n;
    }

    @Override
    public Position<T> insertAfter(Position<T> p, T t)
            throws PositionException {
        Node<T> pos = this.convert(p);
        Node<T> n = new Node<T>();
        n.owner = this;
        n.data = t;
        n.prev = pos;
        n.next = pos.next;
        pos.next.prev = n;
        pos.next = n;
        this.length++;
        return n;
    }

    @Override
    public void remove(Position<T> p) throws PositionException {
        Node<T> pos = this.convert(p);
        pos.owner = null;
        pos.prev.next = pos.next;
        pos.next.prev = pos.prev;
        this.length--;
    }

    @Override
    public void removeFront() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        this.head.next.owner = null;
        Node<T> after = this.head.next.next;
        after.prev = head;
        this.head.next = after;
        this.length--;
    }

    @Override
    public void removeBack() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        this.tail.prev.owner = null;
        Node<T> before = this.tail.prev.prev;
        before.next = tail;
        this.tail.prev = before;
        this.length--;
    }

    @Override
    public Position<T> front() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        return this.head.next;
    }

    @Override
    public Position<T> back() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        return this.tail.prev;
    }

    @Override
    public boolean first(Position<T> p) throws PositionException {
        return this.head.next == this.convert(p);
    }

    @Override
    public boolean last(Position<T> p) throws PositionException {
        return this.tail.prev == this.convert(p);
    }

    @Override
    public Position<T> next(Position<T> p) throws PositionException {
        return this.convert(this.convert(p).next);
    }

    @Override
    public Position<T> previous(Position<T> p) throws PositionException {
        return this.convert(this.convert(p).prev);
    }

    @Override
    public Iterator<T> forward() {
        return new SentinelIterator(true);
    }

    @Override
    public Iterator<T> backward() {
        return new SentinelIterator(false);
    }
    /**overridden toString() method.
     * @return s returns a string
     */

    public String toString() {
        String s = "[";
        for (Node<T> p = this.head.next; p != this.tail; p = p.next) {
            s += p.data;
            if (p.next != this.tail) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }

}
