package ex1.src;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class WGraph_DS implements weighted_graph , Serializable {
    private HashMap <Integer,node_info> nodes;
    private HashMap <Integer,HashMap<Integer, Double>> ni;
    private int esize,mc;
    private static int node_id=0;


    public WGraph_DS(){
    nodes = new HashMap<Integer, node_info>();
    ni=new HashMap<Integer,HashMap<Integer, Double>> ();

    }
    @Override
    public node_info getNode(int key) {
        if (nodes.containsKey(key)) return nodes.get(key);
        return null;
    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        if (this.nodes.containsKey(node1)&&this.nodes.containsKey(node2)) {
            if (this.ni.get(node1).containsKey(node2)&&this.ni.get(node2).containsKey(node1) && node1 != node2) return true;
        }
        return false;
    }

    @Override
    public double getEdge(int node1, int node2) {
        if (hasEdge(node1,node2)) return ni.get(node1).get(node2);
        return -1;
    }

    @Override
    public void addNode(int key) {
        if (!nodes.containsKey(key)) {
            node_info newN = new NodeInfo(key);
            nodes.put(key,newN);
            HashMap newhash = new HashMap<Integer,Double>();
            ni.put(key, newhash);
            mc++;
        }


    }

    @Override
    public void connect(int node1, int node2, double w) {
        if (this.nodes.containsKey(node1)&&this.nodes.containsKey(node2) && w>=0 &&node1 != node2) {
            // if node1&node2 in graph and different and weight's value is legal
            if (!hasEdge(node1,node2)) {
                esize++;
                mc++;
            }
                this.ni.get(node1).put(node2,w); //update w in node1
                this.ni.get(node2).put(node1,w); //update w in node2
            }
        }


    @Override
    public Collection<node_info> getV() {
        return this.nodes.values();
    }

    @Override
    public Collection<node_info> getV(int node_id) {
        if(nodes.containsKey(node_id)) {
            HashMap<Integer, node_info> tmp = new HashMap<>();
            for (int node : this.ni.get(node_id).keySet()) {
                tmp.put(node, this.getNode(node));
            }
            return tmp.values();
        }
        return null;
    }

    @Override
    public node_info removeNode(int key) {
        if (this.getNode(key)!=null) {
            for (int node: this.ni.keySet()) {
//                    this.ni.get(node).remove(key);
                    removeEdge(key,node);

            }
        }
        this.ni.remove(key);
        return nodes.remove(key);
    }

    @Override
    public void removeEdge(int node1, int node2) {
        if (hasEdge(node1,node2)) {
            this.ni.get(node1).remove(node2);
            this.ni.get(node2).remove(node1);
            esize--;
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (Integer i : ni.keySet()) {
            s +=  i + "[";
            for (Integer j : ni.get(i).keySet()) {
                s += j.toString() + " (" + ni.get(i).get(i) + ") , ";
            }
            s += "] \n";
        }
        return s;
    }

    @Override
    public int nodeSize() {
        return this.nodes.size();
    }

    @Override
    public int edgeSize() {
        return this.esize;
    }

    @Override
    public int getMC() {
        return this.mc;
    }

    public class NodeInfo implements node_info, Serializable {
        private int key;
        private double tag;
        String info;

        public NodeInfo(int key){
            this.key = key;
        }

        public NodeInfo () {
            this.key=node_id++;
        }

        public NodeInfo (node_info other) {
            if (other!=null) {
                this.key = other.getKey();
                this.info = other.getInfo();
                this.tag = other.getTag();
            }
        }


        @Override
        public int getKey() {
            return this.key;
        }

        @Override
        public String getInfo() {
            return this.info;
        }

        @Override
        public void setInfo(String s) { this.info=s; }

        @Override
        public double getTag() {
            return this.tag;
        }

        @Override
        public void setTag(double t) { this.tag=t; }

    }

}
