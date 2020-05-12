package GraphSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 对于edge上有权重的图：
 * 用于求图中从一个点遍历整个图的最短路径的算法
 * 有三大算法：
 * 这三大算法都不允许 negative cycles
 *
 * 1. Dijkstra's Algorithms 走全图最短距离，不允许负数边
 * 实际上这是一个贪心算法：greedy思想
 * 为啥贪心呢？因为每一步我们都选择vertex with the smallest known distance from the start node
 * optimize local choice
 * Shortest path from one node to all nodes.
 *
 * 设图中总计有E条边，N个结点。
 * 时间复杂度：O(ElogN)。因为所使用的最小堆最大可达O(N)大小，同时我们最多向其中添加和取出O(E)次结点。
 * 空间复杂度：O(N+E)。其中O(N)为存储dist及最小堆所用空间。O(E)为存储图的邻接链表所用空间。
 *
 * https://www.youtube.com/watch?v=pVfj6mxhdMw
 * 这个视频很清晰
 * https://blog.csdn.net/Yaokai_AssultMaster/article/details/79878371
 * 这个帖子讲的很好
 *
 * 2. Bellman - Ford 走全图最短距离，允许负数边
 * Shortest path from one node to all nodes, negative edges allowed.
 *
 * 3. Floyd - Warshall [DP] 求每两点之间的最短距离,允许负数边
 * Shortest path between all pairs of vertices, negative edges allowed.
 * 想看看Floyd -Warshall的例题，看 399. Evaluate Division(这题可以dfs, bfs, union find, FW)
 * 最短路径，还是要解决所有pair，还是允许负数
 *
 * Pseudocode:
 *
 * let V = numbers of vertices in graph
 * let dist = V * V array of minimum distances initialized to 无穷大
 * for each vertex v: dist[v][v] = 0: 自己到自己的最短距离为0
 * for each edge(u, v): dist[u][v] = weight(u, v)
 *
 * for k from 1 to V
 *   for i from 1 to V
 *     for j from 1 to V
 *       if dist[i][j] > dist[i][k] + dist[k][j] : dist[i][j] = dist[i][k] + dist[k][j]
 *       end if
 */
public class ShortestPathInGraphAlgorithms {

  /**
   * 1. DijkstraShortestPath Given a list of (source, target, weight) edge pairs, return the
   * shortest distance from s to any other nodes in the graph.
   *
   * @param edges given graph
   * @param N     number of nodes
   * @param s     start node
   * @return Shortest path from s to other nodes in the graph
   */
  public Map<Integer, Integer> dijkstraShortestPath(int[][] edges, int N, int s) {
    //build graph
    //key: start node, value: destination node and path weight
    Map<Integer, List<int[]>> graph = new HashMap<>();
    for(int[] edge : edges) {
      graph.putIfAbsent(edge[0], new ArrayList<int[]>());
      graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
    }

    Map<Integer, Integer> distanceMap = new HashMap<>();
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((edge1, edge2) -> {
      return edge1[1] - edge2[1];
    });
    minHeap.offer(new int[] {s, 0});

    //BFS
    while(!minHeap.isEmpty()) {
      int[] curr = minHeap.poll();
      if(distanceMap.containsKey(curr[0])) continue;
      distanceMap.put(curr[0], curr[1]);
      if(graph.containsKey(curr[0])) {
        for(int[] edge : graph.get(curr[0])) {
          minHeap.offer(new int[] {edge[0], edge[1] + curr[1]});
        }
      }
    }

    for(int i = 0; i < N; i++) {
      if(!distanceMap.containsKey(i)) {
        distanceMap.put(i, Integer.MAX_VALUE);
      }
    }
    return distanceMap;
  }
}
