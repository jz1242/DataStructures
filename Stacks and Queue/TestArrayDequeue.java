import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

/**junit test for arraydequeue.
 * @author JZ
 *
 */
public class TestArrayDequeue {
    /**Array created for testing purposes.
     */
    static Dequeue<Integer> testArray;

    /**setups this before running tests.
     */
    @BeforeClass
    public static void setup() {
        testArray = new ArrayDequeue<Integer>();
    }

    /**tests the Front method.
     */
    @Test
    public void frontTest() {
        ArrayDequeue<String> testArray2 = new ArrayDequeue<String>();
        testArray2.insertFront("1");
        testArray2.insertFront("2");
        assertEquals("2", testArray2.front());
    }

    /**tests the insert method.
     */
    @Test
    public void backTest() {
        ArrayDequeue<String> testArray2 = new ArrayDequeue<String>();
        testArray2.insertBack("2");
        testArray2.insertBack("3");
        testArray2.insertBack("4");
        testArray2.insertBack("5");
        assertEquals("5", testArray2.back());
    }

    /**tests to see if the proper number of elements are accounted for.
     */
    @Test
    public void lengthTest() {
        ArrayDequeue<String> testArray2 = new ArrayDequeue<String>();
        testArray2.insertBack("2");
        testArray2.insertFront("1");
        testArray2.insertBack("3");
        testArray2.insertFront("4");
        assertEquals(4, testArray2.length());

    }

    /**Tests to see that the empty method works.
     */
    @Test
    public void emptyTest() {
        ArrayDequeue<String> testArray2 = new ArrayDequeue<String>();
        assertEquals(true, testArray2.empty());
    }

    /**tests to see if the insertFront method works.
     */
    @Test
    public void insertFrontTest() {
        ArrayDequeue<String> testArray2 = new ArrayDequeue<String>();
        testArray2.insertFront("1");
        testArray2.insertFront("2");
        testArray2.insertFront("3");
        assertEquals("3", testArray2.front());
    }

    /**tests to see if the insertBack method works.
     */
    @Test
    public void insertBackTest() {
        ArrayDequeue<String> testArray2 = new ArrayDequeue<String>();
        testArray2.insertBack("1");
        testArray2.insertBack("2");
        testArray2.insertFront("3");
        assertEquals("2", testArray2.back());

    }

    /**tests to see if removeFront method works.
     */
    @Test
    public void removeFrontTest() {
        ArrayDequeue<String> testArray2 = new ArrayDequeue<String>();
        testArray2.insertFront("1");
        testArray2.insertFront("2");
        testArray2.insertFront("3");
        testArray2.removeFront();
        assertEquals("2", testArray2.front());
    }

    /**tests to see if removeBack method works.
     */
    @Test
    public void removeBackTest() {
        ArrayDequeue<String> testArray2 = new ArrayDequeue<String>();
        testArray2.insertBack("1");
        testArray2.insertBack("2");
        testArray2.insertBack("3");
        testArray2.removeBack();
        assertEquals("2", testArray2.back());
    }

    /**tests to see if the toString works.
     */
    @Test
    public void toStringTest() {
        String test = "[1, 2, 3]";
        ArrayDequeue<String> testArray2 = new ArrayDequeue<String>();
        testArray2.insertBack("1");
        testArray2.insertBack("2");
        testArray2.insertBack("3");
        assertEquals(test, testArray2.toString());
    }

    /**tests the emptyException.
     */
    @Test(expected = EmptyException.class)
    public void testEmptyExcep() throws EmptyException {
        ArrayDequeue<String> testArray2 = new ArrayDequeue<String>();
        testArray.removeFront();
    }

}
