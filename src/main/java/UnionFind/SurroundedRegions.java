package UnionFind;

/**
 * 130. Surrounded Regions
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
 * Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */

/**
 * 方法1： Union find，不然为啥在这个文件夹
 * 方法2： 利用flood fill
 *        只有边缘的O不能被改，其余的都要改成X。
 *        一坨O，里面有一个在边缘，这一坨就不能被改成X。
 *        如果是这么理解的话就是先traverse边缘，以此flood fill，把边缘和它的邻居们的O变成另一个字母比如A，
 *        然后traverse整个矩阵，把剩下的O，也就是此刻所有的O都改成X，把遇到的奇葩字母A改回O
 */

//方法1： Union Find
public class SurroundedRegions {

  int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //directions

  public void solve(char[][] board) {
    if(board == null || board.length == 0) return;
    int m = board.length;
    int n = board[0].length;
    int N = m * n;
    int border = N; //mark the dummy group: group on border

    UnionFind uf = new UnionFind(N + 1);

    //group 'O' together in different components, and mark the special component on border with dummy boss of "border"
    for(int x = 0; x < m; x++) {
      for(int y = 0; y < n; y++) {
        if(board[x][y] != 'O') continue;
        int curr = xyToIndex(x, y, n);
        if(x == 0 || y == 0 || x == m - 1 || y == n - 1) { //if on the border
          uf.union(curr, border);
          continue;
        }
        for(int[] dir : dirs) {
          int nextX = x + dir[0];
          int nextY = y + dir[1];
          if(validateXY(nextX, nextY, m, n) && board[nextX][nextY] == 'O') {
            int next = xyToIndex(nextX, nextY, n);
            uf.union(next, curr);
          }
        }
      }
    }

    //convert 'O' to 'X'
    for(int x = 0; x < m; x++) {
      for(int y = 0; y < n; y++) {
        int curr = xyToIndex(x, y, n);
        if(board[x][y] == 'O' && uf.find(curr) != uf.find(border)) { //not in the border union
          board[x][y] = 'X';
        }
      }
    }
  }

  //check if coordinates are valid
  private boolean validateXY(int x, int y, int m, int n) { //m: row; n: column
    if(x >= 0 && y >= 0 && x < m && y < n) return true;
    return false;
  }

  //convert coordinates to index
  private int xyToIndex(int x, int y, int n) { //n: column
    return x * n + y;
  }

  //Union Find
  class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
      parent = new int[n];
      rank = new int[n];
      for(int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    //quick find with path compression
    public int find(int x) {
      if(parent[x] != x) {
        parent[x] = find(parent[x]);
        return parent[x];
      }
      return x;
    }

    //quick union by rank
    public void union(int x, int y) {
      int px = find(x);
      int py = find(y);
      if(px != py) {
        if(rank[px] > rank[py]) parent[py] = px;
        else if(rank[px] < rank[py]) parent[px] = py;
        else {
          parent[px] = py;
          rank[py]++;
        }
      }
    }
  }

  //方法2：flood fill
  //只有边缘的O不能被改，其余的都要改成X。
  //一坨O，里面有一个在边缘，这一坨就不能被改成X。
  //如果是这么理解的话就是先traverse边缘，以此flood fill，把边缘和它的邻居们的O变成另一个字母比如A，
  //然后traverse整个矩阵，把剩下的O，也就是此刻所有的O都改成X，把遇到的奇葩字母A改回O
  public void solve2(char[][] board) {
    if(board == null || board.length == 0) return;
    int rows = board.length;
    int cols = board[0].length;

    for(int i = 0; i < rows; i++) {
      if(board[i][0] == 'O') dfs(board, i, 0);
      if(board[i][cols - 1] == 'O') dfs(board, i, cols - 1);
    }

    for(int i = 0; i < cols; i++) {
      if(board[0][i] == 'O') dfs(board, 0, i);
      if(board[rows - 1][i] == 'O') dfs(board, rows - 1, i);
    }

    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < cols; j++) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }


    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < cols; j++) {
        if(board[i][j] == 'O') board[i][j] = 'X';
        else if(board[i][j] == 'A') board[i][j] = 'O';
      }
    }
  }

  private void dfs(char[][] board, int row, int col) {

    if((row < 0 || row >= board.length) || (col < 0 || col >= board[0].length)) return;

    if(board[row][col] != 'O') return;

    board[row][col] = 'A';

    if(row + 2 < board.length && board[row + 1][col] == 'O') dfs(board, row + 1, col);
    if(row - 2 >= 0 && board[row - 1][col] == 'O') dfs(board, row - 1, col);
    if(col + 2 < board[0].length && board[row][col + 1] == 'O') dfs(board, row, col + 1);
    if(col - 2 >= 0 && board[row][col - 1] == 'O') dfs(board, row, col - 1);
  }
}
