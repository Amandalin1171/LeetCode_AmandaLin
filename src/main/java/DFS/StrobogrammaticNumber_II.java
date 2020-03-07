package DFS;

/**
 * 题目：
 * 247. Strobogrammatic Number II
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees
 * (looked at upside down).
 *
 * Find all strobogrammatic numbers that are of length = n.
 *
 * Example:
 *
 * Input:  n = 2
 * Output: ["11","69","88","96"]
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 笔记：
 * 先提示一下：
 * StrobogrammaticNumber_III这道题我放在了DataStructure这个文件夹里，
 * 因为用到的String.compareTo()太尼玛Tricky了！！！日！！！脑残如我完全没想到 T^T
 * 我研究了半天如何只能操作String的情况下对这一堆数字进行进位制，结果人家compareTo直接转成CASII对比了好吗！！！
 * 那里也补充了compareTo()的全部使用方法，可以替代一部分位运算的题目解决问题。
 */
public class StrobogrammaticNumber_II {
  public List<String> findStrobogrammatic(int n) {
    List<String> res = new ArrayList<>();
    if(n <= 0) return res;
    char[] pair1 = {'0', '1', '8', '6', '9'};
    char[] pair2 = {'0', '1', '8', '9', '6'};
    char[] num = new char[n]; //store each Strobogrammatic Number found
    dfs(res, n, 0, pair1, pair2, num);
    return res;
  }

  private void dfs(List<String> res, int n, int index, char[] pair1, char[] pair2, char[] num) {
    int left = index;
    int right = n - index - 1; //fill each number from both ends
    if(left > right) {
      res.add(new String(num)); //这个写法很nice!直接把char[]转换成String!!!
      return;
    }
    else if(left == right) { //in this case(odd), we can only fill 0/1/8
      //这里面把只有一位数的情况包括了，所以可以放0，所以下面的时候第一位是0就不行，直接skip
      for(int i = 0; i < 3; i++) {
        num[left] = pair1[i];
        dfs(res, n, index + 1, pair1, pair2, num); //next left will > right and return
      }
    } else {
      for(int i = 0; i < 5; i++) {
        if(index == 0 && i == 0) continue; //接上面，由于这里的情况肯定不止一位数，所以第一位一定不能是0
        num[left] = pair1[i];
        num[right] = pair2[i];
        dfs(res, n, index + 1, pair1, pair2, num);
      }
    }
  }

  //Test Case
  public static void main(String[] args) {
    StrobogrammaticNumber_II testClass = new StrobogrammaticNumber_II();
    List<String> res = testClass.findStrobogrammatic(2);
    for(int i = 0; i < res.size(); i++) {
      System.out.println(res.get(i));
    }
  }
}
