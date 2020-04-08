package GraphSearch;

/**
 * 题目：261. Graph Valid Tree
 *
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges
 * (each edge is a pair of nodes), write a function to check whether
 * these edges make up a valid tree.
 *
 * Example 1:
 *
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 * Example 2:
 *
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 * Note: you can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0,1] is the same as [1,0]
 * and thus will not appear together in edges.
 */

/**
 * 笔记：
 * undirected graph is tree or not
 * 1. connected: connected component is only one, which means they all connected together
 *    图是连贯的，因为vertex的index是distinct的所以就看最后存储的visited Set的长度是不是n
 * 2. no cycle: For every visited vertex ‘v’, if there is an adjacent ‘u’
 *    such that u is already visited and u is not parent of v, then there is a cycle in graph.
 *    有没有环，写个判断有环的function,这样一旦出现环我们就直接break掉，节约时间。
 *
 * 有两个方法：
 * 方法1： helper dfs 判断是否connected，从0开始看是否走遍
 * 如果一个graph没有环，那么在有n个node的情况下，一定有n-1条边
 * 所以一开始判断是否存在环：if(edgeLength > n - 1) return false; //there exists cycle
 * (注意：At the very beginning, if the graph is not connected,
 * like there are 2 parts, one part has 3 nodes, 2 edges; the other has 2 nodes, 1 edge,
 * there is no cycle but number of nodes is 2 more than number of edges.
 * So just make step by step and your edges number is only for judgement of cycle then only
 * when edges > n - 1, there will definitely be a cycle.
 * 然后写个helper function去judge是否 fully connected
 * 判断方法就是从0开始dfs，然后看看能不能把这个graph走遍，就是看visited set的size是否就是n
 *
 * 方法2： helper hasCycle，判断看是否有环，有环的情况直接break
 * 这种方法需要避免死循环，
 */
public class GraphValidTree {
  public boolean validTree(int n, int[][] edges) {
    return true;
  }
}
