package BFS;

/**
 * 题目：
 * 934. Shortest Bridge
 * In a given 2D binary array A, there are two islands.
 * (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 *
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 *
 * Return the smallest number of 0s that must be flipped.
 * (It is guaranteed that the answer is at least 1.)
 *
 *
 *
 * Example 1:
 *
 * Input: [[0,1],[1,0]]
 * Output: 1
 * Example 2:
 *
 * Input: [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * Example 3:
 *
 * Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 *
 *
 * Note:
 *
 * 1 <= A.length = A[0].length <= 100
 * A[i][j] == 0 or A[i][j] == 1
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * 笔记：
 * 看note里面说了A.length = A[0].length，所以面试的时候如果例子的row和col长度都一样也可以问一下面试官
 * 重点是一定要用一个boolean去flag第一个岛有没有找到，这样的话就可以节约两层for loop的循环时间不然 break
 * 只能从第一层的循环中退出来，还是会继续找，把第二个也找了00
 * 无论是break还是continue
 * 都是只能从所处在层的循环中跳出来，如果想stop所有循环，就要像下面这么处理
 */
public class ShortestBridge {
  public int shortestBridge(int[][] A) {
    int n = A.length;
    //boolean[][] visited = new boolean[n][n];
    Queue<int[]> queue = new LinkedList<>();
    boolean foundFirstIsland = false; //非常重要！！！
    for(int i = 0; i < n; i++) {
      if(foundFirstIsland) break; //非常重要！！！
      for(int j = 0; j < n; j++) {
        if(A[i][j] == 1) {
          //visited[i][j] = true;
          dfs(A, n, queue, i, j);
          foundFirstIsland = true; //非常重要！！！
          break;
        }
      }
    }

    int steps = 0;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    while(!queue.isEmpty()) {
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        int[] curr = queue.poll();
        int x = curr[0];
        int y = curr[1];
        for(int[] dir : directions) {
          int x1 = x + dir[0];
          int y1 = y + dir[1];
          if(x1 < 0 || x1 >= n || y1 < 0 || y1 >= n) continue;
          if(A[x1][y1] == 1) return steps;
          A[x1][y1] = 2; //不用boolean array的话就要把所有放进queue的点改成2作为标记
          queue.offer(new int[]{x1, y1});
        }
      }
      steps++;
    }
    return steps;
  }

  //dfs to find first island and mark all 1 in this island as 2
  private void dfs(int[][] A, int n, Queue<int[]> queue, int row, int col) {
    if(row < 0 || row >= n || col < 0 || col >= n || A[row][col] != 1) return;
    A[row][col] = 2; //把找到的第一个岛都变成2
    queue.offer(new int[]{row, col});
    dfs(A, n, queue, row + 1, col);
    dfs(A, n, queue, row - 1, col);
    dfs(A, n, queue, row, col + 1);
    dfs(A, n, queue, row, col - 1);
  }

  //Test Case
  public static void main(String[] args) {
    ShortestBridge test = new ShortestBridge();
    int[][] A = {{0,1,0},{0,0,0},{0,0,1}};
    int res = test.shortestBridge(A);
    System.out.println(res);
  }
}
