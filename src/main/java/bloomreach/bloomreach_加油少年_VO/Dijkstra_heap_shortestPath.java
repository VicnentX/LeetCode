package bloomreach.bloomreach_加油少年_VO;

/*
Dijkstra+heap

Dijkstra(迪杰斯特拉)算法是一种经典的求单源最短路的算法，大体上就是利用已经找到的点的最短路去推其他点的最短路。

我们先将图中的点分为两部分：

S：已经找到最短路的点
T：图G - S，剩下的点
具体过程如下：

将dis数组初始化为INF，源点s，dis[s] = 0，s点加入S集，s点为当前点
重复以下步骤：
遍历当前点的子点，如果当前点的子点dis值 > dis[当前点]+其之间边的边权，将子点dis值更新为dis[当前点]+其之间边的边权
在T集中找出一个dis值最小的点移入S集，并将其作为当前点
直到T集为空时，结束
经过上述步骤之后，dis数组内的值便是每一点到源点的最短路值。


**************************************************************************

算法中每次寻找T集中dis值最小的点的复杂度为O(n)，我们可以用堆（heap）来优化这一过程，
使其复杂度降到O(logn)，这样总体复杂度降到O(nlogn)。

示例代码（kuangbin模板）：
 */

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


/**
 * 这个算法是nlogn的
 * n是V+E
 * 但是通常因为边的数量远远大于点的数量
 * 所以可以理解为n是E
 */

public class Dijkstra_heap_shortestPath {
    private static class Edge {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    private static class Graph {
        int vertices;
        List<Edge>[] nextNodeList;

        Graph(int n) {
            vertices = n;
            nextNodeList = new ArrayList[vertices];
            //initialize the nextNodeList
            for (int i = 0; i < vertices; ++i) {
                nextNodeList[i] = new ArrayList<>();
            }
        }

        private void addEdge(int start, int end, int weight) {
            Edge edge = new Edge(start, end, weight);
            nextNodeList[start].add(edge);
        }

        public void dijkstraGetMinDistance(int start) {
            boolean[] visited = new boolean[vertices];
            int[] distance = new int[vertices];
            //distance used to store the distance of i from start
            for (int i = 0; i < vertices; ++i) {
                distance[i] = Integer.MAX_VALUE;
            }
            //priorityQueue is sorted by the key of the pair
            /**
             * 另外我们要注意到Comparator这个接口尽管有很多方法，但是有@FunctionalInterface标志，说明这是一个函数接口，换言之，在Java8中，我们可以使用lambda表达式来写这个方法。
             *
             * 用匿名类方法写的Comparator:
             *
             *         PriorityQueue priorityQueue=new PriorityQueue(new Comparator<Student>() {
             *             @Override
             *             public int compare(Student student1, Student student2) {
             *                 return student1.mark.compareTo(student2.mark);
             *             }
             *         });
             * 用lambda表达式写Comparator
             *
             *         PriorityQueue priorityQueue=new PriorityQueue((Comparator<Student>)(student1, student2)->student1.mark.compareTo(student2.mark));
             * 这里需要注意的是，如果不加上类型转换，Java无法正确推断lambda表达式的类型。
             */
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue((Comparator<Pair<Integer, Integer>>) (a, b) -> a.getKey().compareTo(b.getKey()));

            distance[start] = 0;
            Pair<Integer, Integer> p0 = new Pair<>(distance[start],start);
            //add it to pq
            pq.offer(p0);

            //while priority queue is not empty
            while(!pq.isEmpty()) {
                //extract the min
                Pair<Integer, Integer> extractedPair = pq.poll();
                int extractedVertex = extractedPair.getValue();
                if (!visited[extractedVertex]) {
                    visited[extractedVertex] = true;
                    List<Edge> nextNodes = nextNodeList[extractedVertex];
                    for (int i = 0; i < nextNodes.size(); ++i) {
                        Edge edge = nextNodes.get(i);
                        int end = edge.end;
                        if (!visited[end]) {
                            int newKey = distance[extractedVertex] + edge.cost;
                            int curKey = distance[end];
                            if (newKey < curKey) {
                                Pair<Integer, Integer> pair = new Pair<>(newKey, end);
                                pq.add(pair);
                                distance[end] = newKey;
                            }
                        }
                    }
                }
            }

            printDijkstraDistance(distance, start);
        }

        private void printDijkstraDistance(int[] distance, int start) {
            System.out.println("now start to print shortest paths");
            for (int i = 0; i < vertices; ++i) {
                System.out.println("从" + start + "到" + i + "的最短距离是" + distance[i]);
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        graph.dijkstraGetMinDistance(0);
    }
}
