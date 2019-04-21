/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commerical applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.graph;

/**
 * Two color a bipartite graph
 *
 * @param <T> Edge node type.
 * @author csong2022
 */
public class Bipartite<T extends EdgeNode<T>> {
    private Color[] color;       /* assigned color of v */
    private boolean bipartite;   /* is the graph bipartite? */

    public Bipartite(Graph<T> g) {
        this.color = new Color[g.nvertices() + 1];
        for (int i = 1; i <= g.nvertices(); i++)
            color[i] = Color.UNCOLORED;

        bipartite = true;

        BFS<T> bfs = new BFS<>(g);
        BipartiteCallback callback = new BipartiteCallback();

        for (int i = 1; i <= g.nvertices(); i++)
            if (!bfs.discovered(i)) {
                color[i] = Color.WHITE;
                bfs.search(i, callback);
            }
    }

    Color color(int v) {
        return this.color[v];
    }

    enum Color {
        UNCOLORED, /* vertex not yet colored */
        WHITE,     /* white vertex */
        BLACK;     /* black vertex */

        Color complement() {
            switch (this) {
                case WHITE:
                    return BLACK;
                case BLACK:
                    return WHITE;
                default:
                    return UNCOLORED;
            }
        }
    }

    private class BipartiteCallback implements GraphSearchCallback<T> {
        @Override
        public void processVertexEarly(int v) {
        }

        @Override
        public void processVertexLate(int v) {
        }

        @Override
        public void processEdge(int x, int y) {
            if (color[x] == color[y]) {
                bipartite = false;
                System.out.printf("Warning: graph not bipartite, due to (%d,%d)\n", x, y);
            }

            color[y] = color[x].complement();
        }
    }
}
