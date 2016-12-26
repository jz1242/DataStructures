
/** BasicCounter that increments and decrements.
 * @author JZ
 *
 */
public class BasicCounter implements ResetableCounter {
    /**Private Variable for the counter to increment or decrement.
     */
    private int num;

    /**Constructor that sets the value of counter.
     */
    public BasicCounter() {
        this.num = 0;
    }

    @Override
    public int value() {
        return this.num;
    }

    /* (non-Javadoc)
     * @see Counter#up()
     */
    @Override
    public void up() {
        this.num += 1;
    }

    @Override
    public void down() {
        this.num -= 1;
    }

    @Override
    public void reset() {
        this.num = 0;
    }

    /**main method that runs the asserts.
     * @param args (string array)
     */
    public static void main(String[] args) {
        BasicCounter one = new BasicCounter();
        assert one.value() == 0;
        one.up();
        assert one.value() == 1;
        one.down();
        assert one.value() == 0;
        one.down();
        one.up();
        one.up();
        one.up();
        assert one.value() == 2;
        one.reset();
        one.up();
        assert one.value() == 1;

    }

}
