package basics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class Graph {
    public ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    public boolean directed;

    public Graph(int numVertices, boolean directed) {
        for (int i = 0; i < numVertices; i++)
            adjList.add(new ArrayList<>());
        this.directed = directed;
    }

    public void addEdge(int from, int to) {
        adjList.get(from).add(to);
        if (!directed) {
            adjList.get(to).add(from);
        }
    }

    public int size() {
        return adjList.size();
    }

    public ArrayList<Integer> dfsRecursive() {
        int size = size();
        ArrayList<Integer> result = new ArrayList<>();
        if (size == 0)
            return result;

        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < size; i++)
            visited.add(false);
        for (int i = 0; i < size; i++) {
            if (!visited.get(i))
                dfsRecursive(i, visited, result);
        }
        return result;
    }

    private void dfsRecursive(int vertex, ArrayList<Boolean> visited, ArrayList<Integer> result) {
        visited.set(vertex, true);
        result.add(vertex);
        for (int i = 0; i < adjList.get(vertex).size(); i++) {
            int to = adjList.get(vertex).get(i);
            if (!visited.get(to))
                dfsRecursive(to, visited, result);
        }
    }

    public ArrayList<Integer> dfsStack() {
        int size = size();
        ArrayList<Integer> result = new ArrayList<>();
        if (size == 0)
            return result;

        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < size; i++)
            visited.add(false);

        for (int i = 0; i < size; i++) {
            if (!visited.get(i)) {
                Stack<Integer> stack = new Stack<>();
                stack.push(i);
                while (!stack.empty()) {
                    int v = stack.pop();
                    if (visited.get(v)) {
                        continue;
                    }
                    result.add(v);
                    visited.set(v, true);
                    for (int j = 0; j < adjList.get(v).size(); j++) {
                        int to = adjList.get(v).get(j);
                        if (!visited.get(to)) {
                            stack.push(to);
                        }
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<Integer> bfs() {
        int size = size();
        ArrayList<Integer> result = new ArrayList<>();
        if (size == 0)
            return result;

        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < size; i++)
            visited.add(false);
        for (int i = 0; i < size; i++) {
            if (!visited.get(i))
                bfs(i, visited, result);
        }
        return result;
    }

    private void bfs(int vertex, ArrayList<Boolean> visited, ArrayList<Integer> result) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        while (queue.size() > 0) {
            int v = queue.poll();
            if (visited.get(v)) {
                continue;
            }
            result.add(v);
            visited.set(v, true);
            for (int i = 0; i < adjList.get(v).size(); i++) {
                int to = adjList.get(v).get(i);
                if (!visited.get(to)) {
                    queue.offer(to);
                }
            }
        }
    }

    public ArrayList<Integer> topoSort() {
        if (!directed)
            throw new IllegalStateException();
        int size = size();
        ArrayList<Integer> result = new ArrayList<>();
        if (size == 0)
            return result;

        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < size; i++)
            visited.add(false);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            if (!visited.get(i))
                topoSort(i, stack, visited);
        }
        while (!stack.empty()) {
            result.add(stack.pop());
        }
        return result;
    }

    public boolean hasCycle_Undirected(int vertex, ArrayList<Boolean> visited, int parent) {
        visited.set(vertex, true);
        for (int i = 0; i < adjList.get(vertex).size(); i++) {
            int to = adjList.get(vertex).get(i);
            if (!visited.get(to)) {
                if (hasCycle_Undirected(to, visited, vertex))
                    return true;
            } else if (to != parent)
                return true;
        }
        return false;
    }

    public boolean hasCycle_Directed() {
        if (!directed)
            throw new IllegalStateException();
        int size = size();
        if (size == 0)
            return false;

        ArrayList<Boolean> visited = new ArrayList<>();
        ArrayList<Boolean> recStack = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            visited.add(false);
            recStack.add(false);
        }
        for (int i = 0; i < adjList.size(); i++) {
            if (!visited.get(i))
                if (hasCycle_Directed(i, visited, recStack))
                    return true;
        }
        return false;
    }

    public boolean hasCycle_Directed(int vertex, ArrayList<Boolean> visited, ArrayList<Boolean> recStack) {
        visited.set(vertex, true);
        recStack.set(vertex, true);
        for (int i = 0; i < adjList.get(vertex).size(); i++) {
            int to = adjList.get(vertex).get(i);
            if (!visited.get(to)) {
                if (hasCycle_Directed(to, visited, recStack))
                    return true;
            } else if (recStack.get(to))
                return true;
        }
        recStack.set(vertex, false);
        return false;
    }

    public void topoSort(int vertex, Stack<Integer> stack, ArrayList<Boolean> visited) {
        visited.set(vertex, true);
        for (int i = 0; i < adjList.get(vertex).size(); i++) {
            int to = adjList.get(vertex).get(i);
            if (!visited.get(to))
                topoSort(to, stack, visited);
        }
        stack.push(vertex);
    }

    public ArrayList<Integer> topoSort_KahnsAlgorithm() {
        int n = adjList.size();
        int[] indegree = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();

        // Compute indegrees
        for (int i = 0; i < n; i++) {
            for (int next : adjList.get(i)) {
                indegree[next]++;
            }
        }

        // Add all nodes with indegree 0 into the queue
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // Kahn’s Algorithm (BFS)
        while (!queue.isEmpty()) {
            int top = queue.poll();
            result.add(top);
            for (int next : adjList.get(top)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        return result;
    }
}