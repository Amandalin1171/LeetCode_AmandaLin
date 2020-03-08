package DFS;

/**
 * 题目：
 * 37. Sudoku Solver
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 */

/**
 * 笔记：
 * 这题的填充更像是N-Queens_I那道题
 * 比较tricky的点就是如何trace这些index
 * 见下：1. 用二维数组记录不一定是因为这俩数组是代表X坐标和Y坐标， 就想象成两个格子，每个格子存储每次必要携带的东西。
 * 看下面记录的就是，第一个格子存储的坐标一维化的index，第二个格子存储的是1到9里面哪些数字被用到了。
 * 2. 二维坐标一维化的方法：k = x * n + y 此处n是行数，想想第一行和第二行就明白啦！
 */
public class SudokuSolver {
  public void solveSudoku(char[][] board) {
    //这里虽然是二维boolean数组，但绝不是坐标，第一个是二维坐标一维化的定位，就9个格子
    //第二个是存储1-9这几个数字哪些被用到了哪些还没被用到
    //第一个是index用来定位，第二个是记录哪个数字used or not used
    boolean[][] rows = new boolean[9][9];
    boolean[][] cols = new boolean[9][9];
    boolean[][] boxes = new boolean[9][9];
    for(int i = 0; i < 9; i++) {
      for(int j = 0; j < 9; j++) {
        if(board[i][j] != '.') {
          int k = (i / 3) * 3 + (j / 3); //二维坐标一维化： index = x * n + y (n 是行数)
          int num = board[i][j] - '1';
          rows[i][num] = true;
          cols[j][num] = true;
          boxes[k][num] = true;
        }
      }
    }
    dfs(board, 0, rows, cols, boxes);
  }

  private boolean dfs(char[][] board, int index, boolean[][] rows, boolean[][] cols, boolean[][] boxes) {
    if(index == 9 * 9) return true;
    int row = index / 9;
    int col = index % 9;
    int box = (row / 3) * 3 + col / 3;
    if(board[row][col] != '.') {
      return dfs(board, index + 1, rows, cols, boxes);
    } else {
      for(char c = '1'; c <= '9'; c++) {
        int num = c - '1';
        if(!rows[row][num] && !cols[col][num] && !boxes[box][num]) {
          board[row][col] = c;
          rows[row][num] = true;
          cols[col][num] = true;
          boxes[box][num] = true;
          if(dfs(board, index + 1, rows, cols, boxes)) return true;
          rows[row][num] = false;
          cols[col][num] = false;
          boxes[box][num] = false;
          board[row][col] = '.';
        }
      }
    }
    return false;
  }

  //Test Case
  public static void main(String[] args) {
    SudokuSolver testClass = new SudokuSolver();
    char[][] board =
        {{'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}};
    testClass.solveSudoku(board);
    for(int i = 0; i < 9; i++) {
      for(int j = 0; j < 9; j++) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
  }
}
