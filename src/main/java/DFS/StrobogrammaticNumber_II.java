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
 * 因为用到的String.compareTo()太尼玛Tricky了！！！日！！！脑残如我完全没想到@@
 * 我研究了半天如何只能操作String的情况下对这一堆数字进行进位制，结果人家compareTo直接转成CASII对比了好吗！！！
 * 那里也补充了compareTo()的全部使用方法，可以替代一部分位运算的题目解决问题。
 */
public class StrobogrammaticNumber_II {
  public List<String> findStrobogrammatic(int n) {
    List<String> res = new ArrayList<>();
    char[] pair1 = {'0', '1', '8','6', '9'};
    char[] pair2 = {'0', '1', '8', '9', '6'};
    char[] num = new char[n];
  }
}
