package GraphSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 题目：
 * 133. Clone Graph
 *
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 *
 * Test case format:
 *
 * For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.
 *
 * Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 */

/**
 * 方法1： DFS
 * 找到感觉就是：recursion function中的return value是每一次递归的返回值，不是最终的返回值。
 * 找到重复的动作，找到可以替代的角色（input)
 */
public class CloneGraph {
  Map<Node, Node> map = new HashMap<>();
  public Node cloneGraphDFS(Node node) {
    if(node == null) return null;
    Node newNode = new Node(node.val, new ArrayList<>());
    if(!map.containsKey(node)) {
      map.put(node, newNode);
      for(Node n : node.neighbors) {
        newNode.neighbors.add(cloneGraphDFS(n));
      }
    }
    return map.get(node);
  }

  /**
   * 方法2： BFS
   */
  public Node cloneGraphBFS(Node node) {
    if(node == null) return null;
    Map<Node, Node> map = new HashMap<>();
    Queue<Node> queue = new LinkedList<>();

    queue.add(node);
    Node newNode = new Node(node.val, new ArrayList<>());
    map.put(node, newNode);

    while(!queue.isEmpty()) {
      Node curr = queue.poll();
      for(Node neighbor : curr.neighbors) {
        if(!map.containsKey(neighbor)) {
          map.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
          queue.add(neighbor);
        }
        //这句非常关键，就是copy的时候一定要把每一个neighbor的点从map中copy过来，带上一家人
        //也就是带上自己的val和自己的neighbors
        map.get(curr).neighbors.add(map.get(neighbor));
      }
    }
    return map.get(node);
  }

}
