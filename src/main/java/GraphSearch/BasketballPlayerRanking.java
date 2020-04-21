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
  public char[] rankPlayers(char[][] records) {
    //each char[] in records will have length of 3
    int[] indegree = new int[256];
    Map<Character, Set<Character>> graph = new HashMap<>();
    buildGraph(records, indegree, graph);
    return bfs(indegree, graph);
  }

  private void buildGraph(char[][] records, int[] indegree, Map<Character, Set<Character>> graph) {

    for(char[] record : records) {
      char player1 = record[0];
      char player2 = record[1];
      graph.putIfAbsent(player1, new HashSet<>());
      graph.putIfAbsent(player2, new HashSet<>());
      if(record[2] == player1) {
        graph.get(player1).add(player2);
        indegree[player2 - 'A']++;
      } else {
        graph.get(player2).add(player1);
        indegree[player1 - 'A']++;
      }
    }
  }

  private char[] bfs(int[] indegree, Map<Character, Set<Character>> graph) {
    List<Character> res = new ArrayList<>();
    Queue<Character> queue = new LinkedList<>();
    for(char out : graph.keySet()) {
      if(indegree[out - 'A'] == 0) {
        queue.offer(out);
        res.add(out);
      }
    }
    while(!queue.isEmpty()) {
      char out = queue.poll();
      System.out.println(out);
      for(Character in : graph.get(out)) {
        indegree[in - 'A']--;
        if(indegree[in - 'A'] == 0) {
          queue.offer(in);
          res.add(in);
        }
      }
    }

    if(res.size() == graph.size()) {
      char[] finalRes = new char[res.size()];
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
    char[][] records1 = {{'A', 'B', 'A'}, {'B', 'C', 'B'}, {'A', 'C', 'A'}};
    char[][] records2 = {{'A', 'B', 'A'}, {'B', 'C', 'B'}, {'A', 'C', 'C'}};
    System.out.println(test.rankPlayers(records1));
    System.out.println(test.rankPlayers(records2));
  }
}
