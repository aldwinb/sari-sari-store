import java.util.*;

class Solution {
    private class Node {
        private String id;
        private double cost;
        public Node(String id, double cost) {
            this.id = id;
            this.cost = cost;
        }
    }

    private class Graph {
        private Map<String, Queue<Node>> _graph;
        public Graph(String[][] equations, double[] values) {
            _graph = new HashMap<String, Queue<Node>>();
            for (int i = 0; i < equations.length; i++) {
                String s = equations[i][0],
                    d = equations[i][1];
                double cost = values[i];
                _graph.computeIfAbsent(s, k -> new ArrayDeque<Node>()).add(new Node(d, cost));
                _graph.computeIfAbsent(d, k -> new ArrayDeque<Node>()).add(new Node(s, 1.0 / cost));
            }
        }

        public Queue<Node> adj(String v) {
            return _graph.getOrDefault(v, new ArrayDeque<Node>());
        }

        public boolean contains(String v) {
            return _graph.containsKey(v);
        }
    }

    private Set<String> _visited;
    private Deque<Double> _costTo;

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        double[] queryResults = new double[queries.length];
        Graph g = new Graph(equations, values);
        _costTo = new ArrayDeque<Double>();
        _visited = new HashSet<String>();

        Arrays.fill(queryResults, -1.0);

        for (int i = 0; i < queries.length; i++) {
            String s = queries[i][0],
                v = queries[i][1];
            if (dfs(g, queries[i][0], queries[i][1]))
                queryResults[i] = calculateCost(_costTo);
            _costTo.clear();
            _visited.clear();
        }

        return queryResults;
    }

    private boolean dfs(Graph g, String s, String v) {
        if (_visited.contains(s))
            return false;

        if (s == v)
            return true;

        _visited.add(s);
        boolean found = false;
        Queue<Node> adj = g.adj(s);
        for (Node a : adj) {
            _costTo.push(Double.valueOf(a.cost));
            found = dfs(g, a.id, v);
            if (found)
                break;
            _costTo.pop();
        }

        return found;
    }

    private double calculateCost(Collection<Double> costs) {
        return costs.stream().reduce(1.0, (a, b) -> a*b);
    }

    public static void main(String[] args) {

    }
}

