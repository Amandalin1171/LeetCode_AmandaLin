package DFS;

/**
 * 问题：
 * 52. N-Queens II
 *
 * The n-queens puzzle is the problem of placing n queens
 * on an n×n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * Example:
 *
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 */

import java.util.Arrays;

/**
 * 笔记：
 * 这道题跟N-Queens I的不同之处在于要返回一个count值，所以解题的时候出现了我一直会出现的bug！！！
 * 那就是每次dfs带进去一个integer,然后这玩意是inmmutable的，之前已经说过了都哪些是immutable的呀？
 * 答案是：String + primitive type 的wrapper class
 * 所以解决的办法用一个一个位置的array int[]来存储，很丑陋但可行。
 * 或者就是helper function 每次符合条件的时候返回一个int，在求解的class里面sum起来即可！
 *
 * 解决起来跟N-Queens一样，还是for loop 行，然后用三个Boolean array 来存储列，两条对角线是否用过， DFS + backtracking
 * 棋盘上这地儿是Q，三个Boolean array对应的值就是true,backtrack的时候全变成false,然后棋盘上的位置变成'.'
 * 结合N-Queens一起复习一下吧！
 *
 * 对角线英文：diagonal diagonal diagonal 卧槽这么简单词汇都不会，咋考的GMAT！！！
 */
public class NQueens_II {
  public int totalNQueens(int n) {
    int res = 0;
    if(n <= 0) return res;
    boolean[] col = new boolean[n];
    boolean[] diagonal_45 = new boolean[2 * n - 1];
    boolean[] diagonal_135 = new boolean[2 * n - 1];
    char[][] chessBoard = new char[n][n];
    for(char[] c : chessBoard) Arrays.fill(c, '.');
    return dfs(n, 0, chessBoard, col, diagonal_45, diagonal_135);
  }

  //每次都是进入不同的行，row 或者写作depth, 所以行不用check
  private int dfs(int n, int row, char[][] chessBoard, boolean[] col, boolean[] diagonal_45, boolean[] diagonal_135) {
    if(row == n) return 1;
    int count = 0;
    for(int i = 0; i < n; i++) {
      if(!col[i] && !diagonal_45[i + row] && !diagonal_135[i - row + n - 1]) {
        col[i] = true;
        diagonal_45[i + row] = true;
        diagonal_135[i - row + n - 1] = true;
        chessBoard[row][i] = 'Q';
        dfs(n, row + 1, chessBoard, col, diagonal_45, diagonal_135);
        col[i] = false;
        diagonal_45[i + row] = false;
        diagonal_135[i - row + n - 1] = false;
        chessBoard[row][i] = '.';
      }
    }
    return count;
  }

  //Test Case
  public static void main(String[] args) {
    NQueens_II testClass = new NQueens_II();
    int res = testClass.totalNQueens(4);
    System.out.println(res);
  }
}
