import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**JasonZhang jzhan127@jhu.edu
 * 
 * @author JZ
 *
 */
public class MoveToFrontListSetTest {

    private MoveToFrontListSet<String> list;
    @Before
    public void setup() {
        list = new MoveToFrontListSet<String>();
        
    }
    @Test
    public void testInsert(){
        list.insert("1");
        list.insert("2");
        list.insert("3");
        int count = 0;
        for(String a : list){
            count++;
        }
        assertEquals(count, 3);
    }
    @Test
    public void testEmptyBeginning(){
        int count = 0;
        for(String a : list){
            count++;
        }
        assertEquals(count, 0);
    }
    @Test
    public void testRemove(){
        list.insert("1");
        list.insert("2");
        list.insert("3");
        list.remove("1");
        int count = 0;
        for(String a : list){
            count++;
        }
        assertEquals(count, 2);
    }
    @Test
    public void testRemoveTooMany(){
        list.insert("1");
        list.remove("1");
        list.remove("1");
        int count = 0;
        for(String a : list){
            count++;
        }
        assertEquals(count, 0);
    }
    @Test
    public void testDuplicates(){
        list.insert("1");
        list.insert("2");
        list.insert("3");
        list.insert("4");
        list.insert("4");
        list.insert("4");
        list.insert("4");
        int count = 0;
        for(String a : list){
            count++;
        }
        assertEquals(count, 4);
    }
    @Test
    public void testHas(){
        list.insert("1");
        list.insert("2");
        list.insert("3");
        
        assertEquals(list.has("1"), true);
    }
    @Test
    public void testNotHave(){
        list.insert("1");
        list.insert("2");
        list.insert("3");
        
        assertEquals(list.has("4"), false);
    }
    @Test
    public void testInsertFrontAlreadyFront(){
        list.insert("1");
        list.insert("2");
        list.insert("3");
        list.insert("4");
        list.insert("4");
        list.insert("4");
        String total = "";
        for(String a : list){
            total += a;
        }
        
        assertEquals(total, "4321");
    }
    @Test
    public void testInsertFront(){
        list.insert("1");
        list.insert("2");
        list.insert("3");
        list.insert("4");
        list.insert("2");
        String total = "";
        for(String a : list){
            total += a;
        }
        
        assertEquals(total, "2431");
    }

}



