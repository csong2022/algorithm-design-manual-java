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

package com.algorist.datastructure;

/**
 * Union-find data structure implementation.
 *
 * @author csong2022
 */
public class SetUnion {
    private int[] p;        /* parent element */
    private int[] size;           /* number of elements in subtree i */
    private int n;                /* number of elements in set */

    public SetUnion(int n) {
        this.n = n;
        this.p = new int[n + 1];
        this.size = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            this.p[i] = i;
            this.size[i] = 1;
        }
    }

    private int find(int x) {
        if (p[x] == x)
            return x;
        else
            return find(p[x]);
    }

    public void unionSets(int s1, int s2) {
        int r1 = find(s1);    /* root of s1 */
        int r2 = find(s2);/* root of s2 */

        System.out.printf("s1=%d r1=%d s2=%d r2=%d\n", s1, r1, s2, r2);

        if (r1 == r2) return;        /* already in same set */

        if (size[r1] >= size[r2]) {
            size[r1] += size[r2];
            p[r2] = r1;
        } else {
            size[r2] += size[r1];
            p[r1] = r2;
        }
    }

    public boolean sameComponent(int s1, int s2) {
        return find(s1) == find(s2);
    }

    public void print() {
        for (int i = 1; i <= n; i++)
            System.out.printf("%d  set=%d size=%d \n", i, p[i], size[i]);

        System.out.printf("\n");
    }
}
