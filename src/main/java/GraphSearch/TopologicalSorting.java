package GraphSearch;

/**
 * 题目【注：本题摘自Lintcode】
 * 127. Topological Sorting
 *
 * Description:
 * Given an directed graph, a topological order of the graph nodes is defined as follow:
 *
 * For each directed edge A -> B in graph, A must before B in the order list.
 * The first node in the order can be any node in the graph with no nodes direct to it.
 * Find any topological order for the given graph.
 * You can assume that there is at least one topological order in the graph.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 拓扑排序的概念以及限制条件：
 * Copyright from:
 *  * 作者：Amosasas
 *  * 链接：https://www.jianshu.com/p/9da88d296b99
 *  * 来源：简书
 *
 * 在图论中，拓扑排序（Topological Sorting）是一个有向无环图（DAG, Directed Acyclic Graph）的所有顶点的线性序列。且该序列必须满足下面两个条件：
 * 1.每个顶点出现且只出现一次。
 * 2.若存在一条从顶点 A 到顶点 B 的路径，那么在序列中顶点 A 出现在顶点 B 的前面。
 * 有向无环图（DAG）才有拓扑排序，非DAG图没有拓扑排序一说。
 *
 * 它是一个 DAG 图，那么如何写出它的拓扑排序呢？这里说一种比较常用的方法：
 * 1.从 DAG 图中选择一个 没有前驱（即入度为0）的顶点并输出。
 * 2.从图中删除该顶点和所有以它为起点的有向边。
 * 3.重复 1 和 2 直到当前的 DAG 图为空或当前图中不存在无前驱的顶点为止。后一种情况说明有向图中必然存在环。
 * 可以使用在工期排序、大小关系排列、选课时的先修课（即要先修某某课才能学这一门课，先学了C语言再学数据结构这样）等情况。
 */

/**
 * 笔记：
 * 思路来源于这个视频，讲的很好：https://www.youtube.com/watch?v=x3mm5a_CwRM
 * 拓扑排序的逻辑是这样的：
 * 外层循环中：每次我都选择入度为0的点取出，并且将这个点抹掉；
 * ***上面说的抹掉***是指：将这个点被取出的这个点的邻接点OR以此点为前驱点(Precursor point)的那些个点的入度减1
 * 【思考】如果外层循环还没有结束，我们就找不到入度为0的点了，就说明图中有环，break
 * 优化解法：随时将入度变为0的顶点放到一个容器里，将以此点为前驱点的那些个点的入度减1的同时判断其入度是否变为0
 * 如果变为0则放到那个特殊的容器中，这样大大节约了查找的空间和时间【同时！！！】有环的条件变为：
 * 当我跳出这个循环，不是所有的点都被循环出来了，还有点在那个特殊容器中，就说明有环。(输出点的时候加一个int计数器用来计数即可实现)
 */
public class TopologicalSorting {
  public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
    ArrayList<DirectedGraphNode> res = new ArrayList<>();
    Map<DirectedGraphNode, Integer> map = new HashMap<>();
    //for loop所有点的neighbors点(就是所谓的边)统计入度
    //从每一个点开始找他们的neighbors,找到一次记一笔，所以每个点被找到过的次数就是指向这个点的箭头数
    //也就是这个点的入度，所以KEY: neighbor, VALUE: 计数
    for(DirectedGraphNode node : graph) {
      for(DirectedGraphNode neighbor : node.neighbors) {
        if(map.containsKey(neighbor)) {
          map.put(neighbor, map.get(neighbor) + 1);
        } else {
          map.put(neighbor, 1);
        }
      }
    }

    //先把入度为0的那些个前驱点加到结果res中和存储的容器queue中去
    Queue<DirectedGraphNode> queue = new LinkedList<>();
    for(DirectedGraphNode node : graph) {
      if(!map.containsKey(node)) {
        queue.offer(node);
        res.add(node);
      }
    }

    //每一次的动作：
    //把特殊容器(存储入度变为0的那些个点的Queue)中的点poll出来，for loop它的neighbors
    //将这些个neighbors每个点的入度减一，同时进行判断，一旦这些点的入度变为0了，加到结果res中去
    //同时加到特殊容器中去，以此循环往复，就可以一个个取出拓扑排序的点了！
    while(!queue.isEmpty()) {
      DirectedGraphNode node = queue.poll();
      for(DirectedGraphNode n : node.neighbors) {
        map.put(n, map.get(n) - 1);
        if(map.get(n) == 0) {
          res.add(n);
          queue.offer(n);
        }
      }
    }
    return res;
  }

  //Test Case
  public static void main(String[] args) {
    TopologicalSorting testClass = new TopologicalSorting();
    DirectedGraphNode a = new DirectedGraphNode(0);
    DirectedGraphNode b = new DirectedGraphNode(1);
    DirectedGraphNode c = new DirectedGraphNode(2);
    DirectedGraphNode d = new DirectedGraphNode(3);
    DirectedGraphNode e = new DirectedGraphNode(4);
    DirectedGraphNode f = new DirectedGraphNode(5);


    ArrayList<DirectedGraphNode> aa = new ArrayList<>();
    a.neighbors = aa;
    aa.add(b);
    aa.add(c);
    aa.add(d);
    ArrayList<DirectedGraphNode> bb = new ArrayList<>();
    b.neighbors = bb;
    bb.add(e);
    ArrayList<DirectedGraphNode> cc = new ArrayList<>();
    c.neighbors = cc;
    cc.add(e);
    cc.add(f);
    ArrayList<DirectedGraphNode> dd = new ArrayList<>();
    d.neighbors = dd;
    dd.add(e);
    dd.add(f);

    ArrayList<DirectedGraphNode> graph = new ArrayList<>();
    graph.add(a);
    graph.add(b);
    graph.add(c);
    graph.add(d);
    graph.add(e);
    graph.add(f);

    ArrayList<DirectedGraphNode> res = testClass.topSort(graph);
    for(int i = 0; i < res.size(); i++) {
      System.out.println(res.get(i).label);
    }
  }

}
