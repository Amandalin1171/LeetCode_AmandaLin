package GraphSearch;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 787. Cheapest Flights Within K Stops
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 * Now given all the cities and flights, together with starting city src and the destination dst,
 * your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 *
 * Note:
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * The size of flights will be in range [0, n * (n - 1) / 2].
 * The format of each flight will be (src, dst, price).
 * The price of each flight will be in the range [1, 10000].
 * k is in the range of [0, n - 1].
 * There will not be any duplicated flights or self cycles.
 */
public class CheapestFlightsWithinKStops {
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    //build graph
    Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
    for(int[] flight : flights) {
      prices.putIfAbsent(flight[0], new HashMap<>());
      prices.get(flight[0]).put(flight[1], flight[2]);
    }

    //Dijkstra
    PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
      public int compare(int[] a, int[] b) {
        return a[1] - b[1]; //ticket cost ascending
      }
    });
    pq.offer(new int[] {src, 0, K + 1}); //departure, ticket cost, stops
    while(!pq.isEmpty()) {
      int[] curr = pq.poll();
      int city = curr[0];
      int cost = curr[1];
      int stops = curr[2];
      if(city == dst) return cost;
      if(stops > 0) {
        Map<Integer, Integer> next = prices.getOrDefault(city, new HashMap<>());
        for(int k : next.keySet()) {
          pq.offer(new int[] {k, cost + next.get(k), stops - 1});
        }
      }
    }
    return -1;
  }
}
