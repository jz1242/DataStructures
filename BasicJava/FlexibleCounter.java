
/**counter that takes value from program and sets and increments the counter.
 * @author JZ
 *
 */
public class FlexibleCounter implements ResetableCounter {
    /**private variable to store the number.
     */
    private int num;
    /**private variable to store the increment.
     */
    private int increment;
    /**private variable to hold the reset of counter.
     */
    private int res;

    /**initializes initial values.
     * @param start integer value
     * @param inc integer value
     */
    public FlexibleCounter(int start, int inc) {
        this.num = start;
        this.increment = inc;
        this.res = start;
    }

    @Override
    public int value() {
        return this.num;
    }

    @Override
    public void up() {
        this.num += this.increment;
    }

    @Override
    public void down() {
        this.num -= this.increment;
    }

    @Override
    public void reset() {
        this.num = this.res;
    }

    /**main method runs the asserts for the counter.
     * @param args a string array
     */
    public static void main(String[] args) {
        FlexibleCounter c = new FlexibleCounter(-10, 3);
        c.up();
        assert c.value() == -7;
        c.up();
        assert c.value() == -4;
        c.down();
        assert c.value() == -7;
        c.down();
        c.down();
        c.down();
        assert c.value() == -16;
        c.up();
        c.up();
        c.up();
        c.up();
        c.up();
        c.up();
        c.up();
        assert c.value() == 5;
        c.reset();
        c.up();
        assert c.value() == -7;
    }

}
