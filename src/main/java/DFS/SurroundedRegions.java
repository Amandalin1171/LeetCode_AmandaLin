package DFS;

/**
 * 题目：
 * 130. Surrounded Regions
 *
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Explanation:
 *
 * Surrounded regions shouldn’t be on the border,
 * which means that any 'O' on the border of the board are not flipped to 'X'.
 * Any 'O' that is not on the border and it is not connected to an 'O' on the border
 * will be flipped to 'X'. Two cells are connected
 * if they are adjacent cells connected horizontally or vertically.
 */

/**
 * 笔记：
 * 只有边缘的O不能被改，其余的都要改成X。
 * 一坨O，里面但凡有一个在边缘，这一坨就不能被改成X。
 * 所以先traverse四个边缘，以每个脚下的点flood fill，把边缘和它的邻居们的O变成另一个字母比如A，
 * 然后traverse整个矩阵，把剩下的O，也就是此刻所有的O都改成X，把遇到的奇葩字母A改回O
 */
public class SurroundedRegions {
  public void solve(char[][] board) {
    if(board == null || board.length == 0) return;

    //traverse四个边界，以每个点为中心找邻居flood filling，遇到O就 call dfs 把O变成A
    for(int i = 0; i < board.length; i++) {
      if(board[i][0] == 'O') dfs(board, i, 0);
      if(board[i][board[0].length - 1] == 'O') dfs(board, i, board[i].length - 1);
    }

    for(int i = 0; i < board[0].length; i++) {
      if(board[0][i] == 'O') dfs(board, 0, i);
      if(board[board.length - 1][i] == 'O') dfs(board, board.length - 1, i);
    }

    //之后做两轮变换：
    //1. 把剩下的所有O变成X
    //2. 把变成A的那些变成O
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[0].length; j++) {
        if(board[i][j] == 'O') board[i][j] = 'X';
        else if(board[i][j] == 'A') board[i][j] = 'O';
      }
    }
  }

  //DFS flood filling 遇到O就把自己和邻里四周都变成A
  private void dfs(char[][] board, int row, int col) {
    if((row < 0 || row >= board.length) || (col < 0) || (col >= board[0].length)) return;
    if(board[row][col] != 'O') return;

    board[row][col] = 'A';

    //这种暴力的dfs会导致结果特别慢，在极端情况下如果某一个从边沿出发的 DFS 连
    //通了另一个边沿出发的 DFS，会导致一次的搜索路径非常长，于是stackoverflow.
    //既然边沿的格子无论如何都要检查一遍，就把外圈封住，减少每个起点的 search tree depth.
    //见下面的代码：
    //dfs(board, row + 1, col);
    //dfs(board, row - 1, col);
    //dfs(board, row, col + 1);
    //dfs(board, row, col - 1);

    if(row + 2 < board.length && board[row + 1][col] == 'O') dfs(board, row + 1, col);
    if(row - 2 >= 0 && board[row - 1][col] == 'O') dfs(board, row - 1, col);
    if(col + 2 < board[0].length && board[row][col + 1] == 'O') dfs(board, row, col + 1);
    if(col - 2 >= 0 && board[row][col - 1] == 'O') dfs(board, row, col - 1);

  }

  //Test Case
  public static void main(String[] args) {
    SurroundedRegions testClass = new SurroundedRegions();
    char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
    testClass.solve(board);
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[0].length; j++) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
  }
}
