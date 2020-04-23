package CS5800FinalQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Problem 4
 * Let G=(V,E) be a directed graph in which each vertex is assigned a color, either black or white.
 * A path in this graph is called an alternating path if and only if
 * no two consecutive vertices on the path have the same color.
 * Design an efficient algorithm to determine for all pairs of vertices u,v\in V
 * whether v is reachable from u via an alternating path.
 */
public class AlternatingPaths {
  public boolean[][] AlternatingPaths(int n, Vertex[][] graph) {
    boolean[][] res = new boolean[n][n];
    Map<Vertex, List<Vertex>> neighbors = new HashMap<>();
    Map<Integer, Vertex> hm = new HashMap<>();
    for(Vertex[] edge : graph){
      Vertex key = edge[0];

      // construct graph
      if (!neighbors.containsKey(key)) {
        neighbors.put(edge[0], new ArrayList<>());
      }
      neighbors.get(edge[0]).add(edge[1]);


      // construct id - vertex map
      if(!hm.containsKey(edge[0].id)){
        hm.put(edge[0].id, edge[0]);
      }
      if(!hm.containsKey(edge[1].id)){
        hm.put(edge[1].id, edge[1]);
      }
    }

    for(int i = 0; i < n; i++){
      Set<Vertex> visited = new HashSet<>();
      DFS(res, i, hm.get(i), neighbors, visited);
    }

    return res;



  }

  public void DFS(boolean[][] res, int origin_id, Vertex cur, Map<Vertex, List<Vertex>> neighbors, Set<Vertex> visited){
    if(visited.contains(cur)) return;
    visited.add(cur);
    for(Vertex v : neighbors.getOrDefault(cur, new ArrayList<Vertex>())){
      // color is not the same, can walk through this way
      if(cur.color != v.color){
        res[origin_id][v.id] = true;
        DFS(res, origin_id, v, neighbors, visited);
      }

    }
    visited.remove(cur);
    return;
  }

  class Vertex{
    int id;
    char color;
    // color 'w' : white
    // color 'b' : black
    public Vertex(int id, char color){
      this.id = id;
      this.color = color;
    }
  }

  //Test Case
  public void runTest() {
    Vertex v0 = new Vertex(0, 'w');
    Vertex v1 = new Vertex(1, 'b');
    Vertex v2 = new Vertex(2, 'w');
    Vertex v3 = new Vertex(3, 'b');
    Vertex v4 = new Vertex(4, 'w');
    Vertex v5 = new Vertex(5, 'b');
    Vertex[][] graph = {{v0, v3}, {v0, v1}, {v0, v2}, {v1, v4}, {v2, v5}};
    boolean[][] res = AlternatingPaths(6, graph);
    for (boolean[] row : res) {
      for (boolean b : row) {
        System.out.print(b + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    AlternatingPaths test = new AlternatingPaths();
    test.runTest();
  }
}
