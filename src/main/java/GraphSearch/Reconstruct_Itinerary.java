package GraphSearch;

/**
 * 题目：
 * 332. Reconstruct Itinerary
 *
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 *
 * Note:
 *
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 *
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * Example 2:
 *
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 笔记：
 * 又是被图论知识塞得满满的一天：
 * 笔记思路来自：https://www.cnblogs.com/wkfvawl/p/9626163.html
 *
 * 欧拉回路和欧拉通路的概念和判断：
 * </1> 概念：
 * 如果图G（有向图或者无向图）中所有边一次仅且一次行遍所有顶点的通路称作欧拉通路。
 * 如果图G中所有边一次仅且一次行遍所有顶点的回路称作欧拉回路。
 * 具有欧拉回路的图称为欧拉图（简称E图）。具有欧拉通路但不具有欧拉回路的图称为半欧拉图。
 *
 * </2> 判断方法：
 * A.判断欧拉通路是否存在的方法
 *
 * 有向图：图连通，有一个顶点出度大入度1，有一个顶点入度大出度1，其余都是出度 = 入度。
 *
 * 无向图：图连通，只有两个顶点是奇数度，其余都是偶数度的。
 *
 * B.判断欧拉回路是否存在的方法
 *
 * 有向图：图连通，所有的顶点出度 = 入度。
 *
 * 无向图：图连通，所有顶点都是偶数度。
 *
 * 思考：可以看出来，无向图没有所谓的出入度，只有度，就是所谓的边或者neighbors
 *
 * </3> 求解欧拉回路：
 * </3.1> DFS搜索求解欧拉回路
 * 基本思路：利用欧拉定理判断出一个图存在欧拉回路或欧拉通路后，选择一个正确的起始顶点，
 * 用DFS算法遍历所有的边（每一条边只遍历一次），遇到走不通就回退。
 * 在搜索前进方向上将遍历过的边按顺序记录下来。这组边的排列就组成了一条欧拉通路或回路。
 * </3.2> Fleury算法是对DFS爆搜的一种改进，使用DFS漫不经心的随意走是效率不高的，
 * Fleury是一种有效的算法。
 * 这里待更新
 */

/**
 * 这题有量个tricky的地方：
 * 1. smallest lexical order
 * 构建graph map的时候value用priority queue不是单纯的list，就解决，自动根据String的字母顺序
 *
 * 2. dfs打印出来是倒序
 * 我自己的想法是先打印出来然后在O(n)倒着塞进结果中
 * 还有一种在dfs中就直接解决的是，res用linkedlist不是普通的list每次addFirst()
 */
public class Reconstruct_Itinerary {
  //欧拉通路
  public Map<String, PriorityQueue<String>> neighbors = new HashMap<>();
  public List<String> res = new ArrayList<>();
  public List<String> reverse = new ArrayList<>(); //dfs打印出来是倒着的
  public List<String> findItinerary(List<List<String>> tickets) {
    //corner case
    if(tickets == null || tickets.size() == 0) return res;

    //build graph
    for(List<String> curr : tickets) {
      neighbors.putIfAbsent(curr.get(0), new PriorityQueue<>());
      neighbors.get(curr.get(0)).offer(curr.get(1));
    }

    //res.add("JFK");
    dfs("JFK");
    //从倒序中走一遍填到正确顺序的结果中
    for(int i = reverse.size() - 1; i >= 0; i--) {
      res.add(reverse.get(i));
    }
    return res;
  }

  private void dfs(String departure) {
    PriorityQueue<String> arrivals = neighbors.get(departure);
    while(arrivals != null && !arrivals.isEmpty()) {
      dfs(arrivals.poll());
    }
    reverse.add(departure);
  }
}
