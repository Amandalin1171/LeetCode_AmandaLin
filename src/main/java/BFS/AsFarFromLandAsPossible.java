package BFS;

/**
 * 题目：
 * 1162. As Far from Land as Possible
 *
 * Given an N x N grid containing only values 0 and 1, where 0 represents water
 * and 1 represents land, find a water cell such that its distance to the nearest
 * land cell is maximized and return the distance.
 *
 * The distance used in this problem is the Manhattan distance: the distance
 * between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 *
 * If no land or water exists in the grid, return -1.
 *
 * Example 1:
 * Input: [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 2
 * Explanation:
 * The cell (1, 1) is as far as possible from all the land with distance 2.
 *
 * Example 2:
 * Input: [[1,0,0],[0,0,0],[0,0,0]]
 * Output: 4
 * Explanation:
 * The cell (2, 2) is as far as possible from all the land with distance 4.
 *
 * Note:
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] is 0 or 1
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * 笔记：
 * 思路：
 * Solution: BFS
 * Put all land cells into a queue as source nodes and BFS for water cells,
 * the last expanded one will be the farthest.
 *
 * Time compleixty: O(n^2)
 * Space complexity: O(n^2)
 *
 * 思考： 为什么从land(1)开始找而不是从water(0)开始找？
 * 题目要求是：find a water cell such that its distance to the nearest
 * land cell is maximized and return the distance.
 * 找一个0距离最近的1的距离是最大的
 * 所以换句话说就是以所有的1为中心开始扫描，一圈一圈往外扫描，扫到的最后一个0，就是直到扫到的全都是1了，里面那个最后一个0
 * 就是我们要找的结果
 * 如果从0开始找的话很难hold这个最近且最远的距离！
 */
public class AsFarFromLandAsPossible {
  public int maxDistance(int[][] grid) {
    if(grid == null || grid.length == 0) return -1;
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    Queue<int[]> queue = new LinkedList<>();
    for(int i = 0; i < grid.length; i++) {
      for(int j = 0; j < grid[0].length; j++) {
        if(grid[i][j] == 1) {
          queue.offer(new int[] {i, j});
          visited[i][j] = true;
        }
      }
    }

    int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    int level = -1; //因为塞进去第一个land也就是1的点的时候，哪怕附近全是1，也会跑一遍while循环也会level++
    //所以这一遍应该得到的是0，所以让一开始的level是-1
    //但是如果结果是0就意味着全是land,这种情况应该返回值是-1根据题目要求，所以下面的返回要一步判断
    while(!queue.isEmpty()) {
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        int[] curr = queue.poll();
        for(int[] dir : directions) {
          int x = curr[0] + dir[0];
          int y = curr[1] + dir[1];
          if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !visited[x][y] && grid[x][y] == 0) {
            visited[x][y] = true;
            queue.offer(new int[]{x, y});
          }
        }
      }
      level++;
    }
    if(level <= 0) return -1;
    else return level;
  }

  //Test Case
  public static void main(String[] args) {
    AsFarFromLandAsPossible test = new AsFarFromLandAsPossible();
    int[][] grid = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    int res = test.maxDistance(grid);
    System.out.println(res);
  }
}
