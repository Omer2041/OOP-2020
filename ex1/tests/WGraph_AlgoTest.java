package ex1.tests;

import org.junit.jupiter.api.Test;
import ex1.src.*;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest {

    @Test
    void isConnected() {
        weighted_graph g = new WGraph_DS();
        weighted_graph_algorithms ga = new WGraph_Algo();
        ga.init(g);
        assertTrue(ga.isConnected());

        g.addNode(0);
        assertTrue(ga.isConnected());

        g.addNode(1);
        assertFalse(ga.isConnected());


        g.connect(0,1,1);
        assertTrue(ga.isConnected());

        g = TestMethods.graph_creator(100,400,1);
        ga.init(g);
        assertTrue(ga.isConnected());

        g = TestMethods.graph_creator(100,50,1);
        ga.init(g);
        assertFalse(ga.isConnected());

        g = TestMethods.graph_creator(1000000,900000,1);
        assertFalse(ga.isConnected());

    }

    @Test
    void shortestPathDist() {
        weighted_graph g = new WGraph_DS();
        weighted_graph_algorithms ga = new WGraph_Algo();
        ga.init(g);
        g.addNode(0);
        assertEquals(0,ga.shortestPathDist(0,0));

        g.addNode(1);
        g.connect(0,1,6.8);
        Collection<node_info> col = g.getV();
        assertEquals(6.8,ga.shortestPathDist(0,1));

        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(5);
        g.addNode(6);

        g.connect(0,1,2.5);

        g.connect(1, 3, 2.7);
        g.connect(3, 5, 0.8);
        g.connect(5,6,5);

        assertEquals(11,ga.shortestPathDist(0,6));

        g.connect(0,2,1);
        g.connect(2,3,3.2);

        assertEquals(10,ga.shortestPathDist(0,6));

        g.removeEdge(0,2);
        g.removeEdge(3,2);
        assertEquals(11,ga.shortestPathDist(0,6));


    }

    @Test
    void shortestPath() {
        weighted_graph g = new WGraph_DS();
        weighted_graph_algorithms ga = new WGraph_Algo();
        ga.init(g);
        g.addNode(0);
        assertEquals(null,ga.shortestPath(0,1));
        g.addNode(1);
        g.connect(0,1,Double.MIN_VALUE);
        g.removeEdge(0,1);
        assertEquals(null,ga.shortestPath(0,1));


        g = TestMethods.graph_creator(100,300,1);
        ga.init(g);
        assertNotNull(g);

        g.connect(2,4,Double.MIN_VALUE);
        LinkedList<node_info> lst = new LinkedList<>();
        lst.add(g.getNode(2));
        assertEquals(lst,ga.shortestPath(2,2));
        lst.addLast(g.getNode(4));
        assertEquals(lst,ga.shortestPath(2,4));

    }

    @Test
    void save_load() {
        weighted_graph g = TestMethods.graph_creator(30,50,1);
        weighted_graph_algorithms ga = new WGraph_Algo();
        ga.init(g);
        weighted_graph w = ga.copy();
        String f1 = "g.obj";
        String f2 = "w.obj";
        assertEquals(true, TestMethods.GraphEqualizer(ga.getGraph(),w));
        ga.save(f1);
        ga.load(f1);
        assertTrue(TestMethods.GraphEqualizer(ga.getGraph(),w));
        w.removeNode(0);
        g.removeNode(0);

        weighted_graph_algorithms wa = new WGraph_Algo();
        wa.init(w);
        ga.init(g);
        wa.save(f2);
        wa.load(f2);

        assertTrue(TestMethods.GraphEqualizer(ga.getGraph(),wa.getGraph()));
        w.removeNode(1);
        g.removeNode(2);
        assertFalse(TestMethods.GraphEqualizer(ga.getGraph(),wa.getGraph()));

    }

}