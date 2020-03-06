package DFS;

/**
 * 题目：
 * 79. Word Search
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */

/**
 * 笔记：
 * 这类在 2D 矩阵上 DFS 的题都挺送分的，写的时候重在简洁。 和这题非常相似的
 * 还有经典的用来学习union find 的 Number of Islands.
 *
 * 边界处理在 DFS 一开始做，免得后面的多向 DFS 里再重写
 * 如果要 backtrack，就先把自己格子设成其他字符再 DFS，免得死循环
 *
 * 最上面写的条件就是要考虑三类：
 * 1. 退出得到结果的条件；
 * 2. 边界处理，矩阵的越界处理条件，超过边界了可咋整；
 * 3. dfs退到上一层的条件，找不到答案就跳到下一个dfs function的条件啦~~~
 *
 * 快去看看Number of Islands这道折磨了一开始的自我很久的题目吧！！！
 */
public class WordSearch {
  public boolean exist(char[][] board, String word) {
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[0].length; j++) {
        if(dfs(board, word, i, j, 0)) return true;
      }
    }
    return false;
  }

  private boolean dfs(char[][] board, String word, int row, int col, int index) {
    if(index == word.length()) return true; //退出条件（得到正确结果条件）
    else if((row < 0 || row > board.length) || (col < 0) || (col > board[0].length)) return false; //越界处理
    else if(board[row][col] != word.charAt(index)) return false; //dfs退至上一层条件

    board[row][col] = '#'; //每次先把用过的character变成一个别的字符，类似Number of island的沉下去岛屿

    boolean res =
        dfs(board, word, row + 1, col, index + 1)
        || dfs(board, word, row - 1, col, index + 1)
        || dfs(board, word, row, col + 1, index + 1)
        || dfs(board, word, row, col - 1, index + 1);

    board[row][col] = word.charAt(index); //向上退出的道理自然就是把这个变化后的字符复原
    return res;
  }

  //Test Case
  public static void main(String[] args) {
    WordSearch testClass = new WordSearch();
    char[][] board = {{'A','B','C','E'},{'S','F','C','S'}, {'A','D','E','E'}};
    String word1 = "ABCCED";
    String word2 = "SEE";
    String word3 = "ABCB";

    boolean res1 = testClass.exist(board, word1);
    System.out.println(res1);

    boolean res2 = testClass.exist(board, word2);
    System.out.println(res2);

    boolean res3 = testClass.exist(board, word3);
    System.out.println(res3);
  }
}
