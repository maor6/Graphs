**Graphs**

**Graphs** is a java program that represent a directional weighted graph and supports running algorithms on graphs such as:
1. algorithm to chack if a given graph is strong connected.
2. to find the "cheapest" way between 2 nodes.
3. to find a relatively short path which visit each node in the targets List.

in addition, the program allows to show the graph in a graphical window, to save or load graphs and to run algorithms and show the results in the window to make the use in the progrem simple and easier.

The program consist of several classes and intefaces:

**The data stracture-**

DGraph class that implements the interface "graph" and create a directional weighted graph. The class has a road-system
or communication network in mind - and should support a large number of nodes
(over 100,000). The implementation based on two HashMaps to sure compact
representation with a relatively short run time. 
the class support the set of operations applicable on a graphs such as - remove node/edge , add node/edge , get the numbers of nodes/edges and more.

Node class that implements the interface "node_data" and create Node and the set of operations applicable on a 
node (vertex) in a directional weighted graph.

Edge class that implements the interface "edge_data" and create edge and the set of operations applicable on a 
directional edge(src,dest) in a directional weighted graph.

**The algorithems-**

Graph_Algo class that implements the interface "graph_algorithms" and consist of "regular" Graph Theory algorithms including:
1. save graph.
2. load graph.
3. chack if the graph is strongly connected.
4. find the shortest path between 2 nodes and the cost of it.
5. find the relatively short path which visit each node in a targets List.

**GUI-**

To show the graphs and the resault of the algorithms , we use StdDraw and the class Graph_GUI.

