package AlgorithmForJava;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
*场景：城市光纤网络建设
假设某城市需要在多个区域（节点）之间铺设光纤（边），要求以最低成本连接所有区域。
* 每条光纤线路的成本不同，我们需要找到 最小成本的全连通方案。

Prim算法：适合已知所有区域连接成本（稠密图）的场景。

Kruskal算法：适合光纤线路数据动态输入（稀疏图）的场景。
 */
public class PrimMST {
    static class Edge {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static List<Edge> primMST(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> minHeap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        List<Edge> mst = new ArrayList<>();

        // 从节点0开始
        minHeap.add(new Edge(-1, 0, 0));

        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            int u = edge.to;

            if (visited[u]) continue;
            visited[u] = true;

            if (edge.from != -1) { // 排除初始虚拟边
                mst.add(edge);
            }

            // 将u的邻接边加入堆
            for (int v = 0; v < n; v++) {
                if (graph[u][v] > 0 && !visited[v]) {
                    minHeap.add(new Edge(u, v, graph[u][v]));
                }
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        // 邻接矩阵表示的图（城市A、B、C、D）
        int[][] graph = {
                {0, 10, 20, 0},   // A → B(10), A → C(20)
                {10, 0, 30, 5},    // B → A(10), B → C(30), B → D(5)
                {20, 30, 0, 15},   // C → A(20), C → B(30), C → D(15)
                {0, 5, 15, 0}      // D → B(5), D → C(15)
        };

        List<Edge> mst = primMST(graph);
        System.out.println("Prim算法最小生成树：");
        for (Edge edge : mst) {
            System.out.printf("边 %c-%c 权重: %d\n",
                    (char)('A' + edge.from), (char)('A' + edge.to), edge.weight);
        }
    }}
