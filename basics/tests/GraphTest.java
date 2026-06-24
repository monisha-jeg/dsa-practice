package basics.tests;

import basics.*;
import java.util.List;
import static basics.tests.TestUtils.*;

public class GraphTest {

    public static void run() {
        Graph g = new Graph(10, /* directed= */ true);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(2, 5);
        g.addEdge(4, 5);
        g.addEdge(3, 2);
        g.addEdge(2, 0);

        assertFalse(g.hasCycle_Directed(), "Directed graph should not contain a cycle");
        assertEquals(10, g.size(), "Graph should contain 10 vertices");
        assertEquals(List.of(0, 1, 2, 5, 3, 4, 6, 7, 8, 9), g.dfsRecursive(),
                "DFS recursive should visit vertices in correct order");
        assertEquals(List.of(0, 1, 2, 5, 3, 4, 6, 7, 8, 9), g.dfsStack(),
                "DFS stack should visit vertices in correct order");
        assertEquals(List.of(0, 1, 2, 5, 3, 4, 6, 7, 8, 9), g.bfs(),
                "BFS should visit vertices in breadth-first order");

        Graph dag = new Graph(6, /* directed= */ true);
        dag.addEdge(5, 2);
        dag.addEdge(5, 0);
        dag.addEdge(4, 0);
        dag.addEdge(4, 1);
        dag.addEdge(2, 3);
        dag.addEdge(3, 1);

        assertEquals(List.of(5, 4, 2, 3, 1, 0), dag.topoSort(),
                "Topological sort should preserve dependency order");
        assertEquals(List.of(4, 5, 2, 0, 3, 1), dag.topoSort_KahnsAlgorithm(),
                "Kahn's algorithm should return a valid topological order");
    }

    public static void main(String[] args) {
        run();
    }
}
