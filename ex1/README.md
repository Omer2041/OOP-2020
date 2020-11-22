Author: Omer Shalom. 204124945. 
Ex1:

*****************************************************************************************


My project implementing data structure of an weighted undirected graph and operates a set of algorithms on the graph.
the project is built from 3 classes implementing 3 interfaces: 
class Node info - internal class (of WGraph_DS) implements node_info interface.
class WGraph_DS implements weighted_graph interface.
class WGraph_Algo implements weighted_graph_algorithms interface.

First i implemented the node_info interface by building its constructors as internal class - NodeInfo of WGraph_DS.
every node has int key(id), double tag(used for algorithms), String info (node info).


Then i implemented weighted_graph interface by building its methods and constructors on WGraph_DS.
the graph has a static int node_id (every new node get his unique key) int esize(amount of edges in the graph), int mc(for counting changes in the graph) and a HashMap storing its nodes.
additionally i used another HashMap (by <Integer,HashMap<Integer,Double>>). 
so that the key (Integer) represents the id of the node and the value (HashMap) used for storing this node neighbors (by <Integer,Double>) - 
so that the key (Integer) represents the id of a neighbor node and the value (Double) represents the weight of the edge between the node and his neighbor.
i chose the HashMap data structure beacuse its methods complexity are very efficient (O(1) for most methods) .

Then i implemented weighted_graph_algorithms interface by building its algorithms on Graph_Algo - representing the set of algorithms operates on the graph.

For implementing the algorithms on the graph i used the bfs algorithm which visiting all the connected nodes in a graph. 


NodeInfo class methods:
1. getKey() – returns the key (id) of the node. complexity: O(1).
2. getInfo()  – returns the info of this node. complexity: O(1).
3. setInfo() – set the info of this node. complexity: O(1).
4. getTag() – returns the tag of this node. complexity: O(1).
5. setTag() - set the tag of this node. complexity: O(1).


 *****************************************************************************************


WGraph_DS class methods:
1. getNode(int key) – returns the node by his id. complexity: O(1).
2. hasEdge(int node1, int node2) – checks if has edge between the two given keys of nodes and return true/false.
3. getEdge(int node1, int node2) - returns the weight of the edge between the 2 given nodes.
4. addNode(int key) – Adds a given node to the graph by his key. complexity: O(1).
5. connect(int node1, int node2, double w) – connects two given nodes by an edge and set their weight. complexity: O(1).
6. getV() - returns a collection of all the nodes in the graph. complexity: O(1).
7. getV(node_id) - returns a collection of all the adjacent nodes with the node_id. complexity: O(k) k represent the degree of node_id.
8. removeNode(int key) -  returns and remove given node from the graph and removes all edges starts or ends at this node. complexity: O(n).
9. removeEdge(int node1, int node2) – removes the edge between 2 given nodes from the graph. complexity: O(1).
10. nodeSize() – returns the amount of nodes in the graph O(1)
11. edgeSize() – returns the amount of edges in the graph. complexity: O(1).
12. getMC() – returns the amount of changes made in the graph. complexity: O(1).


*****************************************************************************************


WGraph_Algo class methods:


1. init(weighted_graph g) - initiates the g graph on which this set of algorithms operates on. complexity: O(1).

2. copy() - perform a deep copy of the graph. complexity: O(V+E).

3. getGraph() - returns the weighted graph initiated at this class.

4. is connected() - 
return a boolean type. checking if the graph is connected.
starting by a random node (of a graph), visiting all the nodes connected to him (by bfs) - and counting them.
if the number of nodes visited by the bfs == this graph number of nodes then the graph is connected - return true.
complexity: O(V+E).

4. shortestPathDist(int src,int dest) - 
return double which is the shortest path distance (by edges weight sum) between source node (src) and destination node (dest).
assuming there is a way from src to dest - starting by src node (of this graph), visiting all the nodes connected to him (by bfs) - and set the tag of each node to be the dist from source.
the algorithm is setting the tag of each node to be the sum of the edges' weight on the way from src node to this node.
then its return the tag of dest.
if there is no way from src to dest returns -1.
complexity: O(V+E).


5. shortestPath(int src,int dest) - 
return List of node_info which is the shortest path (by edges weight sum) between source node (src) and destination node (dest).
assuming there is a way from src to dest - starting by src node (of this graph), visiting all the nodes connected to him (by bfs) - and set the tag of each node to be the dist from source.
the algorithm is setting the tag of each node to be the sum of the edges' weight on the way from src node to this node.
then the algorithm adds to a list all the nodes in the path - 
starting from the end to the start and at each iteration adding to the list the neighbor with the lowest weighted edge until the algorithm visit the src node.
then its returns the path List from src to dest.
if there is no way from src to dest returns null.
complexity: O(V+E).
