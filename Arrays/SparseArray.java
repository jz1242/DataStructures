import java.util.Iterator;

/**SparseArray class that creates a linkedlist. I would
 * prefer sparse array because
 * you will have an easier time changing and accessing
 * a linkedlist compared to the simple array.
 * @author jzhan127
 *
 * @param <T> it is generic
 */
public class SparseArray<T> implements Array<T> {
    /**this class creates a node.
     * @param <T> it is of generic type
     */
    private static final class Node<T> {
        /**data of a generic type.
         */
        T data;
        /**gets next node.
         */
        Node<T> next;
        /**gives index of node.
         */
        private int index;

        /** creates a node.
         * @param t variable of type t
         * @param next gets next node
         * @param i gets the index
         */
        private Node(T t, Node<T> next, int i) {
            this.data = t;
            this.next = next;
            this.index = i;
        }
    }

    /**gets the length of array.
     */
    private int length;
    /**gets the node of first.
     */
    private Node<T> first;
    /**gets the node of the last.
     */
    private Node<T> last;
    /**sets the initial value to this.
     */
    private T initial;
    /**has the current node.
     */
    private Node<T> hold;

    /**counts the total number of nodes.
     */
    private int countNodes;

    /**constructor of sparse array.
     * @param t the type of input
     * @param len the length
     */
    public SparseArray(int len, T t) {
        this.length = len;
        this.last = new Node<T>(t, null, len);
        this.first = new Node<T>(t, last, len);
        this.initial = t;
        this.hold = new Node<T>(t, last, len);
        this.countNodes = 0;

    }
    /**class for the Iterator.
     */

    private class SparseArrayIterator implements Iterator<T> {
        // Current position in the basic Java array.
        //int current;
        /**stores where the iterator currently is.
         */
        Node<T> iteratorCurrent;
        /**constructor to set the iterator.
         */

        SparseArrayIterator() {
            this.iteratorCurrent = SparseArray.this.first;
        }

        @Override
        public T next() {
            T t = this.iteratorCurrent.next.data;
            this.iteratorCurrent = this.iteratorCurrent.next;
            return t;
        }

        @Override
        public boolean hasNext() {
            return this.iteratorCurrent.next != last;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new SparseArrayIterator();
    }

    @Override
    public void put(int i, T t) throws IndexException {
        // TODO Auto-generated method stub
        if (i < 0 || i >= length) {
            throw new IndexException();
        }
        if (countNodes == 0) {
            Node<T> s = new Node<T>(t, last, i);
            hold = s;
            first.next = s;
            countNodes++;
        }
        else if (i > hold.index) {
            Node<T> temp = hold;
            while (hold.next != null && i > hold.next.index) {
                hold = hold.next;
            }
            if (hold.index == i) {
                hold.data = t;
            }
            else if (hold.next == null){
                Node<T> s = new Node<T>(t, last, i);
                hold.next = s;
            }
            else {
                Node<T> s = new Node<T>(t, hold.next, i);
                hold.next = s;
            }
            hold = temp;
            countNodes++;
        }
        else if (i < hold.index) {
            Node<T> s = new Node<T>(t, hold, i);
            first.next = s;
            hold = s;
            countNodes++;
        }
        else if (i == hold.index){
            hold.data = t;
        }
    }

    @Override
    public T get(int i) throws IndexException {
        // TODO Auto-generated method stuB
        if (i < 0 || i >= length) {
            throw new IndexException();
        }
        Node<T> temp = hold;
        T val = null;

        while (hold.next != null && hold.index < i) {
            hold = hold.next;
        }

        if (hold.index == i) {
            val = hold.data;
        }
        if (hold.index != i) {
            hold = temp;
            return initial;
        }
        hold = temp;
        return val;
    }

    @Override
    public int length() {
        // TODO Auto-generated method stub
        return length;
    }

}
