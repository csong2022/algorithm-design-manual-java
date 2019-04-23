/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commercial applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.wgraph;

/**
 * Compute all-pairs shortest paths in weighted graphs.
 *
 * @author csong2022
 */
public class Floyd {
    public Floyd(AdjacencyMatrix g) {
        int through_k;            /* distance through vertex k */
        for (int k = 1; k <= g.nvertices(); k++)
            for (int i = 1; i <= g.nvertices(); i++)
                for (int j = 1; j <= g.nvertices(); j++) {
                    through_k = g.weight(i, k) + g.weight(k, j);
                    if (through_k < g.weight(i, j))
                        g.setWeight(i, j, through_k);
                }
    }
}
