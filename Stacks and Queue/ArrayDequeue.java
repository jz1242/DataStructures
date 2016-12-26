/**Creates an ArrayDequeue.
 * @author JZ
 *
 * @param <T> takes any type
 */
public class ArrayDequeue<T> implements Dequeue<T> {
    /**Array created to hold data.
     */
    private T[] data;
    /**the index of the front.
     */
    private int front;
    /**index of the back.
     */
    private int back;
    /**counts number of used.
     */
    private int used;
    /**boolean to see if inputfront used.
     */
    private boolean useFront;
    /**boolean to see if inputBack is used.
     */
    private boolean useBack;

    /**initializes the data array in the constructor.
     */
    public ArrayDequeue(){
        this.data = (T[]) new Object[1];
    }

    @Override
    public boolean empty() {
        // TODO Auto-generated method stub
        return this.used == 0;
    }

    @Override
    public int length() {
        // TODO Auto-generated method stub
        return this.used;
    }

    @Override
    public T front() throws EmptyException {
        // TODO Auto-generated method stub
        return this.data[front];
    }

    @Override
    public T back() throws EmptyException {
        // TODO Auto-generated method stub
        return this.data[back - 1];
    }

    @Override
    public void insertFront(T t) {
        // TODO Auto-generated method stub
        if (full()) {
            grow(this.front);
        }
        this.data[(this.front - 1 + this.data.length) % this.data.length] = t;
        this.front = (this.front - 1 + this.data.length) % this.data.length;
        this.useFront = true;
        used++;

    }

    @Override
    public void insertBack(T t) {
        // TODO Auto-generated method stub
        if (full()) {
            grow(this.back);
        }
        this.data[this.back] = t;
        this.back++;
        this.useBack = true;
        used++;
    }

    @Override
    public void removeFront() throws EmptyException {
        // TODO Auto-generated method stub
        if (this.empty()) {
            throw new EmptyException();
        }
        this.front = (this.front + 1 + this.data.length) % this.data.length;
        this.used--;
    }

    @Override
    public void removeBack() throws EmptyException {
        // TODO Auto-generated method stub
        if (this.empty()) {
            throw new EmptyException();
        }
        this.back = (this.back - 1 + this.data.length) % this.data.length;
        this.used--;
    }

    /**sees if array is full.
     * @return a boolean if full
     */
    public boolean full() {
        return this.length() == this.data.length;
    }

    /**Grows the array.
     * @param a takes parameter int
     */
    private void grow(int a) {
        T[] bigger = (T[]) new Object[this.data.length * 2];
        int len = bigger.length;
        if (useFront) {
            for (int i = this.data.length - 1; i >= this.front; i--) {
                len--;
                bigger[len] = this.data[i];
            }
        }
        for (int i = 0; i < this.back; i++) {
            bigger[i] = this.data[i];
        }
        this.data = bigger;
        if (a == this.front) {
            this.front = len;
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String s = "[";
        for (int i = 0; i < used; i++) {
            s += this.data[(front + i + this.data.length)
                   % this.data.length].toString();
            if (i < used - 1) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }
}
