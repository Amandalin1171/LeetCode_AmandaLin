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

/**
 * 笔记：
 * 又是被图论知识塞得满满的一天：
 * 笔记思路来自：https://www.cnblogs.com/wkfvawl/p/9626163.html
 *
 * 欧拉回路和欧拉通路的概念和判断：
 * </1>概念：
 * 如果图G（有向图或者无向图）中所有边一次仅且一次行遍所有顶点的通路称作欧拉通路。
 * 如果图G中所有边一次仅且一次行遍所有顶点的回路称作欧拉回路。
 * 具有欧拉回路的图称为欧拉图（简称E图）。具有欧拉通路但不具有欧拉回路的图称为半欧拉图。
 *
 * </2>判断方法：
 * A.判断欧拉通路是否存在的方法
 *
 * 有向图：图连通，有一个顶点出度大入度1，有一个顶点入度大出度1，其余都是出度=入度。
 *
 * 无向图：图连通，只有两个顶点是奇数度，其余都是偶数度的。
 *
 * B.判断欧拉回路是否存在的方法
 *
 * 有向图：图连通，所有的顶点出度=入度。
 *
 * 无向图：图连通，所有顶点都是偶数度。
 *
 * 思考：可以看出来，无向图没有所谓的出入度，只有度，就是所谓的边或者neighbors
 */
public class Reconstruct_Itinerary {

}
