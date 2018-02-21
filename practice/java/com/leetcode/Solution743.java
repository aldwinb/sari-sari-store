package com.leetcode;

import java.util.*;

public class Solution743 {
    private class Node {
        public final int id,
            cost;

        public Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }

    private class Graph {
        private Map<Integer, Queue<Node>> _nodes;

        public Graph(int[][] nodes) {
            _nodes = new HashMap<Integer, Queue<Node>>();
            for (int[] node : nodes)
                _nodes.computeIfAbsent(node[0], k -> new ArrayDeque<Node>()).add(new Node(node[1], node[2]));
        }

        public Queue<Node> getAdj(int v) {
            return _nodes.getOrDefault(v, new ArrayDeque<Node>());
        }
    }

    private Queue<Integer> _nodes;
    private Set<Integer> _visited;
    private int[] _costs;

    private int bfs(Graph g, int s) {
        int maxCost = 0;
        _nodes.add(s);
        _visited.add(s);
        _costs[s] = 0;
        while (!_nodes.isEmpty()) {
            int nodeId = _nodes.poll();
            for (Node n : g.getAdj(nodeId)) {
                if (n.id != s) {
                    if (_costs[n.id] > _costs[nodeId]+n.cost) {
                        _costs[n.id] = _costs[nodeId]+n.cost;
                        maxCost = Math.max(_costs[n.id], maxCost);
                        System.out.println(String.format("node = %s, cost = %s, maxCost = %s", n.id, _costs[n.id], maxCost));
                    }
                    if (!_nodes.contains(n.id))
                        _nodes.add(n.id);
                }
            }
        }
        return maxCost;
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        if (times.length == 0)
            return 0;

        Graph g = new Graph(times);
        _nodes = new ArrayDeque<Integer>();
        _visited = new HashSet<Integer>();
        _costs = new int[N+1];
        Arrays.fill(_costs, 101);

        int cost = bfs(g, K);

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


