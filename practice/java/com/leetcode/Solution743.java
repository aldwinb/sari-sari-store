package com.leetcode;

import java.util.*;

class Solution {
    private class AdjacentNode {
        public final int node,
            cost;
        
        public AdjacentNode(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
    
    private class Graph {
        private Map<Integer, Queue<AdjacentNode>> _nodes;
        
        public Graph(int[][] nodes) {
            _nodes = new HashMap<Integer, Queue<AdjacentNode>>();
            for (int[] node : nodes) 
                _nodes.computeIfAbsent(node[0], k -> new LinkedList<AdjacentNode>()).add(new AdjacentNode(node[1], node[2]));
        }
        
        public Queue<AdjacentNode> getAdj(int v) {
            return _nodes.get(v);
        }
    }
   
    private int _visited;

    public int networkDelayTime(int[][] times, int N, int K) {
        if (times.length == 0)
            return 0;
        
        if (times.length == 1)
            return times[0][2];
        
        Graph g = new Graph(times);
        _visited = 0;
        int cost = dfs(g, K);
        
        return _visited == N-1 ? cost : -1;
    }
    
    private int dfs(Graph g, int v) {
        Queue<AdjacentNode> adj = g.getAdj(v);
        int cost = 0;
        while (!adj.isEmpty()) {
            AdjacentNode n = adj.poll();
            cost = Math.max(dfs(g, n.node) + n.cost, cost);
        }
        _visited++;
        return cost;
    }
}


