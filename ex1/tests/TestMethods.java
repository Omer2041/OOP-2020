package ex1.tests;

import ex1.src.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class TestMethods {
    private static Random _rnd = null;


    public static void main(String[] args) {

    }

    public static weighted_graph graph_creator(int v_size, int e_size, int seed) {
        weighted_graph g = new WGraph_DS();
        _rnd = new Random(seed);
        int key = 0;
        for(int i=0;i<v_size;i++) {
            g.addNode(key++);
        }
        int [] nodes = nodes(g);
        while(g.edgeSize() < e_size) {
            int a = nextRnd(0,v_size);
            int b = nextRnd(0,v_size);
            int i = nodes[a];
            int j = nodes[b];
            g.connect(i,j, 3.0);
        }
        return g;
    }


    private static int [] nodes(weighted_graph g) {
        int size = g.nodeSize();
        Collection<node_info> V = g.getV();
        node_info [] nodes = new node_info[size];
        V.toArray(nodes); // O(n) operation
        int[] ans = new int[size];
        for(int i=0;i<size;i++) {ans[i] = nodes[i].getKey();}
        Arrays.sort(ans);
        return ans;
    }
    private static int nextRnd(int min, int max) {
        double v = nextRnd(0.0+min, (double)max);
        int ans = (int)v;
        return ans;
    }
    private static double nextRnd(double min, double max) {
        double d = _rnd.nextDouble();
        double dx = max-min;
        double ans = d*dx+min;
        return ans;
    }
    public static void PrintGraph(weighted_graph g) {
        for (node_info node: g.getV()){
            System.out.println("node"+node.getKey()+" -> ");
            System.out.println("[");
            for (node_info ni:g.getV(node.getKey())){
                System.out.println("{"+ni.getKey()+","+g.getEdge(ni.getKey(),node.getKey())+"},");
            }
            System.out.println("]");
        }
    }

    public static boolean GraphEqualizer(weighted_graph g1, weighted_graph g2) {
        if (g1.edgeSize() != g2.edgeSize() || g1.nodeSize() != g2.nodeSize()) return false;
        boolean v = false;
        boolean e = false;
        for (node_info nodes1 : g1.getV()) {
            for (node_info nodes2 : g2.getV()) {
                if (nodes1.getKey() == nodes2.getKey()) v = true;
            }
            if (!v) return false;
            for (node_info edge1 : g1.getV(nodes1.getKey())) {
                for (node_info edge2 : g2.getV()) {
                    if (edge1.getKey() == edge2.getKey()) e = true;

                }
                if (!e) return false;

            }
        }
        return true;

    }
}
