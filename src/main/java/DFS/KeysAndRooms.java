package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：
 * 841. Keys and Rooms
 *
 * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.
 *
 * Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.
 *
 * Initially, all the rooms start locked (except for room 0).
 *
 * You can walk back and forth between rooms freely.
 *
 * Return true if and only if you can enter every room.
 *
 * Example 1:
 *
 * Input: [[1],[2],[3],[]]
 * Output: true
 * Explanation:
 * We start in room 0, and pick up key 1.
 * We then go to room 1, and pick up key 2.
 * We then go to room 2, and pick up key 3.
 * We then go to room 3.  Since we were able to go to every room, we return true.
 * Example 2:
 *
 * Input: [[1,3],[3,0,1],[2],[0]]
 * Output: false
 * Explanation: We can't enter the room with number 2.
 * Note:
 *
 * 1 <= rooms.length <= 1000
 * 0 <= rooms[i].length <= 1000
 * The number of keys in all rooms combined is at most 3000.
 */

/**
 * 笔记：
 * 这道题说白了就是看所有的room是否能够连接到一起
 * 转换思路：这题就是检查整张图graph是不是一个connected component)
 * 所以就变得很简单，只需要dfs判断这个图是不是一个联系到一起的图就可以了
 */
public class KeysAndRooms {
  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    int n = rooms.size(); // number of rooms
    boolean[] visited = new boolean[n];
    dfs(rooms, visited, 0);
    for(int i = 0; i < n; i++) {
      if(!visited[i]) return false;
    }
    return true;
  }

  private void dfs(List<List<Integer>> rooms, boolean[] visited, int index) {
    if(visited[index]) return;
    visited[index] = true;
    List<Integer> curr = rooms.get(index);
    for(int i : curr) {
      dfs(rooms, visited, i);
    }
  }

}
