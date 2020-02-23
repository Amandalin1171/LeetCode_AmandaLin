package LinkedList.SlowFasterPointer;

/**
 * 题目：
 * 287. Find the Duplicate Number
 *
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 *
 * Example 1:
 *
 * Input: [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: [3,1,3,4,2]
 * Output: 3
 * Note:
 *
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */

/**
 * 笔记：
 * 首先是n + 1个数包含着1到n,所以index跟里面的数值就有一点千丝万缕的关系的感觉！
 * 题中说了假设只有一个重复的，所以就有那味儿了，就有一种只需要相遇一次的感觉！
 * 利用快慢指针，一个慢指针是每次以自己为index找, 一个快指针是以每次自己的index的数值为index下一个数值那么找，
 * 直到这俩数相等，再找一个慢指针开始走，两个慢指针相遇的地方就是重复的数字
 * 记住这个套路吧，啥也不说了，心动的感觉T^T
 */
public class FindTheDuplicateNumber {
  public int findDuplicate(int[] nums) {
    if(nums == null || nums.length <= 1) return -1;
    int walker = nums[0];
    int runner = nums[nums[0]];

    while(walker != runner) {
      walker = nums[walker];
      runner = nums[nums[runner]];
    }

    int walker2 = 0;
    while(walker != walker2) {
      walker = nums[walker];
      walker2 = nums[walker2];
    }
    return walker;
  }

  public static void main(String[] args) {
    FindTheDuplicateNumber testClass = new FindTheDuplicateNumber();
    int[] nums = {3,1,3,4,2};
    int res = testClass.findDuplicate(nums);
    System.out.println(res);
  }
}
