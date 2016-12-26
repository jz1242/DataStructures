import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;


/**
 * @author Jason Zhang jzhan127
 * Testing implementations of the List interface.
 *
 * The tests defined here apply to all implementations of the List
 * interface. However, they cannot be run directly as we don't know
 * which implementation to test or how to create an instance of it.
 *
 * The solution is to define a "template method" called createList()
 * that subclasses of this test override. The NodeListTest.java class,
 * for example, creates a suitable NodeList instance to be tested.
 *
 * (We could use a JUnit feature called "parameterized tests" to do
 * the same thing, however that feature is a bit more complex to use
 * than we would like.)
 *
 * Note that we (somewhat arbitrarily) choose to test lists of strings.
 * We could have gone for lists of integers or lists of whatever, but
 * strings seem convenient in any case: You can pick strings in such a
 * way as to make your test cases more readable.
 */
public abstract class ListTestBase {
    private List<String> list;

    protected abstract List<String> createList();

    @Before
    public void setupListTests() {
        list = this.createList();
    }

    @Test
    public void newListEmpty() {
        assertTrue(list.empty());
        assertEquals(0, list.length());
        assertEquals("[]", list.toString());

        int c = 0;

        for (String s: list) {
            c++;
        }

        assertEquals(0, c);

    }

    @Test(expected=EmptyException.class)
    public void newListNoFront() {
        Position<String> p = list.front();
    }

    @Test(expected=EmptyException.class)
    public void newListNoBack() {
        Position<String> p = list.back();
    }

