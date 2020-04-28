package CodingExerciseWithGary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 310. Minimum Height Trees
 * For an undirected graph with tree characteristics, we can choose any node as the root.
 * The result graph is then a rooted tree.
 * Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
 * Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 *
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1.
 * You will be given the number n and a list of undirected edges (each edge is a pair of labels).
 *
 * You can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 * Example 1 :
 *
 * Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *
 *         0
 *         |
 *         1
 *        / \
 *       2   3
 *
 * Output: [1]
 */
public class MinimumHeightTrees {
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    List<Integer> finalRes = new ArrayList<>();
    if(n == 0) return finalRes;
    else if(edges == null || edges.length == 0) {
      for(int i = 0; i < n; i++) finalRes.add(i);
      return finalRes;
    }

    int[] numOfEdges = new int[n]; //count connections
    Map<Integer, Set<Integer>> neighbors = new HashMap<>(); //store neighbors of each node

    //build connections array
    for(int i = 0; i < edges.length; i++) {
      numOfEdges[edges[i][0]]++;
      numOfEdges[edges[i][1]]++;
    }
    for(int i = 0; i < n; i++) neighbors.put(i, new HashSet<Integer>());

    //build neighbors map
    for(int i = 0; i < edges.length; i++) {
      neighbors.get(edges[i][0]).add(edges[i][1]);
      neighbors.get(edges[i][1]).add(edges[i][0]);
    }

    //BFS
    Queue<Integer> queue = new LinkedList<>();
    for(int i = 0; i < n; i++) {
      if(numOfEdges[i] == 1) {
        queue.offer(i);
      }
    }

    Set<Integer> res = new HashSet<>(); // store result nodes
    while(!queue.isEmpty()) {
      int size = queue.size();
      if(size == 0) {
        break;
      } else {
        res.clear();
      } // the Integers stored in Set in last loop is the result
      for(int i = 0; i < size; i++) {
        int curr = queue.poll();
        res.add(curr);
        numOfEdges[curr]--;
        for(int currNeighbor : neighbors.get(curr)) {
          numOfEdges[currNeighbor]--;
          if(neighbors.get(currNeighbor).contains(curr)) {
            neighbors.get(currNeighbor).remove(curr);
          }
          if(numOfEdges[currNeighbor] == 1) {
            queue.offer(currNeighbor);
          }
        }
      }
    }


    for(int i : res) finalRes.add(i);
    return finalRes;
  }
}
