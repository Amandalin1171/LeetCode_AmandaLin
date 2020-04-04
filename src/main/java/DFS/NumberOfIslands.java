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
 * 另一个用到flood filling的题是SurroundedRegions，快去复习吧！！！
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

  /**
   * 这道题 Union Find 的解法见下：
   *
   * Union Find的解法主要是为了NumberOfIslandsII做准备，找岛二只能用unionfind解
   * 注意二位数组坐标一维化的时候：
   * x : m
   * y : n
   * 坐标 k = x * n + y
   * 注意是乘以n 不是乘以m ！！！！！
   *
   * class Solution {
   *     class UnionFind{
   *         private int count;
   *         private int[] parents;
   *         private int[] rank;
   *
   *         public UnionFind(char[][] grid){
   *             count = 0;
   *             int m = grid.length;
   *             int n = grid[0].length;
   *             parents = new int[m * n];
   *             rank = new int[m * n];
   *
   *             for(int i = 0; i < m; i++) {
   *                 for(int j = 0; j < n; j++) {
   *                     if(grid[i][j] == '1') {
   *                         parents[i * n + j] = i * n + j; 看看这里 二维坐标一维化别再出错啦！
   *                         count++;
   *                     }
   *                 }
   *             }
   *         }
   *
   *         public int find(int child) {
   *             if(parents[child] != child) {
   *                 parents[child] = find(parents[child]);
   *                 return parents[child];
   *             }
   *             return child;
   *         }
   *
   *         public void union(int x, int y) {
   *             int px = find(x);
   *             int py = find(y);
   *             if(px != py) {
   *                 if(rank[px] > rank[py]) parents[py] = px;
   *                 else if(rank[px] < rank[py]) parents[px] = py;
   *                 else {
   *                     parents[px] = py;
   *                     rank[py]++;
   *                 }
   *                 count--;
   *             }
   *         }
   *
   *         public int getCount() {
   *             return count;
   *         }
   *     }
   *     public int numIslands(char[][] grid) {
   *         if(grid == null || grid.length == 0) return 0;
   *         int m = grid.length;
   *         int n = grid[0].length;
   *         int res = 0;
   *         UnionFind uf = new UnionFind(grid);
   *         for(int i = 0; i < m; i++) {
   *             for(int j = 0; j < n; j++) {
   *                 if(grid[i][j] == '1') {
   *                     grid[i][j] = '0';
   *                     if(i - 1 > 0 && grid[i - 1][j] == '1') uf.union(i * n + j, (i - 1) * n + j);
   *                     if(i + 1 < m && grid[i + 1][j] == '1') uf.union(i * n + j, (i + 1) * n + j);
   *                     if(j - 1 > 0 && grid[i][j - 1] == '1') uf.union(i * n + j, i * n + j - 1);
   *                     if(j + 1 < n && grid[i][j + 1] == '1') uf.union(i * n + j, i * n + j + 1);
   *                     }
   *             }
   *         }
   *         return uf.getCount();
   *     }
   * }
   */
}