    /**test1.
     */
    @Test
    public void insertFrontWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        assertFalse(list.empty());
        assertEquals(3, list.length());
        assertEquals("[Three, Two, One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }

    @Test
    public void insertBackWorks() {
        list.insertBack("One");
        list.insertBack("Two");
        list.insertBack("Three");

        assertFalse(list.empty());
        assertEquals(3, list.length());
        assertEquals("[One, Two, Three]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }

    @Test
    public void insertFrontBackConsistent() {
        Position<String> f = list.insertFront("Front");
        assertEquals("Front", f.get());
        Position<String> b = list.insertBack("Back");
        assertEquals("Back", b.get());

        assertNotEquals(f, b);
        assertTrue(list.first(f));
        assertTrue(list.last(b));

        Position<String> x;

        x = list.front();
        assertEquals(f, x);

        x = list.back();
        assertEquals(b, x);
    }

    @Test
    public void removeFrontWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");
        list.removeFront();
        list.removeFront();

        assertFalse(list.empty());
        assertEquals(1, list.length());
        assertEquals("[One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(1, c);
    }

    @Test
    public void removeBackWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");
        list.removeBack();
        list.removeBack();

        assertFalse(list.empty());
        assertEquals(1, list.length());
        assertEquals("[Three]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(1, c);
    }

    @Test(expected = EmptyException.class)
    public void removeFrontException() {
        list.removeFront();
    }

    @Test(expected = EmptyException.class)
    public void removeBackException() {
        list.removeBack();
    }

    @Test(expected = PositionException.class)
    public void testNextException() {
        Position<String> p = list.insertFront("One");
        Position<String> s = list.next(p);
        list.next(s);

    }

    @Test(expected = PositionException.class)
    public void testPrevException() {
    	Position<String> p= list.insertFront("One");
    	Position<String> s = list.previous(p);
    	list.previous(s);

    }

    @Test
    public void testNext() {
    	Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("Two");
    	assertEquals(list.next(s).get(), "One");
    }

    @Test
    public void testPrev() {
    	Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("Two");
    	assertEquals(list.previous(p).get(), "Two");
    }

    @Test
    public void testLength() {
        assertEquals(0, list.length());
        Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("Two");
    	list.removeBack();
    	assertEquals(1, list.length());
    }

    @Test
    public void testInsertAfter() {
    	Position<String> p = list.insertFront("One");
    	list.insertAfter(p, "Two");

    	assertEquals(list.next(p).get(), "Two");
    }

    @Test
    public void testInsertBefore() {
    	Position<String> p = list.insertBack("One");
    	list.insertBefore(p, "Two");
    	assertEquals(list.previous(p).get(), "Two");
    }
    @Test
    public void testInserts() {
    	Position<String> p = list.insertFront("1");
    	Position<String> s = list.insertFront("2");
    	Position<String> q = list.insertAfter(s, "3");
    	Position<String> r = list.insertBefore(q, "4");
    	Position<String> t = list.insertBack("5");
    	Position<String> a = list.insertBefore(t,"6");
    	assertEquals("[2, 4, 3, 1, 6, 5]", list.toString());
    	
    
    }

    @Test(expected = PositionException.class)
    public void testInsertAfterException() {
    	Position<String> p = list.insertFront("One");
    	Position<String> s = list.next(list.next(p));
    	list.insertAfter(s, "Two");

    }

    @Test(expected = PositionException.class)
    public void testInsertBeforeException(){
    	Position<String> p = list.insertBack("One");
    	Position<String> s = list.previous(list.next(p));
    	list.insertBefore(s, "Two");
    }

    @Test(expected = PositionException.class)
    public void testInsertBeforeHead(){
    	Position<String> p = list.insertBack("One");
    	Position<String> s = list.previous(p);
    	list.insertBefore(s, "Two");
    }
    
    @Test(expected = PositionException.class)
    public void testInsertBeforeTail(){
    	Position<String> p = list.insertBack("One");
    	Position<String> s = list.next(p);
    	list.insertBefore(s, "Two");
    	assertEquals(list.back().get(), "Two");
    }
    @Test(expected = PositionException.class)
    public void testInsertAfterHead(){
    	Position<String> p = list.insertBack("One");
    	Position<String> s = list.previous(p);
    	list.insertAfter(s, "Two");
    	assertEquals(list.front().get(), "Two");
    }
    @Test(expected = PositionException.class)
    public void testInsertAfterTail(){
    	Position<String> p = list.insertBack("One");
    	Position<String> s = list.next(p);
    	list.insertAfter(s, "Two");
    }

    @Test
    public void testRemove() {
    	Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertBack("Two");
    	Position<String> q = list.insertBack("Three");
    	list.remove(s);
        assertEquals(list.next(p), q);
    }
    @Test(expected = PositionException.class)
    public void testRemoveInvalid() {
    	Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertBack("Two");
    	Position<String> q = list.insertBack("Three");
    	list.remove(p);
    	list.insertBefore(list.previous(s), "Two");
    }


    @Test(expected = PositionException.class)
    public void testRemoveException() {
    	Position<String> p = list.insertFront("One");
    	Position<String> s = list.previous(p);
    	list.remove(s);

    }

    @Test
    public void testFront() {
    	Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("t");
    	Position<String> q = list.insertFront("3");
    	assertEquals(list.front().get(), "3");
    }

    @Test
    public void testBack() {
    	Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("t");
    	Position<String> q = list.insertFront("3");

    	assertEquals(list.back().get(), "One");
    }

    @Test
    public void testFirst() {
        Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("t");
    	Position<String> q = list.insertFront("3");

    	assertEquals(list.first(q), true);
    	assertEquals(list.first(p), false);
    }
    @Test
    public void testFirstFalse() {
        Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("t");
    	Position<String> q = list.insertFront("3");

    	assertEquals(list.first(p), false);
    }

    @Test
    public void testLast() {
        Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("t");
    	Position<String> q = list.insertFront("3");
    	assertEquals(list.last(p), true);
    }
    @Test
    public void testLastFalse() {
        Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("t");
    	Position<String> q = list.insertFront("3");
    	assertEquals(list.last(s), false);
    }

    @Test(expected = PositionException.class)
    public void testFirstException() {
        Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("t");
    	Position<String> q = list.insertFront("3");
        list.first(list.previous(list.previous(q)));
    }

    @Test(expected = PositionException.class)
    public void testLastException() {
        Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("t");
    	Position<String> q = list.insertFront("3");
    	list.first(list.next(list.next(p)));

    }

    @Test
    public void testIteratorForward(){
        Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("t");
    	Position<String> q = list.insertFront("3");
        String tot = "";
        Iterator<String> a = list.forward();
        while (a.hasNext()) {
            tot += a.next();
        }
        assertEquals("3tOne", tot);
    }

    @Test
    public void testIteratorBackwards() {
        Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("t");
    	Position<String> q = list.insertFront("3");
        String tot = "";
        Iterator<String> a = list.backward();
        while (a.hasNext()) {
            tot += a.next();
        }
        assertEquals("Onet3", tot);
    }

    @Test
    public void testToString() {
        Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("t");
    	Position<String> q = list.insertFront("3");
    	Position<String> n = list.insertFront("4");
        String tot = list.toString();
        assertEquals("[4, 3, t, One]", tot);
    }
    @Test
    public void testToStringEmpty() {
        String tot = list.toString();
        assertEquals("[]", tot);
    }
    @Test
    public void testToStringUsingRemove() {
        Position<String> p = list.insertFront("One");
    	Position<String> s = list.insertFront("t");
    	Position<String> q = list.insertFront("3");
    	Position<String> n = list.insertFront("4");
    	list.remove(s);
    	list.remove(n);
        String tot = list.toString();
        assertEquals("[3, One]", tot);
    }
    @Test
    public void testGet() {
        Position<String> p = list.insertFront("1");
        assertEquals(p.get(), "1");
    }
    @Test
    public void testPut() {
        Position<String> p = list.insertFront("1");
        p.put("3");
        assertEquals(p.get(), "3");
    }


    // TODO You need to add *many* more test cases here, ideally before you
    // even start working on SentinelList!
}
