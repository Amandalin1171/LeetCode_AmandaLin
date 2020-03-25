package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：802. Find Eventual Safe States
 *
 * In a directed graph, we start at some node and every turn,
 * walk along a directed edge of the graph.
 * If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.
 * Now, say our starting node is eventually safe if and only if
 * we must eventually walk to a terminal node.  More specifically,
 * there exists a natural number K so that for any choice of where to walk,
 * we must have stopped at a terminal node in less than K steps.
 * Which nodes are eventually safe?  Return them as an array in sorted order.
 * The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.
 *
 * Example:
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Here is a diagram of the above graph.
 *
 * Note:
 * graph will have length at most 10000.
 * The number of edges in the graph will not exceed 32000.
 * Each graph[i] will be a sorted list of different integers,
 * chosen within the range [0, graph.length - 1].
 */

/**
 * 笔记：第一次提交超时(看方法2：第一次的TLE代码），是因为每一个点经过第二次的时候，
 * 他后面的点都会被重复经历，所以应该存储这个点是否有效
 * 因为一旦一个点是有效点，那么抵达这个点开始，从这个点往后的路径就都是有效路径
 *
 * 还有一个一开始没有想清楚的地方：
 * 就是dfs function 返回boolean的时候也是一层层向上返回的，
 * 所以要利用到这个返回的boolean值进行下一次的循环，看代码！！！
 *
 * 以及学会利用ENUM，当需要存储大于两种状态的时候，
 * 两种状态用boolean存储没毛病；
 * 三种及以上用Integer就没有ENUM好了
 * ENUM可以避免错误，就是使得输入错误的时候压根无法运行，要是Integer的话，你定义123
 * 就算输入的4，也可以跑，可以运行只是结果不对，这就很麻烦~
 */
public class FindEventualSafeStates {
  enum State{
    UNKNOWN,
    SAFE,
    UNSAFE
  }
  public List<Integer> eventualSafeNodes(int[][] graph) {
    int n = graph.length; // number of graph nodes
    State[] states = new State[n];
    Arrays.fill(states, State.UNKNOWN);
    List<Integer> res = new ArrayList<>();
    for(int i = 0; i < n; i++) {
      if(isSafe(graph, states, i)) res.add(i);
    }
    return res;
  }
  //dfs helper function
  private boolean isSafe(int[][] graph, State[] states, int index) {
    //already exists
    if(states[index] != State.UNKNOWN) return states[index] == State.SAFE;
    states[index] = State.UNSAFE;
    for(int i : graph[index]) {
      //not safe
      if(!isSafe(graph, states, i)) return false;
    }
    //safe
    states[index] = State.SAFE;
    return true;
  }


  //方法2：第一次提交的TLE方法
  //可以看出就是纯粹暴力，完全没有记录已经找到的有效点
  public List<Integer> eventualSafeNodes2(int[][] graph) {
    int n = graph.length; // number of nodes
    List<Integer> res = new ArrayList<>();
    for(int i = 0; i < n; i++) {
      if(dfs(graph, new boolean[n], i)) res.add(i);
    }
    return res;
  }

  private boolean dfs(int[][] graph, boolean[] visited, int start) {

    for(int i = 0; i < graph[start].length; i++) {
      if(visited[graph[start][i]]) return false;
      visited[graph[start][i]] = true;
      boolean curRes = dfs(graph, visited, graph[start][i]);
      if(!curRes) return false;
      visited[graph[start][i]] = false;
    }
    return true;
  }

  //Test Case
  public static void main(String[] args) {
    FindEventualSafeStates test = new FindEventualSafeStates();
    int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};
    List<Integer> res = test.eventualSafeNodes(graph);
    for(int i = 0; i < res.size(); i++) {
      System.out.println(res.get(i));
    }
  }
}
