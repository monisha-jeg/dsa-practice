package sample_problems;

import basics.Graph;

/** Count paths from source to destination in a graph */
public class NumPathsBetweenTwoNodes {

    private static int numPaths(Graph g, int source, int dest) {
        int size = g.size();
        if (size == 0)
            return 0;

        boolean[] visited = new boolean[size];
        return countPathsRecursive(g, source, dest, visited);
    }

    private static int countPathsRecursive(Graph g, int current, int dest, boolean[] visited) {
        // Base case: we reached the destination
        if (current == dest) {
            return 1;
        }

        visited[current] = true; // Mark current node as visited for this path
        int count = 0;

        for (int neighbor : g.adjList.get(current)) {
            if (!visited[neighbor]) {
                count += countPathsRecursive(g, neighbor, dest, visited);
            }
        }

        visited[current] = false; // BACKTRACK: allow this node to be used in other paths
        return count;
    }

    public static void main(String[] args) {
        Graph g = new Graph(10, /* directed= */ false);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(2, 5);
        g.addEdge(4, 5);
        g.addEdge(3, 2);
        g.addEdge(2, 0);
        g.addEdge(3, 9);
        g.addEdge(9, 4);
        g.addEdge(8, 5);

        System.out.println(numPaths(g, 2, 4));
    }
}
