package DFS;

/**
 * 题目：
 * 547. Friend Circles
 *
 * There are N students in a class. Some of them are friends, while some are not.
 * Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in the class.
 * If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
 * And you have to output the total number of friend circles among all the students.
 *
 * Example 1:
 * Input:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 * Example 2:
 * Input:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends,
 * the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle,
 * so return 1.
 * Note:
 * N is in range [1,200].
 * M[i][i] = 1 for all students.
 * If M[i][j] = 1, then M[j][i] = 1.
 */

/**
 * 笔记：
 * 这里有一个很重要的思想，就是跟之前做数独一样的，matrix的格子不一定要代表横纵坐标，就是一个储蓄的盒子
 * 这里面就是N个小朋友，N*N的matrix,我们就可以把第一位坐标当成第一个小朋友，第二位当成另一个小朋友。
 * 所以这题的思想是在外层for loop 0到N，找到没有被记录跟其他人有关系的小朋友。
 * 然后找到一个是数字1的，我们记录了第一位坐标（first person）又记录了第二个坐标（the other person）
 * 然后我们从第二位坐标，另一个小朋友开始进入下一层DFS，这样就可以一个传递一个的找到所有有关系的小朋友
 * 找不到有关系的，关系链断掉了，就返回，count++
 * 这样就可以找到所有的朋友圈啦！！！
 * 有没有很机智！！！
 */
public class FriendCircles {
  public int findCircleNum(int[][] M) {
    int count = 0;
    boolean[] friends = new boolean[M.length];
    for(int firstPerson = 0; firstPerson < M.length; firstPerson++) {
      if(!friends[firstPerson]) {
        dfs(M, friends, firstPerson);
        count++;
      }
    }
    return count;
  }

  private void dfs(int[][] M, boolean[] friends, int person) {
    for(int theOtherPerson = 0; theOtherPerson < M.length; theOtherPerson++) {
      if(M[person][theOtherPerson] == 1 && !friends[theOtherPerson]) {
        friends[theOtherPerson] = true;
        dfs(M, friends, theOtherPerson);
      }
    }
  }

  //Test Case
  public static void main(String[] args) {
    FriendCircles testClass = new FriendCircles();
    int[][] M = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
    int res = testClass.findCircleNum(M);
    System.out.println(res);
  }
}
