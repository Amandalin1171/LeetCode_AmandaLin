package GraphSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * You want to rank all the basketball players (let’s assume there are n players in total). So you collected all the 1-on-1 games they played (let’s assume there are m games you collected). Each game you collected is a tuple of the form (player_1, player_2, winner_of_the_game). For example (A, B, A) means player A and B played a 1-on-1 game and player A won that game.
 *
 * Given all the records, you need to rank all the n players in a total order. For example, if you are given the following records for player A, B, and C
 *
 * (A, B, A), (B, C, B), (A, C, A)
 *
 * then your order of the players should be A, B, C.
 *
 * In case there are inconsistent game records, you should be able to detect it and report an error. For example, if you are given the following records for player A, B, and C
 *
 * (A, B, A), (B, C, B), (A, C, C)
 *
 * then your algorithm should return an error message.
 *
 * In case there aren't enough game records to give a total order, then any order that is consistent with the game records you received is fine. For example, if you are given the following records for player A, B, and C
 *
 * (A, B, A), (A, C, A)
 *
 * then your algorithm should either return A, B, C or A, C, B.
 *
 * Please design an efficient algorithm for this.
 */
public class BasketballPlayerRanking {
  public int[] rankPlayers(int[][] records) {
    //each int[] in records will have length of 3
    Map<Integer, Integer> indegree = new HashMap<>();//key: player ID, value: indegree of this ID
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    buildGraph(records, indegree, graph);
    return bfs(indegree, graph);
  }

  private void buildGraph(int[][] records, Map<Integer, Integer> indegree, Map<Integer, Set<Integer>> graph) {

    for(int[] record : records) {
      int player1 = record[0];
      int player2 = record[1];
      graph.putIfAbsent(player1, new HashSet<>());
      graph.putIfAbsent(player2, new HashSet<>());
      indegree.putIfAbsent(player1, 0);
      indegree.putIfAbsent(player2, 0);
      if(record[2] == player1) {
        graph.get(player1).add(player2);
        indegree.put(player2, indegree.getOrDefault(player2, 0) + 1);
      } else {
        graph.get(player2).add(player1);
        indegree.put(player1, indegree.getOrDefault(player1, 0) + 1);
      }
    }
  }

  private int[] bfs(Map<Integer, Integer> indegree, Map<Integer, Set<Integer>> graph) {
    List<Integer> res = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    for(int out : graph.keySet()) {
      //System.out.println(out);
      if(indegree.get(out) == 0) {
        queue.offer(out);
        res.add(out);
      }
    }
    while(!queue.isEmpty()) {
      int out = queue.poll();
      for(int in : graph.get(out)) {
        indegree.put(in, indegree.get(in) - 1);
        if(indegree.get(in) == 0) {
          queue.offer(in);
          res.add(in);
        }
      }
    }

    if(res.size() == graph.size()) {
      int[] finalRes = new int[res.size()];
      for(int i = 0; i < res.size(); i++) {
        finalRes[i] = res.get(i);
      }
      return finalRes;
    } else {
      return null;
    }
  }

  //Test Case
  public static void main(String[] args) {
    BasketballPlayerRanking test = new BasketballPlayerRanking();
    int[][] records1 = {{1, 2, 1}, {2, 3, 2}, {1, 3, 1}};
    int[][] records2 = {{1, 2, 1}, {2, 3, 2}, {1, 3, 3}};
    int[] res1 = test.rankPlayers(records1);
    int[] res2 = test.rankPlayers(records2);
    for(int i = 0; i < res1.length; i++) {
      System.out.println(res1[i]);
    }

    System.out.println(res2);
  }
}
