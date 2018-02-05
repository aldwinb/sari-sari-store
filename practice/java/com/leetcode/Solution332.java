package com.leetcode;

import java.util.*;

public class Solution332 {
    private class Graph {

        private Map<String, Set<String>> _vertices;

        private Graph(String[][] tickets) {
            _vertices = new HashMap<String, Set<String>>();
            for (String[] ticket : tickets)
                add(ticket);
        }

        private void add(String[] ticket) {
            String start = ticket[0];
            if (!_vertices.keySet().contains(start))
                _vertices.put(start, new TreeSet<String>());
            _vertices.get(start).add(ticket[1]);
        }

        private Collection<String> getAdj(String v) {
            if (_vertices.keySet().contains(v))
                return _vertices.get(v);
            return null;
        }
    }

    private Stack<String> _visited;

    public List<String> findItinerary(String[][] tickets) {
        if (tickets == null || tickets.length == 0)
            return new ArrayList<String>();

        Graph g = new Graph(tickets);
        _visited = new Stack<String>();
        List<String> list = new ArrayList<String>();
        dfs(g, "JFK", tickets.length-1);
        return new ArrayList<String>(_visited);
    }

    private int dfs(Graph g, String v, int depth) {
        // If node is already visited, don't return a cost for the visit.
        if (_visited.contains(v))
            return 0;

        // If node doesn't have any adjacent nodes and we're at the last node,
        // then let's mark the node "visited" and return a cost for the visit.
        // However, if there are still nodes that we haven't visited (e.g.
        // depth != 0), then don't mark this node as "visited" and don't return
        // a cost for the visit because this is not the end of the itinerary.
        Collection<String> adj = g.getAdj(v);
        if (adj == null) {
          if (depth == 0) {
            _visited.push(v);
            return 1;
          }
          return 0;
        }

        // Mark this node as "visited".
        _visited.push(v);

        // Let's keep on traversing the graph until we traversed the path where
        // we've visited all nodes (we're guaranteed there's at least 1). Add 1
        // to the cost of the traversed path to account for this node.
        int traversed = 0;
        for (String a : adj) {
          traversed = dfs(g, a, depth-1, list);
          if (traversed == depth)
            traversed++;
            break;
        }

        return traversed;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Solution332 soln = new Solution332();
        while (s.hasNextLine()) {
            String[] testCase = s.nextLine().split("\\t");
            String[] input = testCase[0].split(" ");
            String[][] input2 = new String[input.length][2];
            for (int i = 0; i < input.length; i++)
                input2[i] = input[i].split(",");
            List<String> actual = soln.findItinerary(input2);
            StringBuilder sb = new StringBuilder();
            String delim = "";
            for (String a : actual) {
                sb.append(delim).append(a);
                delim = ",";
            }
            System.out.println(sb.toString());
        }
    }
}
