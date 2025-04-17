package AlgorithmForJava;

import java.util.*;
/*
*场景：城市最短路径导航
假设我们需要为一个城市的地铁系统开发一个导航功能，计算从某一站到其他所有站的最短时间（权重为分钟）。
 */
public class DijkstraShortestPath {
    static class Edge {
        int to;
        int weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static int[] dijkstra(List<List<Edge>> graph, int start) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minHeap.add(new int[]{start, 0});

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int u = current[0];
            int currentDist = current[1];

            if (currentDist > dist[u]) continue; // 跳过旧数据

            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int newDist = dist[u] + edge.weight;
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    minHeap.add(new int[]{v, newDist});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        // 地铁站编号：0-A, 1-B, 2-C, 3-D, 4-E
        List<List<Edge>> graph = new ArrayList<>();
        graph.add(Arrays.asList(new Edge(1, 5), new Edge(2, 2)));  // A → B(5), A → C(2)
        graph.add(Arrays.asList(new Edge(3, 3), new Edge(4, 2)));  // B → D(3), B → E(2)
        graph.add(Arrays.asList(new Edge(1, 1), new Edge(3, 6)));  // C → B(1), C → D(6)
        graph.add(Arrays.asList(new Edge(4, 1)));                  // D → E(1)
        graph.add(Collections.emptyList());                         // E无出边

        int start = 0; // 从A站出发
        int[] shortestTimes = dijkstra(graph, start);

        System.out.println("从地铁站A出发到各站的最短时间（分钟）：");
        for (int i = 0; i < shortestTimes.length; i++) {
            System.out.printf("A → %c: %d\n", (char)('A' + i), shortestTimes[i]);
        }
    }
}
