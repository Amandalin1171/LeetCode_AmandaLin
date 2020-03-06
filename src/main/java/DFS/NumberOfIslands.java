package DFS;

/**
 * 题目：
 * 200. Number of Islands
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 */

/**
 * 笔记：
 * 第一个解法：DFS using Flood Fill Algorithm, 注意，No backtracking!!!!
 * Flood Fill Algorithm：
 * Flood fill is an algorithm mainly used to determine a bounded area connected to a given node in a multi-dimensional array.
 * It is a close resemblance to the bucket tool in paint programs.
 *
 * 这题用dfs解决的思路就是直接遇到一个1就开始flood fill,先把这个变成0，再把邻居们都变成0，
 * 如果往外扩展过程中都是0了就证明是孤岛了就count++, 然后不用变回去1，就这样继续for loop
 * 走一圈，把所有1全部都改成0之后看一共能count到几个孤立的岛，就是岛屿的数量。
 * 所以没有backtrack的过程，就是单纯的fill
 */
public class NumberOfIslands {
  public int numIslands(char[][] grid) {
    int count = 0;
    for(int i = 0; i < grid.length; i++) {
      for(int j = 0; j < grid[0].length; j++) {
        if(grid[i][j] == '1') { //找到一个岛的位置，进入dfs
          dfs(grid, i, j);
          count++;
        }
      }
    }
    return count;
  }

  private void dfs(char[][] grid, int row, int col) {
    if((row < 0 || row >= grid.length) || (col < 0 || col >= grid[0].length)) return; //out of bounds judgement
    if(grid[row][col] == '0') return;

    grid[row][col] = '0'; //进入dfs循环先把脚下的1变成0

    //开始看邻里四周是不是都是0，并且每个邻居的邻居都是不是0，开始找
    //如果都是0，就是上面的退出条件，退出到上一层，层层退出，退到最外面就是找到孤岛了，就count++
    dfs(grid, row + 1, col);
    dfs(grid, row - 1, col);
    dfs(grid, row, col + 1);
    dfs(grid, row, col - 1);
  }

  //Test Case
  public static void main(String[] args) {
    NumberOfIslands testClass = new NumberOfIslands();
    char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'},{'1', '1', '0', '0', '0'},{'0', '0', '0', '0', '0'}};
    int res = testClass.numIslands(grid);
    System.out.println(res);
  }
}
