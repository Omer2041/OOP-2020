package ex1.src;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WGraph_Algo implements weighted_graph_algorithms {
    private weighted_graph wgr;

    @Override
    public void init(weighted_graph g) {
        this.wgr = g;
    }

    @Override
    public weighted_graph getGraph() {
        return this.wgr;
    }

    @Override
    public weighted_graph copy() {
        weighted_graph g1 = new WGraph_DS();
        for (node_info node : this.wgr.getV()) { // make new nodes in memory and add them to the graph by their keys
            g1.addNode(node.getKey());
        }
        for (node_info node : this.wgr.getV()) { // connect the edges between the nodes
            for (node_info ni : this.wgr.getV(node.getKey())) {
                g1.connect(node.getKey(), ni.getKey(), this.wgr.getEdge(node.getKey(), ni.getKey()));
            }
        }
        return g1;
    }

    @Override
    public boolean isConnected() {
        if (wgr.nodeSize() == 0 || wgr.nodeSize() == 1) return true; // if the graph is empty or has one node
        for (node_info gnode : this.wgr.getV()) { //initiate the tags of the graph node
            gnode.setTag(0);
        }
        Queue<node_info> q = new LinkedList<node_info>();
        int con = 0; // count the connected nodes by bfs
        node_info rnd = null;
        for (node_info node : wgr.getV()) { // get a random node from the graph
            rnd = node;
            break;
        }
        if (rnd != null) {
            q.add(rnd);
            rnd.setTag(1);
            while (!q.isEmpty()) { // make bfs and count the connected nodes
                rnd = q.remove(); // dequeue the last node
                con++;
                for (node_info node : this.wgr.getV(rnd.getKey())) {
                    if (node.getTag() == 0) {
                        q.add(node);
                        node.setTag(1); // mark node as visited
                    }
                }
            }
        }
        return (con == this.wgr.nodeSize()); //if the bfs counter and the nodes graph equals => graph is connected
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        for (node_info gnode : this.wgr.getV()) { //initiate the tags of the graph node
            gnode.setTag(Integer.MAX_VALUE);
        }

        if (src == dest) return 0; // if src and dest are the same node
        Queue<node_info> q = new LinkedList<node_info>();
        node_info s = this.wgr.getNode(src);
        if (s == null) return -1; // if src not in the graph
        s.setTag(0); //set him as src
        node_info d = this.wgr.getNode(dest);
        node_info p;
        q.add(s);
        double ans = 0;
        while (!q.isEmpty()) { // make bfs
            p = q.remove();
            for (node_info ni : this.wgr.getV(p.getKey())) {
                if (ni.getTag() > p.getTag() + wgr.getEdge(p.getKey(), ni.getKey())) {
                    ans = p.getTag() + wgr.getEdge(p.getKey(), ni.getKey()); // the distance weight of this ni from src
                    ni.setTag(ans); // set the tag to be the weight dist from src
                    q.add(ni); // add to queue
                }
            }
        }

        if (d == null || d.getTag() == Integer.MAX_VALUE) return -1; // id dest unvisited or not connected to src
        return d.getTag(); // the dist from src
    }


    @Override

    public List<node_info> shortestPath(int src, int dest) {
        for (node_info gnode : this.wgr.getV()) { //initiate the tags of the graph node
            gnode.setTag(Integer.MAX_VALUE);
        }
        LinkedList<node_info> path = new LinkedList<>();
        if (src == dest) { // if src and dest are the same node
            path.addFirst(this.wgr.getNode(src));
            return path; //return list with that node only
        }
        Queue<node_info> q = new LinkedList<node_info>();
        node_info s = this.wgr.getNode(src);
        if (s == null) return null; //if src is null
        s.setTag(0);
        node_info d = this.wgr.getNode(dest);
        node_info p;
        q.add(s);
        double ans = 0;
        while (!q.isEmpty()) { // make bfs
            p = q.remove();
            for (node_info ni : this.wgr.getV(p.getKey())) {
                // set the tag of each connected node to src to be the distance weight from src
                if (ni.getTag() > p.getTag() + this.wgr.getEdge(p.getKey(), ni.getKey())) {
                    ans = p.getTag() + this.wgr.getEdge(p.getKey(), ni.getKey());
                    ni.setTag(ans);
                    q.add(ni); // add to queue
                }
            }
        }
        if ( d == null || d.getTag() == Integer.MAX_VALUE) return null; // if dest not connected to src or null
        node_info tmp = this.wgr.getNode(dest);
        path.add(tmp); // add dest to the the list
        while (tmp != this.wgr.getNode(src)) { //start from dest to src and find the shortest way by weight
            for (node_info node : this.wgr.getV(tmp.getKey())) {
                if (node.getTag() + wgr.getEdge(node.getKey(), tmp.getKey()) == tmp.getTag()) {
                    //if this is the neighbor with the min weight (by his tag)
                    path.addFirst(node); //add to the path list
                    tmp = node; // move to the next level neighbor

                }
            }
        }


        return path; //return the path list
    }

    @Override
    public boolean save(String file) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.wgr);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean load(String file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.wgr = (weighted_graph) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return false;
        }
        return true;
    }


}