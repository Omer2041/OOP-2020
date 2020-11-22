package ex1.tests;

import org.junit.jupiter.api.Test;
import ex1.src.*;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSTest {

    @Test
    void getNode() {
        weighted_graph g = TestMethods.graph_creator(10,15,1);
        node_info n = g.getNode(5);
        assertNotNull(n);
        n= g.getNode(15);
        assertNull(n);
    }

    @Test
    void getEdge() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.connect(0,3,5.5);
        assertEquals(5.5,g.getEdge(0,3));

        g.connect(0,3,8);
        assertEquals(8,g.getEdge(0,3));

        g.connect(1,2,8);
        assertEquals(g.getEdge(1,2),g.getEdge(0,3));

        g.connect(0,1,-5);
        assertEquals(-1,g.getEdge(0,1));

    }

    @Test
    void getV() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        node_info n= g.getNode(1);
        Collection<node_info> V = g.getV();
        Iterator<node_info> i = V.iterator();
        assertEquals(4,V.size());
        while (i.hasNext()){
            assertNotNull(i);
            i.next();
        }
    }




}