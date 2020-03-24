package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：
 * 47. Permutations II
 *
 * Given a collection of numbers that might contain duplicates,
 * return all possible unique permutations.
 *
 * Example:
 *
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class Permutations_II {
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    if(nums == null || nums.length == 0) return res;
    Arrays.sort(nums); //排序，为啥要排序呢，去看CombinationSum_II！！！
    boolean[] used = new boolean[nums.length]; //这里需要用到boolean array去hold每个元素是否被使用
    //跟subsets, combinationSum不一样的是，组合，是每一次都要从0开始遍历的
    //所以需要一个boolean array去hold每一次的结果每一个元素是否被用到，
    //那俩题是用一个遍历的指针int start，在外层for loop进入dfs循环中的时候，每次开始遍历的地方是new start = i + 1
    //一定要融会贯通啊！！！
    dfs(nums, res, new ArrayList<Integer>(), used);
    return res;
  }

  private void dfs(int[] nums, List<List<Integer>> res, ArrayList<Integer> curr, boolean[] used) {
    if(curr.size() == nums.length) {
      res.add(new ArrayList<Integer>(curr));
      return;
    }

    for(int i = 0; i < nums.length; i++) {
      //最重要的就是这一步条件：

      // *1*** 需要注意的是，i > 0 这个一定要写在最前面！！！&&链接的多个条件是一个个按顺序判断的
      //如果 i > 0 不写在前的话，后面call到used[i - 1] 会有越界报错！！！

      // *2*** 第二个就是为啥：
      //when a number has the same value with its previous,
      //we can use this number only if his previous is used
      //低端人口的理解：当有重复的数字的时候，我定义他们在结果中的排序，就是index小的必须出现在前面
      //再由于我们已经排好序了，所以相等的元素一定连在一起，这句话就定义了我后面的数字如果跟前面的数字相等，前面的数字必须被用过。
      //其实就是数值相同的元素，我们定义了在答案中出现的顺序永远是fix的，是index小的在前面，这样就不会出现他们数值相同，但是index前后混乱导致不同的答案。
      //eg：就会避免 2 1(index:0) 1(index :1) 和 2 1(index:1) 1(index :0)这两种结果同时出现了
      //因为我们定义index小的0的1必须出现在前面，所以我们只会得到  2 1(index:0) 1(index :1)这一个结果！！！
      if((i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) || used[i]) continue;
      curr.add(nums[i]);
      used[i] = true;
      dfs(nums, res, curr, used);
      used[i] = false;
      curr.remove(curr.size() - 1);
    }
  }

  //Test Case
  public static void main(String[] args) {
    Permutations_II testClass = new Permutations_II();
    int[] nums = {1,2,1};
    List<List<Integer>> res = testClass.permuteUnique(nums);
    for(int i = 0; i < res.size(); i++) {
      List<Integer> curr = res.get(i);
      for(int j = 0; j < curr.size(); j++) {
        System.out.print(curr.get(j));
      }
      System.out.println();
    }
  }
}
