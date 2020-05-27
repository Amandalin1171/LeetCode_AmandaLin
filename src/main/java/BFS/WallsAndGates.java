package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 286. Walls and Gates
 * You are given a m x n 2D grid initialized with these three possible values.
 *
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 * Example:
 *
 * Given the 2D grid:
 *
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 * After running your function, the 2D grid should be:
 *
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 */

/**
 * 这道题刚开始没有用visited boolean的bug在于：如果遇到相邻的点，会被重复offer 到queue里面，比如给的例子，左上角两个2
 * 会由于poll的顺序不同导致最左边的2重复进入queue就会被改变数字。
 * 解决办法就是visited的就不再offer进queue中。
 * 当然有一个更牛逼的解法就是offer邻里四周之前就直接改变数值，见74行
 */
public class WallsAndGates {
  public void wallsAndGates(int[][] rooms) {
    //corner case
    if(rooms == null || rooms.length == 0) return;

    int m = rooms.length;
    int n = rooms[0].length;
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[m][n];

    for(int i = 0; i < m; i++) {
      for(int j = 0; j < n; j++) {
        if(rooms[i][j] == 0) {
          queue.offer(new int[] {i, j});
          visited[i][j] = true;
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
        int x = curr[0];
        int y = curr[1];
        rooms[x][y] = count;
        //System.out.println("X: " + x + ", Y: " + y + ", value: " + rooms[x][y]);
        for(int[] dir : directions) {
          int newX = x + dir[0];
          int newY = y + dir[1];

          if(newX < 0 || newY < 0 || newX >= m || newY >= n || rooms[newX][newY] != Integer.MAX_VALUE || visited[newX][newY]) continue;
          visited[newX][newY] = true;
          //rooms[newX][newY] = rooms[x][y] + 1;
          queue.offer(new int[] {newX, newY});

        }
      }
      count++;
    }
  }
}
