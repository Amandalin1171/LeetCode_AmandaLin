package BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * 542. 01 Matrix：
 *
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 * Example 2:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,1,1]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,2,1]]
 *
 *
 * Note:
 *
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 */

/**
 * 笔记：
 * 2020-5-24更新：
 * 理解：
 * BFS关键的就是取出当前层的queue的长度，只遍历这个长度，在遍历过程中，对符合条件的邻居们offer进queue里面
 * 但是由于当前层你只遍历当前的size，新加入的成员只能在下一层for loop中遍历到
 * 所以结算steps一般都是在for loop queue size后进行结算。
 *
 * 这是一道最最最最基础的最最最模板化的BFS
 * 千万要记住的就是out of bound的check一定要写在最前面！！！不然就会出现out of bound exception
 */
public class ZeroOneMatrix {
  public int[][] updateMatrix(int[][] matrix) {
    if(matrix == null || matrix[0].length == 0) return matrix;
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    Queue<int[]> queue = new LinkedList<>();
    for(int i = 0; i < matrix.length; i++) {
      for(int j = 0; j < matrix[0].length; j++) {
        if(matrix[i][j] == 0) {
          queue.offer(new int[]{i, j});
          visited[i][j] = true;
        }
      }
    }

    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int steps = 0;
    while(!queue.isEmpty()) {
      int size = queue.size(); //取出当前层的长度，这样后来offer进来的就只能在下一层结算
      for(int i = 0; i < size; i++) {
        int[] curr = queue.poll();
        int x = curr[0];
        int y = curr[1];
        matrix[x][y] = steps;
        for(int[] dir : directions) {
          int newX = x + dir[0];
          int newY = y + dir[1];
          //一定要记住，out of bound的条件一定要写在前面！！！！
          //下面这一串条件如果visited那个写在前面就会出现out of bound exception！！！
          if(newX < 0 || newX >= matrix.length || newY < 0 || newY >= matrix[0].length || visited[newX][newY]) continue;
          visited[newX][newY] = true;
          queue.offer(new int[] {newX, newY}); //因为之前取得了当前层的size,所以新offer进去的只能下一层进行结算
        }
      }
      steps++; //当前层结算之后进行增加。
    }
    return matrix;
  }

  //Test Case
  public static void main(String[] args) {
    ZeroOneMatrix test = new ZeroOneMatrix();
    int[][] matrix = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
    int[][] res = test.updateMatrix(matrix);
    for(int i = 0; i < res.length; i++) {
      for(int j = 0; j < res[0].length; j++) {
        System.out.print(res[i][j]);
      }
      System.out.println();
    }
  }
}
