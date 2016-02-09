package com.leetcode;

import java.util.*;

public class Solution332 {
    private class Graph {

        private Map<String, Queue<String>> _vertices;

        private Graph(String[][] tickets) {
            _vertices = new HashMap<String, Queue<String>>();
            for (String[] ticket : tickets)
                add(ticket);
        }

        private void add(String[] ticket) {
            String start = ticket[0];
            if (!_vertices.keySet().contains(start))
                _vertices.put(start, new PriorityQueue<String>());
            _vertices.get(start).add(ticket[1]);
        }

//        private void remove(String v, String a) {
//            if (_vertices.keySet().contains(v))
//                _vertices.get(v).remove(a);
//            else
//                _vertices.remove(v);
//        }

        private String getAdj(String v) {
            if (_vertices.keySet().contains(v)) {
                Queue<String> q = _vertices.get(v);
                String w = q.remove();
                if (q.size() == 0)
                    _vertices.remove(v);
                return w;
            }
            return null;
        }

        private int count() {
            return _vertices.size();
        }
    }

    private List<String> _visited;

    public List<String> findItinerary(String[][] tickets) {
        if (tickets == null || tickets.length == 0)
            return new ArrayList<String>();

        Graph g = new Graph(tickets);
        _visited = new ArrayList<String>();
        List<String> list = new ArrayList<String>();

        String v = "JFK";
        list.add("JFK");
        while (g.count() > 0) {
            String w = g.getAdj(v);
            if (w == null)
                continue;
            list.add(w);
            v = w;
        }
        return list;
    }

    // JFK

//    private void dfs(Graph g, String v, List<String> list) {
//        list.add(v);
//        //if (_visited.contains(v))
//        //    return;
//
//        //_visited.add(v);
//        Collection<String> adj = g.getAdj(v);
//        if (adj == null)
//            return;
//
//        List<String> sorted = new ArrayList<String>(adj);
//        Collections.sort(sorted);
//        for (String a : sorted) {
//            dfs(g, a, list);
//            g.remove(v, a);
//        }
//    }

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
//            int expected = Integer.parseInt(testCase[1]),
//                    actual = soln.findItinerary(input[0], input[1]);
//            if (expected != actual)
//                System.out.format(
//                        "Test case = %s, expected = %s, actual = %s\n",
//                        testCase[0],
//                        expected,
//                        actual);
        }
    }
}
