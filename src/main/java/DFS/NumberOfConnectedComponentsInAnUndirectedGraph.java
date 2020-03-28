package DFS;

/**
 * 题目：323. Number of Connected Components in an Undirected Graph
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges
 * (each edge is a pair of nodes), write a function to find the number of
 * connected components in an undirected graph.
 *
 * Example 1:
 *
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 *
 *      0          3
 *      |          |
 *      1 --- 2    4
 *
 * Output: 2
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 笔记：
 * 这种题有两个解法，一种是connected components, 一种是union find
 * 非unionfind的解法就是for loop一遍所有元素的时候每一个开dfs
 * dfs每个元素的neighbors
 *
 * union find 就很直接了，注意面试的时候不要说 easy, straighforward这些词汇
 * 就是把 int[] edge : edges里面的元素两两union,然后for loop一遍看有几个parents.
 * 我们做了根据rank union，所以可以用set放一遍，返回set.size()
 * 也可以看parents[i] == i的有几个
 * 十分简单，就不写了，union find有很多复杂的题，看union find的文件夹
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {
  public int countComponents(int n, int[][] edges) {
    Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
    for(int i = 0; i < n; i++) {
      graph.put(i, new ArrayList<>());
    }
    for(int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }

    int count = 0;
    Set<Integer> visited = new HashSet<>();
    for(int i = 0; i < n; i++) {
      if(!visited.contains(i)) {
        visited.add(i);
        dfs(graph, visited, i);
        count++;
      }
    }
    return count;
  }

  private void dfs(Map<Integer, ArrayList<Integer>> graph, Set<Integer> visited, int start) {
    for(int neighbor : graph.get(start)) {
      if(!visited.contains(neighbor)) {
        visited.add(neighbor);
        dfs(graph, visited, neighbor);
      }
    }
  }

  //Test Case
  public static void main(String[] args) {
    NumberOfConnectedComponentsInAnUndirectedGraph test = new NumberOfConnectedComponentsInAnUndirectedGraph();
    int[][] input = {{0, 1}, {1, 2}, {3, 4}};
    int res = test.countComponents(5, input);
    System.out.println(res);
  }
}
