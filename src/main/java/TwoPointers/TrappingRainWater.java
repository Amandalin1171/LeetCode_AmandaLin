package TwoPointers;

/**
 * 题目：
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */

/**
 * 笔记：
 * 思路是这样：
 * 两边挡板，哪个短从哪个开始往里倒水
 * 开始倒水的逻辑是看此刻的挡板跟往里面走一格的高度比：如果挡板高，就可以倒挡板-里面一格高度的水量
 * 如果挡板低，就把挡板更新成往里一格，就是挡板往里挪一格。
 */
public class TrappingRainWater {
  public int trap(int[] height) {
    int res = 0;
    if(height == null || height.length == 0) return res;
    int left = 0;
    int right = height.length - 1;
    int leftBar = height[left];
    int rightBar = height[right];

    while(left < right - 1) {
      if(leftBar < rightBar) {
        left++;
        if(leftBar > height[left]) {
          res += leftBar - height[left];
        } else {
          leftBar = height[left];
        }
      } else {
        right--;
        if(rightBar > height[right]) {
          res += rightBar - height[right];
        } else {
          rightBar = height[right];
        }
      }
    }
    return res;
  }

  //Test Case
  public static void main(String[] args) {
    TrappingRainWater test = new TrappingRainWater();
    int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
    int res = test.trap(height);
    System.out.println(res);
  }
}
