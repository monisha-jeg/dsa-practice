package basics.tests;

import basics.*;

public class GraphTest {

    public static void run() {
        Graph g = new Graph(10, /* directed= */ true);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(2, 5);
        g.addEdge(4, 5);
        g.addEdge(3, 2);
        g.addEdge(2, 0);

        g.dfsRecursive();
        g.dfsStack();
        g.bfs();

        System.out.println("\nCycle: " + g.hasCycleDirected());

        g.topoSort();
    }

    public static void main(String[] args) {
        run();
    }
}
