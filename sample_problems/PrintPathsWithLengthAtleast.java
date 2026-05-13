package sample_problems;

import java.util.Stack;
import basics.Graph;

/** Print paths of length >= k in a graph from given source */
public class PrintPathsWithLengthAtleast {

    private static void getPathsOfLengthKOrLonger(Graph g, int source, int k) {
        int size = g.size();
        if (size == 0)
            return;

        boolean[] visited = new boolean[size];
        Stack<Integer> path = new Stack<>();
        getPathsRecursive(g, source, k, visited, path);
    }

    private static void printPath(Stack<Integer> path) {
        for (Integer v : path)
            System.out.print(v + " ");
        System.out.println();
    }

    private static void getPathsRecursive(Graph g, int current, int k, boolean[] visited, Stack<Integer> path) {
        path.push(current);
        visited[current] = true; // Mark current node as visited for this path

        if (k <= 0) {
            printPath(path);
        }

        for (int neighbor : g.adjList.get(current)) {
            if (!visited[neighbor]) {
                getPathsRecursive(g, neighbor, k - 1, visited, path);
            }
        }

        path.pop();
        visited[current] = false; // BACKTRACK: allow this node to be used in other paths
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

        getPathsOfLengthKOrLonger(g, 2, 0);
    }
}