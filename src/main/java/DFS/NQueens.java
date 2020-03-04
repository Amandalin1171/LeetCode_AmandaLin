package DFS;

/**
 * 题目：
 * 51. N-Queens
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 * Output: [
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
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 笔记：
 * 此处补充一些基础的学术知识：
 * Bits and Bytes
 * 笔记知识来源： https://web.stanford.edu/class/cs101/bits-bytes.html
 * Bit：
 * a "bit" is atomic: the smallest unit of storage
 * A bit stores just a 0 or 1
 * "In the computer it's all 0's and 1's" ... bits
 * Anything with two separate states can store 1 bit
 * In a chip: electric charge = 0/1
 * In a hard drive: spots of North/South magnetism = 0/1
 * A bit is too small to be much use
 * Group 8 bits together to make 1 byte
 *
 * Byte：
 * One byte = collection of 8 bits
 * e.g. 0 1 0 1 1 0 1 0
 * One byte can store one character, e.g. 'A' or 'x' or '$'
 */

/**
 * 笔记：
 * 这题解法关键在于矩阵中各种参数的处理，这块数学不好确实脑壳疼T^T
 * 1. 用一个for loop去走每一行，所以行不用记录，每次都是不同的行，for loop里面的index i 就代表行
 * 然后用三个boolean array去记录所在列，所在的45度对角线（左上->右下）所在的135度对角线（右上->左下）
 * 注意的是，Boolean array列的长度是n, 对角线的长度是2n - 1.
 *
 * 2. index 处理，个人认为是一开始最懵逼的地方
 * 每次都进行新的一行，所以行不用检查
 * 90度代表棋盘的列
 * 45度对角线（从左上到右下）同一条线上 row + col 是相等的，因此可用作 index
 * 135度对角线（从左下到右上）可从 (0,0) 即 index (n - 1) 为起点
 * 因为同一条对角线 row - col 值恒定，可用作 offset 表示对角线的 index.
 *
 * 3. 因为每次写完这个棋盘之后又要for loop一遍去填入结果，所以写一个fillAnswer的helper function比较好
 * 每次传进去res list和写好的棋盘即可。
 */
public class NQueens {
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> res = new ArrayList<>();
    if(n <= 0) return res;
    char[][] chessBoard = new char[n][n];
    for(char[] c : chessBoard) Arrays.fill(c, '.');
    boolean[] col = new boolean[n];
    boolean[] diagonal_45 = new boolean[2 * n - 1];
    boolean[] diagonal_135 = new boolean[2 * n - 1];
    dfs(n, 0, res, chessBoard, col, diagonal_45, diagonal_135);
    return res;
  }

  private void dfs(int n, int row, List<List<String>> res, char[][] chessBoard, boolean[] col, boolean[] diagonal_45, boolean[] diagonal_135) {
    if(row == n) {
      buildRes(res, chessBoard);
      return;
    }

    for(int i = 0; i < n; i++) {
      if(!col[i] && !diagonal_45[i + row] && !diagonal_135[i - row + n - 1]) {
        col[i] = true; //列的index是i
        diagonal_45[i + row] = true; //左上右下对角线的index是 i + row
        diagonal_135[i - row + n - 1] = true; //右上左下对角线的index是 n - 1 + i - row
        chessBoard[row][i] = 'Q';
        dfs(n, row + 1, res, chessBoard, col, diagonal_45, diagonal_135);
        col[i] = false;
        diagonal_45[i + row] = false;
        diagonal_135[i - row + n - 1] = false;
        chessBoard[row][i] = '.';
      }
    }
  }

  private void buildRes(List<List<String>> res, char[][] chessBoard) {
    List<String> curr = new ArrayList<>();
    for(char[] c : chessBoard) {
      curr.add(String.valueOf(c));
    }
    res.add(curr);
  }

  //Test Case
  public static void main(String[] args) {
    NQueens testClass = new NQueens();
    List<List<String>> res = testClass.solveNQueens(5);
    for(int i = 0; i < res.size(); i++) {
      List<String> curr = res.get(i);
      for(int j = 0; j < curr.size(); j++) {
        System.out.print(curr.get(j));
      }
      System.out.println();
    }
  }
}
