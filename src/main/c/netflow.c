/*	netflow.c

	Network flow implementation -- Ford-Fulkerson augmenting path algorithm.

	by: Steven Skiena
	begun: September 19, 2002

	This algorithm requires its own special graph type, to allow for
	flows and residual flow fields.
*/

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


#include "bool.h"
#include "queue.h"
#include <stdio.h>
#include "geometry.h"


#define	MAXV		100		/* maximum number of vertices */
#define MAXDEGREE	50		/* maximum outdegree of a vertex */


typedef struct {
	int v;				/* neighboring vertex */
	int capacity;			/* capacity of edge */
	int flow;			/* flow through edge */
	int residual;			/* residual capacity of edge */
	struct edgenode *next;          /* next edge in list */
} edgenode;

typedef struct {
	edgenode *edges[MAXV+1];		/* adjacency info */
	int degree[MAXV+1];		/* outdegree of each vertex */
	int nvertices;			/* number of vertices in the graph */
	int nedges;			/* number of edges in the graph */
} flow_graph;



main()
{
	flow_graph g;			/* graph to analyze */
	int source, sink;		/* source and sink vertices */
	int flow;			/* total flow */
	edgenode *p;			/* temporary pointer */

	scanf("%d %d",&source,&sink);
	read_flow_graph(&g,TRUE);

	netflow(&g,source,sink);

	print_flow_graph(&g);

	flow = 0;
	p = g.edges[source];
	while (p != NULL) {
		flow += p->flow;
		p = p->next;
	}

	printf("total flow = %d\n",flow);
}



initialize_graph(g)
flow_graph *g;				/* graph to initialize */
{
	int i;				/* counter */

	g -> nvertices = 0;
	g -> nedges = 0;

	for (i=0; i<MAXV; i++) g->degree[i] = 0;
	for (i=0; i<MAXV; i++) g->edges[i] = NULL;

}

read_flow_graph(g,directed)
flow_graph *g;				/* graph to initialize */
bool directed;				/* is this graph directed? */
{
	int i;				/* counter */
	int m;				/* number of edges */
	int x,y,w;			/* placeholder for edge and weight */

	initialize_graph(g);

	scanf("%d %d\n",&(g->nvertices),&m);

	for (i=1; i<=m; i++) {
		scanf("%d %d %d\n",&x,&y,&w);
		insert_flow_edge(g,x,y,directed,w);
	}
}

insert_flow_edge(flow_graph *g, int x, int y, bool directed, int w)
{
        edgenode *p;                    /* temporary pointer */

        p = malloc(sizeof(edgenode));   /* allocate storage for edgenode */

	p->v = y;
	p->capacity = w;
	p->flow = 0;
	p->residual = w;
	p->next = g->edges[x];

	g->edges[x] = p;                /* insert at head of list */

	g->degree[x] ++;

	if (directed == FALSE)
		insert_flow_edge(g,y,x,TRUE,w);
	else
		g->nedges ++;
}


edgenode *find_edge(flow_graph *g, int x, int y)
{
	edgenode *p;                    /* temporary pointer */

	p = g->edges[x];

	while (p != NULL) {
		if (p->v == y) return(p);
		p = p->next;
	}

	return(NULL);
}


add_residual_edges(flow_graph *g)
{
	int i,j;			/* counters */
	edgenode *p;                    /* temporary pointer */
	edgenode *find_edge();

	for (i=1; i<=g->nvertices; i++) {
		p = g->edges[i];
		while (p != NULL) {
			if (find_edge(g,p->v,i) == NULL)
				insert_flow_edge(g,p->v,i,TRUE,0);
			p = p->next;
		}
	}
}


print_flow_graph(flow_graph *g)
{
        int i;                          /* counter */
        edgenode *p;                    /* temporary pointer */

        for (i=1; i<=g->nvertices; i++) {
                printf("%d: ",i);
                p = g->edges[i];
                while (p != NULL) {
			printf(" %d(%d,%d,%d)",p->v, p->capacity,
				p->flow, p->residual);
			p = p->next;
		}
		printf("\n");
	}
}

bool processed[MAXV+1];		/* which vertices have been processed */
bool discovered[MAXV+1];        /* which vertices have been found */
int parent[MAXV+1];		/* discovery relation */

bool finished = FALSE;          /* if true, cut off search immediately */


initialize_search(g)
flow_graph *g;				/* graph to traverse */
{
        int i;                          /* counter */

        for (i=1; i<=g->nvertices; i++) {
                processed[i] = FALSE;
                discovered[i] = FALSE;
                parent[i] = -1;
        }
}


bfs(flow_graph *g, int start)
{
        queue q;                        /* queue of vertices to visit */
        int v;                          /* current vertex */
        int y;                          /* successor vertex */
        edgenode *p;                    /* temporary pointer */

        init_queue(&q);
        enqueue(&q,start);
        discovered[start] = TRUE;

        while (empty_queue(&q) == FALSE) {
                v = dequeue(&q);
                process_vertex_early(v);
                processed[v] = TRUE;
                p = g->edges[v];
                while (p != NULL) {
                    y = p->v;
		    if (valid_edge(p) == TRUE) {
                        if ((processed[y] == FALSE) /*|| g->directed*/)
                            process_edge(v,y);
                        if (discovered[y] == FALSE) {
                            enqueue(&q,y);
                            discovered[y] = TRUE;
                            parent[y] = v;
                        }
		    }
                    p = p->next;
                }
                process_vertex_late(v);
        }
}



bool valid_edge(edgenode *e)
{
	if (e->residual > 0) return (TRUE);
	else return(FALSE);
}

process_vertex_early(v)
int v;					/* vertex to process */
{
}

process_vertex_late(v)
int v;                                  /* vertex to process */
{
}

process_edge(x,y)
int x,y;                                /* edge to process */
{
}




find_path(start,end,parents)
int start;				/* first vertex on path */
int end;				/* last vertex on path */
int parents[];				/* array of parent pointers */
{
	if ((start == end) || (end == -1))
		printf("\n%d",start);
	else {
		find_path(start,parents[end],parents);
		printf(" %d",end);
	}
}

int path_volume(flow_graph *g, int start, int end, int parents[])
{
	edgenode *e;			/* edge in question */
	edgenode *find_edge();

	if (parents[end] == -1) return(0);

	e = find_edge(g,parents[end],end);

	if (start == parents[end])
		return(e->residual);
	else
		return( min(path_volume(g,start,parents[end],parents), e->residual) );
}


augment_path(flow_graph *g, int start, int end, int parents[], int volume)
{
	edgenode *e;			/* edge in question */
	edgenode *find_edge();

	if (start == end) return;

	e = find_edge(g,parents[end],end);
	e->flow += volume;
	e->residual -= volume;
	
	e = find_edge(g,end,parents[end]);
	e->residual += volume;

	augment_path(g,start,parents[end],parents,volume);
}


netflow(flow_graph *g, int source, int sink)
{
	int volume;			/* weight of the augmenting path */

	add_residual_edges(g);

	initialize_search(g);
	bfs(g,source);

	volume = path_volume(g, source, sink, parent);

	while (volume > 0) {
		augment_path(g,source,sink,parent,volume);
		initialize_search(g);
		bfs(g,source);
		volume = path_volume(g, source, sink, parent);
	}
}
