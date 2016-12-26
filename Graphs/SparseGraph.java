import java.util.ArrayList;
/**SparseGraph implementation.
 * Jason Zhang jzhan127 Assignment 6
 *
 * @param <V> vertices generic
 * @param <E> edge generic
 */

public class SparseGraph<V, E> implements Graph<V, E> {

    /**creates vertex.
     * @param <T> takes a generic type T.
     */
    private final class GraphVertex<T> implements Vertex<T> {
        /**Arraylist of all the edges related to the vertex.
         */
        ArrayList<GraphEdge<E>> allEdges = new ArrayList<GraphEdge<E>>();
        /**Data Value stored in vertex.
         */
        T data;
        /**gives the owner of the graph.
         */
        Graph<V, E> owner;
        /**creates a label for the graph.
         */
        Object label;

        @Override
        public T get() {
            return this.data;
        }

        @Override
        public void put(T t) {
            this.data = t;
        }
    }

    /**creates an edge for the graph.
     * @param <T> takes in generic type t
     */
    private final class GraphEdge<T> implements Edge<T> {
        /**gives vertex of to.
         */
        GraphVertex<V> to;
        /**stores vertex of from.
         */
        GraphVertex<V> from;
        /**stores the data.
         */
        T data;
        /**stores the label.
         */
        Object label;
        /**Stores the owner.
         */
        Graph<V, E> owner;

        @Override
        public T get() {
            return this.data;
        }

        @Override
        public void put(T t) {
            this.data = t;
        }
    }

    /**holds list of all the vertices.
     */
    private ArrayList<GraphVertex<V>> vertexList =
            new ArrayList<GraphVertex<V>>();
    /**holds list of all the edges.
     */
    private ArrayList<GraphEdge<E>> edgeList = new ArrayList<GraphEdge<E>>();

    /**convert edge to graphedge.
     * @param p takes in edge
     * @return a new graphedge
     * @throws PositionException if edge is not possible
     */
    private GraphEdge<E> convertEdg(Edge<E> p) throws PositionException {
        try {
            GraphEdge<E> n = (GraphEdge<E>) p;
            if (n.owner != this) {
                throw new PositionException();
            }
            return n;
        } catch (NullPointerException | ClassCastException e) {
            throw new PositionException();
        }
    }

    /**convert a vertex.
     * @param p takes in a vertex
     * @return gives a graphvertex
     * @throws PositionException if vertex is invalid
     */
    private GraphVertex<V> convertVer(Vertex<V> p) throws PositionException {
        try {
            GraphVertex<V> n = (GraphVertex<V>) p;
            if (n.owner != this) {
                throw new PositionException();
            }
            return n;
        } catch (NullPointerException | ClassCastException e) {
            throw new PositionException();
        }
    }

    @Override
    public Vertex<V> insert(V v) {
   
        GraphVertex<V> n = new GraphVertex<V>();
        n.data = v;
        n.owner = this;
        vertexList.add(n);
        return n;
    }

    @Override
    public Edge<E> insert(Vertex<V> from, Vertex<V> to, E e) throws
        PositionException, InsertionException {
        if (from.equals(to)) {
            throw new InsertionException();
        }

        GraphVertex<V> fromTest = this.convertVer(from);
        GraphVertex<V> toTest = this.convertVer(to);
        for (int i = 0; i < fromTest.allEdges.size(); i++) {
            if (fromTest.allEdges.get(i).from.equals(fromTest)
                && fromTest.allEdges.get(i).to.equals(toTest)) {
                throw new InsertionException();
            }
        }
        GraphEdge<E> n = new GraphEdge<E>();
        n.data = e;
        n.to = toTest;
        n.from = fromTest;
        n.owner = this;
        fromTest.allEdges.add(n);
        toTest.allEdges.add(n);
        edgeList.add(n);
        return n;
    }

    @Override
    public V remove(Vertex<V> v) throws PositionException, RemovalException {
        GraphVertex<V> n = this.convertVer(v);
        if (n.allEdges.size() > 0) {
            throw new RemovalException();
        }

        n.owner = null;
        vertexList.remove(n);
        return n.data;
    }

    @Override
    public E remove(Edge<E> e) throws PositionException {
        GraphEdge<E> n = this.convertEdg(e);
        GraphVertex<V> s = n.from;
        GraphVertex<V> t = n.to;
        s.allEdges.remove(n);
        t.allEdges.remove(n);
        edgeList.remove(n);
        n.owner = null;
        return n.data;
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        ArrayList<Vertex<V>> n = new ArrayList<Vertex<V>>();
        for (GraphVertex<V> a : vertexList) {
            n.add((Vertex<V>) a);
        }
        return n;
    }

    @Override
    public Iterable<Edge<E>> edges() {
        ArrayList<Edge<E>> n = new ArrayList<Edge<E>>();
        for (GraphEdge<E> a : edgeList) {
            n.add((Edge<E>) a);
        }
        return n;
    }

    @Override
    public Iterable<Edge<E>> outgoing(Vertex<V> v) throws PositionException {
        GraphVertex<V> n = convertVer(v);
        ArrayList<Edge<E>> s = new ArrayList<Edge<E>>();
        for (GraphEdge<E> a : n.allEdges) {
            if (a.from.equals(n)) {
                s.add((Edge<E>) a);
            }
        }
        return s;
    }

    @Override
    public Iterable<Edge<E>> incoming(Vertex<V> v) throws PositionException {
        GraphVertex<V> n = convertVer(v);
        ArrayList<Edge<E>> s = new ArrayList<Edge<E>>();
        for (GraphEdge<E> a : n.allEdges) {
            if (a.to.equals(n)) {
                s.add((Edge<E>) a);
            }
        }
        return s;
    }

    @Override
    public Vertex<V> from(Edge<E> e) throws PositionException {
        GraphEdge<E> n = this.convertEdg(e);
        return n.from;
    }

    @Override
    public Vertex<V> to(Edge<E> e) throws PositionException {
        GraphEdge<E> n = this.convertEdg(e);
        return n.to;
    }

    @Override
    public void label(Vertex<V> v, Object l) throws PositionException {
        GraphVertex<V> n = this.convertVer(v);
        n.label = l;


    }

    @Override
    public void label(Edge<E> e, Object l) throws PositionException {
        GraphEdge<E> n = this.convertEdg(e);
        n.label = l;
    }

    @Override
    public Object label(Vertex<V> v) throws PositionException {
        GraphVertex<V> n = this.convertVer(v);
        return n.label;
    }

    @Override
    public Object label(Edge<E> e) throws PositionException {
        GraphEdge<E> n = this.convertEdg(e);
        return n.label;
    }

    @Override
    public void clearLabels() {
        for (int i = 0; i < vertexList.size(); i++) {
            GraphVertex<V> n = vertexList.get(i);
            n.label = null;
            for (int j = 0; j < n.allEdges.size(); j++) {
                GraphEdge<E> s = n.allEdges.get(j);
                s.label = null;
            }

        }

    }

    /**gives the tostring of the graph.
     * @return String to print
     */
    public String toString() {
        String total = "digraph {\n";
        for (int i = 0; i < vertexList.size(); i++) {
            total += "  \"" + vertexList.get(i).data + "\";\n";
        }
        for (int j = 0; j < edgeList.size(); j++) {
            total += "  \"" + edgeList.get(j).from.data + "\"" + " -> " + "\""
                    + edgeList.get(j).to.data + "\""
                    + " [label=\"" + edgeList.get(j).data + "\"];\n";
        }
        total += "}";
        return total;

    }


}
