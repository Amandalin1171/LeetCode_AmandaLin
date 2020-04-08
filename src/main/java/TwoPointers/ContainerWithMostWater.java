package TwoPointers;

/**
 * 11. Container With Most Water
 * Given n non-negative integers a1, a2, ..., an ,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai)
 * and (i, 0). Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 * Example:
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */

/**
 * 笔记：永远都向内移动较短的那根挡板，因为向内移动较高挡板一定会比移动之前装的水少
 */
public class ContainerWithMostWater {
  public int computeArea(int left, int right, int[] height) {
    return (right - left) * Math.min(height[left], height[right]);
  }
  public int maxArea(int[] height) {
    int left = 0, right = height.length - 1;
    int area = 0;
    while(left <= right) {
      area = Math.max(area, computeArea(left, right, height));
      if(height[left] < height[right]) {
        left++;
      }
      else {
        right--;
      }
    }
    return area;
  }

  //第二种写法，没有helper function,但是看起来很清晰
  public int maxArea2(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int area = 0;
    while(left < right) {
      //永远都向内移动较短的那根挡板，因为向内移动较高挡板一定会比移动之前装的水少
      if(height[left] < height[right]) {
        area = Math.max(area, height[left] * (right - left));
        left++;
      } else {
        area = Math.max(area, height[right] * (right - left));
        right--;
      }
    }
    return area;
  }

  //Test Case
  public static void main(String[] args) {
    ContainerWithMostWater test = new ContainerWithMostWater();
    int[] height = {1,8,6,2,5,4,8,3,7};
    System.out.println(test.maxArea(height));
  }
}
