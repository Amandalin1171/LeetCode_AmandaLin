package CodingExerciseWithGary;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1197. Minimum Knight Moves
 *
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 *
 * A knight has 8 possible moves it can make, as illustrated below.
 * Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 *
 * Return the minimum number of steps needed to move the knight to the square [x, y].
 * It is guaranteed the answer exists.
 *
 * Example 1:
 *
 * Input: x = 2, y = 1
 * Output: 1
 * Explanation: [0, 0] → [2, 1]
 * Example 2:
 *
 * Input: x = 5, y = 5
 * Output: 4
 * Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 */

/**
 * 笔记：
 * 这题用BFS暴力的话时间复杂度是8^n, 会TLE，改进方法是全都转到abs第一象限，然后将起始点向左下角挪到-1-1
 * 更好的办法是DP，类似于爬楼梯的解法。
 *
 * 需要注意的是SET进行int[]去重是扯淡，只能转成String再往SET里面放，下面code中具体有提到。
 */
public class MinimumKnightMoves {
  public int minKnightMoves(int x, int y) {
    int[][] dirs = {{1, 2}, {-1, 2}, {-1, -2}, {1, -2}, {2, 1}, {-2, 1}, {-2, -1}, {2, -1}}; // 8 directions
    Queue<int[]> steps = new LinkedList<>(); // queue to do BFS
    int count = -1; // count steps
    Set<String> visited = new HashSet<>(); //use Set to store visited nodes to skip
    StringBuilder sb = new StringBuilder();
    // int[] a = {1,2};
    // int[] b = {1,2};
    // a == b ?? 不等于
    //这里非常重要， 只要是object都是传地址的，所以长一样的int[]地址不一样，就无法做到去重
    //解决方法：用string来解决，stringbuilder 或者 a + "" + b, a, b是integer也没事
    //"12"  "-12"
    // corner case
    if(x == 0 && y == 0) return count;

    //BFS
    steps.offer(new int[] {0, 0});
    while(!steps.isEmpty()) {
      int size = steps.size();
      count += 1;
      for(int i = 0; i < size; i++) {
        int[] curr = steps.poll();
        sb.append(curr[0]);
        sb.append(curr[1]);
        if(!visited.contains(sb.toString())) {
          visited.add(sb.toString());
          System.out.println(sb.toString());
        } else {
          sb.setLength(0);
          continue;
        }
        sb.setLength(0); // for reuse
        int currX = curr[0];
        int currY = curr[1];
        if(currX == x && currY == y) return count;
        for(int[] dir : dirs) {
          int nextX = currX + dir[0];
          int nextY = currY + dir[1];
          steps.offer(new int[] {nextX, nextY});
        }
      }
    }
    //It is guaranteed the answer exists
    return count;
  }
}
