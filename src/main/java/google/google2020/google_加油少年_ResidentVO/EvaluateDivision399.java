package google.google2020.google_加油少年_ResidentVO;

/*
Equations are given in the format A / B = k,
where A and B are variables represented as strings,
and k is a real number (floating point number).
Given some queries, return the answers.
If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are:
a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is:
vector<pair<string, string>> equations,
vector<double>& values,
vector<pair<string, string>> queries ,
where equations.size() == values.size(),
and the values are positive.
This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].


The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */

/**
 * Binary relationship is represented as a graph usually.
 * Does the direction of an edge matters? -- Yes. Take a / b = 2 for example, it indicates a --2--> b as well as b --1/2--> a.
 * Thus, it is a directed weighted graph.
 * In this graph, how do we evaluate division?
 * Take a / b = 2, b / c = 3, a / c = ? for example,
 *
 * a --2--> b --3--> c
 * We simply find a path using DFS from node a to node c and multiply the weights of edges passed, i.e. 2 * 3 = 6.
 *
 * Please note that during DFS,
 *
 * Rejection case should be checked before accepting case.
 * Accepting case is (graph.get(u).containsKey(v))
 * rather than (u.equals(v)) for it takes O(1)
 * but (u.equals(v)) takes O(n) for n is the length of
 * the longer one between u and v.
 */

import java.util.*;

public class EvaluateDivision399 {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        double[] ret = new double[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            ret[i] = getPathWeight(queries[i][0], queries[i][1], new HashSet<>(), graph);
        }
        return ret;
    }

    private double getPathWeight(String start, String end, Set<String> visited, Map<String, Map<String, Double>> graph) {
        if (!graph.containsKey(start)) {
            return -1;
        }

        if (graph.get(start).containsKey(end)) {
            return graph.get(start).get(end);
        }

        visited.add(start);
        for (Map.Entry<String, Double> next: graph.get(start).entrySet()) {
            if (!visited.contains(next.getKey())) {
                double curWieght = getPathWeight(next.getKey(), end, visited, graph);
                if (curWieght != -1) {
                    return next.getValue() * curWieght;
                }
            }
        }
        return -1;
    }

    private Map<String, Map<String, Double>> buildGraph(String[][] equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.length; ++i) {
            String u = equations[i][0];
            String v = equations[i][1];
            double value = values[i];
            graph.putIfAbsent(u, new HashMap<>());
            graph.get(u).put(v, value);
            graph.putIfAbsent(v, new HashMap<>());
            graph.get(v).put(u, 1 / value);
        }
        return graph;
    }
}
