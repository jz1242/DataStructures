
/**Counter that squares the value.
 * @author JZ
 *
 */
public class SquareCounter implements ResetableCounter {
    private int num;

    public SquareCounter() {
        this.num = 2;
    }

    @Override
    public int value() {
        return this.num;
    }

    @Override
    public void up() {
        this.num = (int) Math.pow(this.num, 2);
    }

    @Override
    public void down() {
        this.num = (int) Math.ceil(Math.sqrt(this.num));
    }

    @Override
    public void reset() {
        this.num = 2;

    }

    public static void main(String[] args) {
        SquareCounter c = new SquareCounter();
        c.up();
        c.up();
        c.up();
        assert c.value() == 256;
        c.down();
        assert c.value() == 16;
        c.reset();
        c.up();
        assert c.value() == 4;
    }

}
