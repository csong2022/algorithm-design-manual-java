# algorithm-design-manual-java
Java translation of Programs from The Algorithm Design Manual (2nd Edition)

Original C code imported from https://www3.cs.stonybrook.edu/~skiena/algorist/book/programs/

Features:
1. Translated original C programs in procedural language style into Object Oriented fashion.
2. Converted original test cases, included in test-script, into set of Java unit test cases.
3. Implemented generic Graph data structure to cover unweighted, weights, flow graph instances. Removed code duplication in original C implementation.

List of files:
 
Chapter 3. Data Structures
com.algorist.datastructure
- List.java --- linked implementation.
- Stack.java --- Implementation of a LIFO stack abstract data type.
- Queue.java --- implementation of a FIFO queue abstract data type
- Tree.java --- binary search tree implementation
- PriorityQueue.java -- Implementation of a heap / priority queue abstract data type.
- SetUnion.java --- union-find data structure implementation
- War.java --- simulation of the children's card game War
- ArrayUtils.java


Chapter 4. Sorting and Search
com.algorist.sort
- Sorting.java --- implementations of primary sorting algorithms
- PollyTest.java --- rank the desirability of suitors -- sorting example

Chapter 5. Graph Traversal
com.algorist.graph
- Graph.java -- graph data type
- EdgeNode.java -- abstraction of edge node.
- UnweightedEdgeNode.java --- unweighted edge node.
- GraphSearch.java -- abstraction of graph traversal.
- GraphSearchCallback.java -- graph search callback API.
- GraphReader.java -- abstraction of graph reader from input file.
- UnweightedGraphReader.java -- unweighted graph reader.
- AbstractGraphSearch.java -- common code sharing between BFS and DFS. 
- BFS.java -- a generic implementation of breath first search.
- DFS.java -- a generic implementation of depth first search.
- Connected.java --- compute connected components of a graph
- Bipartite.java --- Two color a bipartite graph
- FindCycle.java --- identify a cycle in a graph, if one exists
- Biconnected.java --- Identify articulation vertices in a graph
- TopSort1.java --- Topologically sort a directed acyclic graph by DFS numbering (DAG)
- TopSort.java --- topologically sort a directed acyclic graph
- Strong.java --- Identify strongly connected components in a graph
- GraphSearchUtils.java

Chapter 6. Weighted Graph Algorithms
com.algorist.wgraph
- WeightedEdgeNode.java --- Weighted edge node.
- WeightedGraphReader.java --- Weighted graph reader.
- Prim.java --- compute minimum spanning trees of graphs via Prim's algorithm
- Kruskal.java --- Compute minimum spanning trees of graphs via Kruskal's algorithm.
- Dijkstra.java --- compute shortest paths in weighted graphs
- Floyd.java --- compute all-pairs shortest paths in weighted graphs
- AdjacencyMatrix.java --- Adjacency matrix representation of a graph.
- FlowEdgeNode.java --- Flow edge node.
- FlowGraph.java --- Flow graph.
- FlowGraphReader.java --- Flow graph reader.
- Netflow.java --- network flow implementation -- augmenting path algorithm

Chapter 7. Combinatorial Search and Heuristic Methods
com.algorist.backtrack
- Backtrack.java --- a generic implementation of backtracking
- BacktrackCallback.java --- backtrack callback API.
- Subsets.java --- construct all subsets via backtracking
- Permutations.java --- construct all permutations via backtracking
- Paths.java --- Enumerate the paths in a graph via backtracking
- Sudoku.java --- A backtracking program to solve Seduku
- NQueens.java --- solve the eight queens problem using backtracking
- TSP.java --- Heuristics for solving TSP
- Annealing.java --- a fairly generic implementation of simulated annealing

Chapter 8. Dynamic Programming
com.algorist.dp
- Fib.java --- Compute the binomial coefficients using dynamic programming
- Binomial.java --- compute the binomial coefficients using dynamic programming
- StringEdit.java --- compute the optimal alignment matching two strings
- SubStringEdit.java --- approximately match one string as a substring of another
- LCS.java --- longest common subsequence of two strings
- EditDistance.java --- a generic implementation of string comparison via dp
- EditBrute.java --- compute string edit distance *without* dynamic programming
- Partition.java --- Optimally balance partitions using dynamic programming
- Elevator.java --- elevator stop optimization via dynamic programming

Chapter 13. Numerical Problems
com.algorist.numerical
- GCD.java --- compute the greatest common divisor of two integers
- Random.java --- compute random numbers within given ranges
- Primes.java --- compute the prime factorization of an integer
- BigNum.java --- implementation of large integer arithmetic
- Matrix.java --- Multiply two matrices.

Chapter 17. Computational Geometry
com.algorist.geometry
- Distance.java --- compute Euclidian distances
- Geometry.java --- basic geometric primitives and data types
- GeoTest.java --- driver program for geometry routines
- CgTest.java  --- driver program for computational geometry routines
- ConvexHull.java --- compute convex hulls of points in the plane
- Order.java --- demonstrate traversal orders on a grid
- SuperMan.java --- compute Superman's flight path -- geometry example
- Triangulate.java --- triangulate a polygon via ear-clipping, and compute area
- Plates.java --- compute the number of circles in two different packings


Chapter 18. Set and String Problems
com.algorist.string
- Name.java --- corporate name changing program -- string example

com.algorist.utils
- IterableUtils.java
