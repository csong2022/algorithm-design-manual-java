The Algorithm Design Manual (2nd Edition) by Steven Skiena, Springer-Verlag, New York 2008. See my website http://www.algorist.com for additional information. This book can be ordered from Amazon.com.

The programs are made available under the following copyright notice: 
Copyright 2003, 2008 by Steven S. Skiena; all rights reserved. Permission is granted for use in non-commerical applications provided this copyright notice remains intact and unchanged.

What follows are a list of all the files in this directory with a brief description of what they are. A single tar file with all the programs is also available.

- Makefile --- instructions on how to compile all of our programs
- README --- this file; a description of all programs in distribution
- test-script* --- run tests on each of the programs created by Makefile
- datafiles/ --- a directory with test files for all the programs, see test-script
- component-graphs/ --- a directory with test files for connected components
- original/ --- a directory with the original versions from Programming Challenges

Chapter 2. Algorithm Analysis
matrix.c        /* Multiply two matrices. */

Chapter 3. Data Structures
- item.h --- Header file for linked implementation
- list.h --- Header file for linked implementation
- list-demo.c --- Driver for a linked list-based container implementation.
- stack.h --- Header file for queue implementation
- stack.c --- Implementation of a LIFO stack abstract data type.
- queue.h --- header file for queue implementation
- queue.c --- implementation of a FIFO queue abstract data type
- tree.h --- Header file for binary search tree implementation
- tree-demo.c --- Driver for a binary search tree container implementation.
- priority_queue.h --- Header file for queue implementation
- priority_queue.c --- Implementation of a heap / priority queue abstract data type.
- set_union.h --- Header file for union-find data structure implementation
- set_union.c --- Implementation of a heap / priority queue abstract data type.

Chapter 4. Sorting and Search
- sorting.c --- implementations of primary sorting algorithms
- polly.c --- rank the desirability of suitors -- sorting example

Chapter 5. Graph Traversal
- graph.h --- header file for graph data type
- graph.c --- a generic adjacency list-in-array graph data type
- bfs-dfs.c --- a generic implementation of graph traversal
- bfs-demo.c --- driver program demonstrating breadth-first search
- dfs-demo.c --- driver program demonstrating depth-first search
- connected.c --- compute connected components of a graph
- bipartite.c --- Two color a bipartite graph
- findcycle.c --- identify a cycle in a graph, if one exists
- biconnected.c --- Identify articulation vertices in a graph
- topsort1.c --- Topologically sort a directed acyclic graph by DFS numbering (DAG)
- topsort.c --- topologically sort a directed acyclic graph
- strong.c --- Identify strongly connected components in a graph

Chapter 6. Weighted Graph Algorithms
- wgraph.h --- header file for weighted graph type
- wgraph.c --- a generic weighted graph data type
- prim.c --- compute minimum spanning trees of graphs via Prim's algorithm
- kruskal.c --- Compute minimum spanning trees of graphs via Kruskal's algorithm.
- dijkstra.c --- compute shortest paths in weighted graphs
- floyd.c --- compute all-pairs shortest paths in weighted graphs
- netflow.c --- network flow implementation -- augmenting path algorithm

Chapter 7. Combinatorial Search and Heuristic Methods
- backtrack.h --- header file for generic backtracking
- backtrack.c --- a generic implementation of backtracking
- subsets.c --- construct all subsets via backtracking
- permutations.c --- construct all permutations via backtracking
- paths.c --- Enumerate the paths in a graph via backtracking
- sudoku.c --- A backtracking program to solve Seduku
- sudoku-examples/ --- a directory with test files for sudoku
- 8-queens.c --- solve the eight queens problem using backtracking
- annealing.h --- header file for simulated annealing
- annealing.c --- a fairly generic implementation of simulated annealing
- tsp.h --- Header file for tsp
- tsp.c --- Heuristics for solving TSP
- tsp-examples/ --- a directory with test files for the traveling salesmen

Chapter 8. Dynamic Programming
- fib.c --- Compute the binomial coefficients using dynamic programming
- binomial.c --- compute the binomial coefficients using dynamic programming
- stringedit.c --- compute the optimal alignment matching two strings
- substringedit.c --- approximately match one string as a substring of another
- editdistance.h --- header file for string comparison
- editdistance.c --- a generic implementation of string comparison via dp
- editbrute.c --- compute string edit distance *without* dynamic programming
- partition.c --- Optimally balance partitions using dynamic programming
- elevator.c --- elevator stop optimization via dynamic programming

Chapter 9. Intractable Problems and Approximation Algorithms
- gcd.c --- compute the greatest common divisor of two integers

Chapter 13. Numerical Problems
- random.c --- compute random numbers within given ranges
- primes.c --- compute the prime factorization of an integer
- bignum.c --- implementation of large integer arithmetic

Chapter 17. Computational Geometry
- distance.c --- compute Euclidian distances
- geometry.h --- header file for geometric data types
- geometry.c --- basic geometric primitives and data types
- geotest.c --- driver program for geometry routines
- cgtest.c --- driver program for computational geometry routines
- convex-hull.c --- compute convex hulls of points in the plane
- mwt.c --- Compute the minimum weight triangulation of convex polygons
- order.c --- demonstrate traversal orders on a grid
- superman.c --- compute Superman's flight path -- geometry example
- triangulate.c --- triangulate a polygon via ear-clipping, and compute area
- plates.c --- compute the number of circles in two different packings

Chapter 18. Set and String Problems
- name.c --- corporate name changing program -- string example
- lcs.c --- longest common subsequence of two strings

Other
- bool.h --- header file for boolean datatype
- 10055.c --- program demonstrating standard IO in C
- 10055.cc --- program demonstrating standard IO in C++
- 10055.java --- program demonstrating standard IO in Java
- 10055.pascal --- program demonstrating standard IO in Pascal
- sentinel.c --- example search program using sentinels
- war.c --- simulation of the children's card game War