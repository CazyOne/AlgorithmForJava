package AlgorithmForJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KrusalMST {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static class UnionFind {
        int[] parent;
        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // 路径压缩
            }
            return parent[x];
        }

        void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

    public static List<Edge> kruskalMST(List<Edge> edges, int n) {
        Collections.sort(edges); // 按权重排序
        UnionFind uf = new UnionFind(n);
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            if (uf.find(edge.from) != uf.find(edge.to)) {
                uf.union(edge.from, edge.to);
                mst.add(edge);
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        // 边列表表示的图（城市A=0, B=1, C=2, D=3）
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10)); // A-B
        edges.add(new Edge(0, 2, 20)); // A-C
        edges.add(new Edge(1, 2, 30)); // B-C
        edges.add(new Edge(1, 3, 5));  // B-D
        edges.add(new Edge(2, 3, 15)); // C-D

        List<Edge> mst = kruskalMST(edges, 4);
        System.out.println("Kruskal算法最小生成树：");
        for (Edge edge : mst) {
            System.out.printf("边 %c-%c 权重: %d\n",
                    (char)('A' + edge.from), (char)('A' + edge.to), edge.weight);
        }
    }
}
