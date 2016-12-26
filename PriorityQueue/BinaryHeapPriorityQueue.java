import java.awt.List;
import java.util.ArrayList;
import java.util.Comparator;

/**Jason Zhang jzhan127 PQ that uses a binary heap.
 * @param <T> comparator
 */
public class BinaryHeapPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueue<T> {

    /**Comparator created.
     * @param <T> comparator
     */
    private static class DefaultComparator<T extends Comparable<? super T>>
        implements Comparator<T> {
        /**compare method.
         * @param t2 parameter
         * @param t1 parameter
         * @return integer for compare
         */
        public int compare(T t1, T t2) {
            return t1.compareTo(t2);
        }
    }

    /**holds data.
     */
    private ArrayList<T> data;
    /**is a comparator.
     */
    private Comparator<T> cmp;

    /**
    * A sorted array using the "natural" ordering of T.
    */
    @SuppressWarnings("unchecked")
    public BinaryHeapPriorityQueue() {
        this((Comparator<T>) new DefaultComparator<>());
    }

    /**
    * A sorted array using the given comparator for T.
    * @param cmp Comparator to use.
    */
    public BinaryHeapPriorityQueue(Comparator<T> cmp) {
        this.data = new ArrayList<T>();
        this.data.add(null);
        this.cmp = cmp;
    }

    /**method is less.
     * @param i integer
     * @param j integer
     * @return boolean
     */
    private boolean less(int i, int j) {
        return this.cmp.compare(this.data.get(i), this.data.get(j)) < 0;
    }

    /**method to see where to insert data.
     * @param i intger
     */
    private void check(int i) {
        while (i > 1 && less(i, i / 2)) {
            T temp = this.data.get(i);
            this.data.set(i, this.data.get(i / 2));
            this.data.set(i / 2, temp);
            i = i / 2;
        }
    }
    /**swaps i and j.
     * @param i int
     * @param j int
     */

    private void change(int i, int j) {
        T temp = this.data.get(i);
        this.data.set(i, this.data.get(j));
        this.data.set(j, temp);
    }
    /**remakes the heap after remove.
     * @param i integer
     */

    private void remakeHeap(int i) {
        boolean increment = false;
        while (2 * i <= this.data.size() - 1) {

            if (this.data.size() - 1 > 2 * i && less(2 * i + 1, 2 * i)) {
                change(i, 2 * i + 1);
                increment = true;
            }
            else if (!less(2 * i, i)) {
                break;
            }
            else {
                change(i, 2 * i);
            }
            i *= 2;
            if (increment == true) {
                i++;
                increment = false;
            }
        }
    }

    @Override
    public void insert(T t) {
        this.data.add(t);
        this.check(data.size() - 1);

    }

    @Override
    public void remove() throws EmptyException {

        if (empty()) {
            throw new EmptyException();
        }

        this.data.set(1, this.data.get(this.data.size() - 1));
        this.data.remove(this.data.size() - 1);
        remakeHeap(1);

    }

    @Override
    public T top() throws EmptyException {
        if (empty()) {
            throw new EmptyException();
        }
        return this.data.get(1);
    }

    @Override
    public boolean empty() {
        return this.data.size() == 1;
    }


}
