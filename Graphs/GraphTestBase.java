import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public abstract class GraphTestBase {

    protected abstract Graph<String, String> createGraph();
    
    private Graph<String, String> test;
    
    @Before
    public void setup() {
        test = this.createGraph();
    }
    
    @Test
    public void testInsertVertex(){
       Vertex<String> a = test.insert("one");
       assertEquals("one", test.remove(a));
    }
    @Test
    public void testInsertVertexMulti(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
       assertEquals("one", test.remove(a));
       assertEquals("2", test.remove(b));
       assertEquals("3", test.remove(c));
    }
    @Test
    public void testInsertEdge(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Edge<String> d = test.insert(a, b, "4");
       assertEquals("4", test.remove(d));

    }
    @Test(expected = PositionException.class)
    public void testInsertEdgeException(){
       Vertex<String> a = test.insert("one");
       Vertex<String> c = null;
       Vertex<String> b = test.insert("2");
       Edge<String> d = test.insert(a, c, "4");
       

    }
    @Test(expected = InsertionException.class)
    public void testInsertEdgeInsertionException(){
       Vertex<String> a = test.insert("one");
       Vertex<String> c = null;
       Vertex<String> b = test.insert("2");
       Edge<String> d = test.insert(a, a, "4");
       

    }
    @Test(expected = InsertionException.class)
    public void testInsertEdgeInsertionExceptionDuplicate(){
       Vertex<String> a = test.insert("one");
       Vertex<String> c = null;
       Vertex<String> b = test.insert("2");
       Edge<String> d = test.insert(a, b, "4");
       Edge<String> q = test.insert(a, b, "4");
       

    }
    @Test
    public void testRemoveVertex(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       assertEquals("one" , test.remove(a));
       
    }
    @Test
    public void testRemoveVertexMultiple(){
       Vertex<String> a = test.insert("one");
       Vertex<String> c = null;
       Vertex<String> b = test.insert("2");
       assertEquals("one" , test.remove(a));
       assertEquals("2" , test.remove(b));
    }
    @Test(expected = PositionException.class)
    public void testRemoveVertexPositionException(){
       Vertex<String> a = test.insert("one");
       Vertex<String> c = null;
       Vertex<String> b = test.insert("2");
       assertEquals("one" , test.remove(c));
       assertEquals("2" , test.remove(b));
    }
    @Test(expected = RemovalException.class)
    public void testRemoveVertexRemovalException(){
       Vertex<String> a = test.insert("one");
       Vertex<String> c = null;
       Vertex<String> b = test.insert("2");
       Edge<String> d = test.insert(a, b, "4");
       test.remove(b);
    }
    @Test
    public void testRemoveEdge(){
       Vertex<String> a = test.insert("one");
       Vertex<String> c = null;
       Vertex<String> b = test.insert("2");
       Edge<String> d = test.insert(a, b, "4");
       assertEquals("4", test.remove(d));
    }
    @Test
    public void testRemoveEdges(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Edge<String> d = test.insert(a, b, "4");
       Edge<String> q = test.insert(b, a, "5");
       assertEquals("4", test.remove(d));
       assertEquals("5", test.remove(q));
    }
    @Test(expected = PositionException.class)
    public void testRemoveEdgeException(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = null;
       Edge<String> d = test.insert(a, c, "4");

    }
    @Test
    public void testIterateVertices(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
       int count = 0;
       for(Vertex<String> s: test.vertices()){
           count++;
       }
       assertEquals(3, count);
    }
    @Test
    public void testIterateEdges(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
       Edge<String> d = test.insert(a, c, "4");
       Edge<String> e = test.insert(b, a, "4");
       Edge<String> f = test.insert(c, b, "4");
       
       int count = 0;
       for(Edge<String> s: test.edges()){
           count++;
       }
       assertEquals(3, count);
    }
    @Test
    public void testIterateOutgoing(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
       Edge<String> d = test.insert(a, b, "4");
       Edge<String> e = test.insert(a, c, "4");
       
       int count = 0;
       for(Edge<String> s: test.outgoing(a)){
           count++;
       }
       assertEquals(2, count);
    }
    @Test(expected = PositionException.class)
    public void testIterateOutgoingException(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
       Vertex<String> f = null;
       Edge<String> d = test.insert(a, b, "4");
       Edge<String> e = test.insert(a, c, "4");
       
       int count = 0;
       for(Edge<String> s: test.outgoing(f)){
           count++;
       }
       assertEquals(2, count);
    }
    @Test
    public void testIterateIncoming(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
       Edge<String> d = test.insert(b, a, "4");
       Edge<String> e = test.insert(c, a, "4");
       
       int count = 0;
       for(Edge<String> s: test.incoming(a)){
           count++;
       }
       assertEquals(2, count);
    }
    @Test(expected = PositionException.class)
    public void testIterateIncomingException(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
       Vertex<String> f = null;
       Edge<String> d = test.insert(b, a, "4");
       Edge<String> e = test.insert(c, a, "4");
       
       int count = 0;
       for(Edge<String> s: test.incoming(f)){
           count++;
       }
       assertEquals(2, count);
    }
    @Test
    public void testTo(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
       Edge<String> d = test.insert(b, a, "4");
       Edge<String> e = test.insert(c, a, "4");
       
       assertEquals(test.to(d), a);
    }
    @Test(expected = PositionException.class)
    public void testToException(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
       Edge<String> f = null;
       Edge<String> d = test.insert(b, a, "4");
       Edge<String> e = test.insert(c, a, "4");
       
       test.to(f);
    }
    @Test
    public void testFrom(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
      
       Edge<String> d = test.insert(b, a, "4");
       Edge<String> e = test.insert(c, a, "4");
       
       assertEquals(test.from(d), b);
    }
    @Test(expected = PositionException.class)
    public void testFromException(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
       Edge<String> f = null;
       Edge<String> d = test.insert(b, a, "4");
       Edge<String> e = test.insert(c, a, "4");
       
       test.from(f);
    }
    @Test
    public void testLabelVertex(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
       test.label(a, "labeled");
       assertEquals(test.label(a), "labeled");
    }
    @Test(expected = PositionException.class)
    public void testLabelExceptionVertex(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = null;
       test.label(c, "one");
    }
    @Test
    public void testLabelEdge(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
       Edge<String> d = test.insert(b, a, "4");
       Edge<String> e = test.insert(c, a, "4");
       test.label(d, "labeled");
       assertEquals(test.label(d), "labeled");
    }
    @Test(expected = PositionException.class)
    public void testLabelExceptionEdge(){
       Vertex<String> a = test.insert("one");
       Vertex<String> b = test.insert("2");
       Vertex<String> c = test.insert("3");
       Edge<String> f = null;
       test.label(f, "one");
    }
    @Test
    public void testGetLabelVertex(){
        Vertex<String> a = test.insert("one");
        Vertex<String> b = test.insert("2");
        Vertex<String> c = test.insert("3");
        test.label(a, "labeled");
        assertEquals(test.label(a), "labeled");
    }
    @Test(expected = PositionException.class)
    public void testGetLabelVertexException(){
        Vertex<String> a = test.insert("one");
        Vertex<String> b = test.insert("2");
        Vertex<String> c = null;
        test.label(c);
    }
    @Test
    public void testGetLabelEdge(){
        Vertex<String> a = test.insert("one");
        Vertex<String> b = test.insert("2");
        Vertex<String> c = test.insert("3");
        Edge<String> d = test.insert(b, a, "4");
        test.label(d, "labeled");
        assertEquals(test.label(d), "labeled");
    }
    @Test(expected = PositionException.class)
    public void testGetLabelEdgeException(){
        Vertex<String> a = test.insert("one");
        Vertex<String> b = test.insert("2");
        Vertex<String> c = test.insert("3");
        Edge<String> d = null;
        test.label(d, "labeled");
        
    }
    @Test
    public void testClearLabels(){
        Vertex<String> a = test.insert("one");
        Vertex<String> b = test.insert("2");
        Vertex<String> c = test.insert("3");
        Edge<String> d = test.insert(b, a, "4");
        Edge<String> e = test.insert(b, c, "e");
        test.label(a, "labelA");
        test.label(b, "labelB");
        test.label(d, "labeled");
        test.clearLabels();
        assertEquals(null, test.label(a));
        assertEquals(null, test.label(b));
        assertEquals(null, test.label(d));
        
    }
    @Test
    public void testtoString() {
        Vertex<String> a = test.insert("A");
        Vertex<String> b = test.insert("B");
        Edge<String> d = test.insert(a, b, "7");
        String total = "digraph {\n"
                        + "  \"A\";\n"
                        + "  \"B\";\n"
                        + "  \"A\" -> \"B\" [label=\"7\"];\n"
                        +"}";
        assertEquals(total, test.toString());
    }
    
}