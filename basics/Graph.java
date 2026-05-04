package basics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class Graph {
    public ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    public boolean directed;
    
    public Graph(int numVertices, boolean directed) {
        for(int i = 0; i < numVertices; i++)
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
    
    public void dfsRecursive() {
        int size = size();
        if (size == 0)
            return;
    
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < size; i++)
            visited.add(false);
        System.out.println("\nDFS Recursive:");
        for(int i = 0; i < size; i++) 
            if (!visited.get(i))
                dfsRecursive(i, visited);
    }
    
    public void dfsRecursive(int vertex, ArrayList<Boolean> visited) {
        visited.set(vertex, true);
        System.out.print(vertex + " ");
        for(int i = 0; i < adjList.get(vertex).size(); i++) {
            int to = adjList.get(vertex).get(i);
            if (!visited.get(to))
                dfsRecursive(to, visited);
        }
    }
    
    public void dfsStack() {
        int size = size();
        if(size == 0)
            return;
        
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < size; i++)
            visited.add(false);
        System.out.println("\nDFS Stack:");
        for (int i = 0; i < size; i++) {
            if (!visited.get(i))
                dfsStack(i, visited);
        }
    }
    
    public void dfsStack(int vertex, ArrayList<Boolean> visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(vertex);
        while(!stack.empty()) {
            int v = stack.pop();
            System.out.print(v + " ");
            for(int i = 0; i < adjList.get(v).size(); i++) {
                int to = adjList.get(v).get(i);
                if (!visited.get(to)) {
                    stack.push(to);
                    visited.set(to, true);
                }
            }
        }
    }
    
    public void bfs() {
        int size = size();
        if(size == 0)
            return;
        
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < size; i++)
            visited.add(false);
        System.out.println("\nBFS:");
        for(int i = 0; i < size; i++) {
            if (!visited.get(i))
                bfs(i, visited);
        }
    }

    public void bfs(int vertex, ArrayList<Boolean> visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        while(queue.size() > 0) {
            int v = queue.poll();
            System.out.print(v + " ");
            for(int i = 0; i < adjList.get(v).size(); i++) {
                int to = adjList.get(v).get(i);
                if (!visited.get(to)) {
                    queue.offer(to);
                    visited.set(to, true);
                }
            }
        }
    }
    
    public boolean hasCycleUndirected() {
        if (directed)
            throw new IllegalStateException();
        int size = size();
        if(size == 0)
            return false;
        
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < size; i++)
            visited.add(false);
        for (int i = 0; i < adjList.size(); i++) {
            if (!visited.get(i))
                if(hasCycleUndirected(i, visited, -1))
                    return true;
        }
        return false;
    }
    
    public boolean hasCycleUndirected(int vertex, ArrayList<Boolean> visited, int parent) {
        visited.set(vertex, true);
        for(int i = 0; i < adjList.get(vertex).size(); i++) {
            int to = adjList.get(vertex).get(i);
            if (!visited.get(to))
                if(hasCycleUndirected(to, visited, vertex))
                    return true;
            else if(to != parent)
                return true;
        }
        return false;
    }
    
    public boolean hasCycleDirected() {
        if (!directed)
            throw new IllegalStateException();
        int size = size();
        if(size == 0)
            return false;
        
        ArrayList<Boolean> visited = new ArrayList<>();
        ArrayList<Boolean> recStack = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            visited.add(false);
            recStack.add(false);
        }
        for (int i = 0; i < adjList.size(); i++) {
            if (!visited.get(i))
                if(hasCycleDirected(i, visited, recStack))
                    return true;
        }
        return false;
    }
   
    public boolean hasCycleDirected(int vertex, ArrayList<Boolean> visited, ArrayList<Boolean> recStack) {
        visited.set(vertex, true);
        recStack.set(vertex, true);
        for(int i = 0; i < adjList.get(vertex).size(); i++) {
            int to = adjList.get(vertex).get(i);
            if (!visited.get(to))
                if(hasCycleDirected(to, visited, recStack))
                    return true;
            else if(recStack.get(to))
                return true;
        }
        return false;
    }
    
    public void topoSort() {
        if (!directed)
            throw new IllegalStateException();
        int size = size();
        if(size == 0)
            return;
        
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < size; i++)
            visited.add(false);
        Stack<Integer> stack = new Stack<>();
        System.out.println("\nTopo Sort:");
        for (int i = 0; i < size; i++) {
            if (!visited.get(i))
                topoSort(i, stack, visited);
        }
        
        while(!stack.empty()) {
            int v = stack.pop();
            System.out.print(v + " ");
        }
    }

    public void topoSort(int vertex, Stack<Integer> stack,  ArrayList<Boolean> visited) {
        visited.set(vertex, true);
        for(int i = 0; i < adjList.get(vertex).size(); i++) {
            int to = adjList.get(vertex).get(i);
            if (!visited.get(to))
                topoSort(to, stack, visited);
        }
        stack.push(vertex);
    }
}