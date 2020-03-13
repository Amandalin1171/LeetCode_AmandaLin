package DFS;

/**
 * 题目：
 * 827. Making A Large Island
 *
 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1.
 *
 * After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).
 *
 * Example 1:
 *
 * Input: [[1, 0], [0, 1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: [[1, 1], [1, 0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: [[1, 1], [1, 1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 *
 */

/**
 * 笔记：
 * 这道题我上来的理解就是：
 * corner case是场面上全是1，没有0，这种情况就返回 m * n就可以了；
 * 其他的情况把一个0改成1一定会导致更大的岛屿面积，所以只需要考虑脚下是水（0）的情况
 * 当你遇到一片水：0，有两个选择，变成1或者不变成1，变成1的话就跟找岛屿面积一样开始dfs
 *
 * 此刻这个0不变成1，就是找另一个位置变成1，也就是上一轮退出的之后把刚刚变成1的0还原成0， 进行下一步for loop，
 * 这就是下面的代码，虽然有点慢，但是运用了 Max Area of Island这道题的思想（这题我一遍过了就没整理，可以自己回去看看）
 */
public class MakingALargeIsland {
  public int largestIsland(int[][] grid) {
    int area = 0;
    for(int i = 0; i < grid.length; i++) {
      for(int j = 0; j < grid[0].length; j++) {
        if(grid[i][j] == 0) {
          grid[i][j] = 1;
          int[] curArea = {0};
          //每一层dfs都要开一个棋盘boolean array去记录是不visited
          //类似于把1变成0的过程，也可以每一层放进去一个复制的棋盘，然后sink island
          //就是复制棋盘比较复杂，直接生成boolean array比较方便
          dfs(grid, i, j, curArea, new boolean[grid.length][grid[0].length]);
          area = Math.max(area, curArea[0]);
          grid[i][j] = 0;
        }
      }
    }
    if(area == 0) return grid.length * grid[0].length;
    else return area;
  }

  private void dfs(int[][] grid, int row, int col, int[] curArea, boolean[][] visited) {
    if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return;
    //这里一定要注意返回条件是要么四周都被我们后来变成了岛屿（因为我们这次是用的boolean 棋盘来sink island，
    //所以不能都用0， 1来判断，
    //visited[row][col] 全都被我们变成水了，就是后来遍历过了
    //grid[row][col] == 0 就是一开始就是水，不是我们sink island 导致的
    //所以这里有两个条件，OR的关系，存在一个即可
    if(visited[row][col] || grid[row][col] == 0) return;

    visited[row][col] = true;
    curArea[0] = curArea[0] + 1;
    dfs(grid, row + 1, col, curArea, visited);
    dfs(grid, row - 1, col, curArea, visited);
    dfs(grid, row, col + 1, curArea, visited);
    dfs(grid, row, col - 1, curArea, visited);
  }

  //Test Case
  public static void main(String[] args) {
    MakingALargeIsland testClass = new MakingALargeIsland();
    int[][] M = {{1, 0}, {0, 1}};
    int res = testClass.largestIsland(M);
    System.out.println(res);
  }
}
