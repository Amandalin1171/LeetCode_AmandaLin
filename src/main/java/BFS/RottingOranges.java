package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 * In a given grid, each cell can have one of three values:
 *
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 *
 * Example 1:
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 * Input: [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 *
 * Example 3:
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 */

public class RottingOranges {
  public int orangesRotting(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    Queue<int[]> queue = new LinkedList<>();

    for(int i = 0; i < m; i++) {
      for(int j = 0; j < n; j++) {
        if(grid[i][j] == 2) {
          queue.offer(new int[] {i, j});
        }
      }
    }

    //BFS
    int count = 0;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    while(!queue.isEmpty()) {
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        int[] curr = queue.poll();
        for(int[] dir : directions) {
          int newX = curr[0] + dir[0];
          int newY = curr[1] + dir[1];
          if(newX < 0 || newY < 0 || newX >= m || newY >= n || grid[newX][newY] != 1) continue;
          queue.offer(new int[] {newX, newY});
          grid[newX][newY] = 2;
        }
      }
      count++;
    }

    //check for fresh orange
    //这题一开始读题不完整导致有bug，如果impossible就返回-1
    for(int i = 0; i < m; i++) {
      for(int j = 0; j < n; j++) {
        if(grid[i][j] == 1) return -1;
      }
    }
    return count == 0 ? 0 : count - 1;
  }
}
