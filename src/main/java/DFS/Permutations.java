package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 46. Permutations
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */

/**
 * 笔记：
 * 一开始的思维误区：认为由于given的数字elements不重复，所以可以用一个index去标记每次遍历的位置
 * 但是这样是不对滴小盆友！！！
 * 因为123 你从1开始，OK第二层咱们从2 开始得到123和13，
 * 但是要是从2开始呢，start + 1的话就reach不到1了呀，所以每一次都要从第一个位置开始遍历
 * 唯一的解决方法就是遇到重复的就skip！！！
 */
public class Permutations {
  //方法: 不标记每次遍历开始的地方，而是每次都从头遍历，但是遇到重复的元素就skip
  public List<List<Integer>> permute_2(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    if(nums == null) return res;
    dfsHelper(nums, res, new ArrayList<Integer>());
    return res;
  }

  private void dfsHelper(int[] nums, List<List<Integer>> res, List<Integer> cur) {
    if(cur.size() == nums.length) {
      res.add(new ArrayList<Integer> (cur));
      return;
    }
    for(int i = 0; i < nums.length; i++) {
      if(cur.contains(nums[i])) continue; //遇到重复的元素就跳过！
      cur.add(nums[i]);
      dfsHelper(nums, res, cur);
      cur.remove(cur.size() - 1);
    }
  }

  //Test Case
  //记住这个神奇的打印方法，可以每一行是每一个单独的list,然后整个集合根据列来打印
  public static void main(String[] args) {
    Permutations testClass = new Permutations();
    int[] nums = {1, 2, 3};
    List<List<Integer>> res1 = testClass.permute_2(nums);
    for(int i = 0; i < res1.size(); i++) {
      List<Integer> cur = res1.get(i);
      for(int j = 0; j < cur.size(); j++) {
        System.out.print(cur.get(j));
        System.out.print(" ");
      }
      System.out.println(); //神奇的就在于这一行
    }
    List<List<Integer>> res2 = testClass.permute_2(nums);
  }
}
