package DFS;

/**
 * 题目：
 * 36. Valid Sudoku
 *
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need
 * to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 *
 * Example 1:
 *
 * Input:
 * [
 *   ["5","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: true
 *
 * Explanation: Same as Example 1, except with the 5 in the top left corner being
 *     modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * The given board contain only digits 1-9 and the character '.'.
 * The given board size is always 9x9.
 */

/**
 * 笔记：
 * 关键的是几个点
 * 1. block = col / 3 + (row / 3) * 3 就可以代表一个具体 block.
 * 2. 建立三个boolean array来存储是否用过那个数字就OK了，在第一层for loop下面
 * 每一层for loop代表一个行， 一个列，一个box，所以共生成9个这组（三个）Boolean array
 * 3. int num = board[i][j] - '1'; character转换成数字的方法，数字自动ASCII比较大小
 */
public class ValidSudoku {
  public boolean isValidSudoku(char[][] board) {
    for(int i = 0; i < 9; i++) {
      //开三个boolean array来记录三类数独要求的数字单一出现规则是否重复了：行，列，格子，都是9个
      boolean[] rowSeen = new boolean[9];
      boolean[] colSeen = new boolean[9];
      boolean[] boxSeen = new boolean[9];

      for(int j = 0; j < 9; j++) {
        //check row
        if(board[i][j] != '.') {
          int num = board[i][j] - '1';
          if(rowSeen[num]) return false;
          rowSeen[num] = true;
        }

        //check col
        if(board[j][i] != '.') {
          int num = board[j][i] - '1';
          if(colSeen[num]) return false;
          colSeen[num] = true;
        }

        //check box
        int x = (i / 3) * 3 + j / 3;
        int y = (i % 3) * 3 + j % 3;
        if(board[x][y] != '.') {
          int num = board[x][y] - '1';
          if(boxSeen[num]) return false;
          boxSeen[num] = true;
        }
      }
    }
    return true;
  }

  //Test Case
  public static void main(String[] args) {
    ValidSudoku testCase = new ValidSudoku();
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
    boolean res = testCase.isValidSudoku(board);
    System.out.println(res);
  }
}
