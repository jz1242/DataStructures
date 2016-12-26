import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;
/**JasonZhang jzhan127@jhu.edu
 * 
 * @author JZ
 *
 */
public class BinaryHeapPriorityQueueTest {
    private BinaryHeapPriorityQueue<String> pq;

    @Before
    public void setup(){
        pq = new BinaryHeapPriorityQueue<String>();

    }
    @Test
    public void testInsert(){
        pq.insert("1");
        pq.insert("2");
        pq.insert("3");
        
        assertEquals("1", pq.top());
        pq.remove();
        assertEquals("2", pq.top());
        pq.remove();
        assertEquals("3", pq.top());
        
    } 
    @Test
    public void testRemove(){
        pq.insert("1");
        pq.insert("2");
        pq.insert("3");
        
        assertEquals("1", pq.top());
        pq.remove();
        assertEquals("2", pq.top());
        pq.remove();
        assertEquals("3", pq.top());
        
    } 
    @Test
    public void testRemoveDuplicates(){
        pq.insert("1");
        pq.insert("1");
        pq.insert("1");
        pq.insert("2");
        pq.insert("3");

        
        assertEquals("1", pq.top());
        pq.remove();
        assertEquals("1", pq.top());
        pq.remove();
        assertEquals("1", pq.top());
        pq.remove();
        assertEquals("2", pq.top());
        
    } 
    @Test(expected = EmptyException.class)
    public void testRemoveException(){
        pq.remove();
        
    } 
    @Test
    public void testTop(){
        pq.insert("1");
        pq.insert("1");
        pq.insert("2");
        pq.insert("3");
        pq.insert("3");
        pq.insert("3");
        
        assertEquals("1", pq.top());
        pq.remove();

    } 
    @Test(expected = EmptyException.class)
    public void testTopException(){
        pq.top();

    } 
    @Test
    public void testEmpty(){
        assertEquals(true, pq.empty());

    } 
    @Test
    public void testComparator(){
        class Comparator2 implements Comparator<String>{

            @Override
            public int compare(String a, String b) {
                
                return b.compareTo(a);
            }
        
        }
        pq = new BinaryHeapPriorityQueue<String>(new Comparator2());
        pq.insert("1");
        pq.insert("3");
        pq.insert("4");
        assertEquals(pq.top(), "4");
        pq.remove();
        assertEquals(pq.top(), "3");
        pq.remove();
        assertEquals(pq.top(), "1");
        
    } 
    



}
