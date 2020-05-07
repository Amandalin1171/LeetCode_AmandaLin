package DataStructure.Stack;

/**
 * 42. Trapping Rain Water
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */

/**
 * 笔记：这题基本做法就是2 pointer， hold左右两边两个最高的挡板，木桶原理，一边往中间挪动挡板一边计算水的体积
 * 另一个比较惊艳的做法就是用stack
 * 这也是为什么这题在stack文件夹里，基本思想跟 84. Largest Rectangle in Histogram 很像。
 *
 * stack真的是一个很神奇的data structure，一定要学会灵活运用
 */

//方法1：2 pointer 在2 pointer文件夹里有相同解法
public class TrappingRainWater {
  public int trap_2pointer(int[] height) {
    int l = 0;
    int r = height.length - 1;
    int l_Bar = height[l]; //left max height when moving, like a bar
    int r_Bar = height[r]; //right max height when moving, like a bar
    int waterTrapped = 0; //final result

    while(l < r) {
      if(l_Bar < r_Bar) {
        //move left bar
        l++;
        if(height[l] < l_Bar) {
          waterTrapped += l_Bar - height[l];
        } else {
          l_Bar = height[l];
        }
      } else {
        //move right bar
        r--;
        if(height[r] < r_Bar) {
          waterTrapped += r_Bar - height[r];
        } else {
          r_Bar = height[r];
        }
      }
    }
    return waterTrapped;
  }

  //方法2： stack
  public int trap_stack(int[] height) {

  }
}
