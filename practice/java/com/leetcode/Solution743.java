package com.leetcode;

import java.util.*;

public class Solution743 {
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
   
    // private int _visited;
    private Set<Integer> _visited; 
    private Queue<AdjacentNode> _nodes;

    private int dfs(Graph g, int v) {
        _visited.add(v);

        Queue<AdjacentNode> adj = g.getAdj(v);
        int cost = 0;
        while (adj != null && !adj.isEmpty()) {
            AdjacentNode n = adj.poll();
            if (!_visited.contains(n.node))
              cost = Math.max(dfs(g, n.node) + n.cost, cost);
        }
        //_visited++;
        return cost;
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        if (times.length == 0)
            return 0;
        
        // if (times.length == 1)
        //     return times[0][2];
        
        Graph g = new Graph(times);
        _visited = new HashSet<Integer>();

        int cost = dfs(g, K);
        
        return _visited.size() == N ? cost : -1;
    }

    public static void main(String[] args) {
      int[][] times = Arrays.stream(args[0].split("|"))
        .map(s -> Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray())
        .toArray(int[][]::new);
      int N = Integer.parseInt(args[1]),
          K = Integer.parseInt(args[2]);
      Solution743 s = new Solution743();
      System.out.println(String.format("%s = %s", args, s.networkDelayTime(times, N, K)));
    }
}


