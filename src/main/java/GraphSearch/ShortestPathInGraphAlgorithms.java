package GraphSearch;

/**
 * 对于edge上有权重的图：
 * 用于求图中从一个点遍历整个图的最短路径的算法
 * 有三大算法：
 * 这三大算法都不允许 negative cycles
 * 1. Dijkstra's Algorithms 走全图最短距离，不允许负数边
 * greedy思想
 * Shortest path from one node to all nodes.
 * 2. Bellman - Ford 走全图最短距离，允许负数边
 * Shortest path from one node to all nodes, negative edges allowed.
 * 3. Floyd - Warshall [DP] 求每两点之间的最短距离
 * Shortest path between all pairs of vertices, negative edges allowed.
 */
public class ShortestPathInGraphAlgorithms {
//去看看 743. Network Delay Time 这道题
}
