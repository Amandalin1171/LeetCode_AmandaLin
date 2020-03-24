package DFS;

/**
 * 题目： 241. Different Ways to Add Parentheses
 *
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
 *
 * Example 1:
 *
 * Input: "2-1-1"
 * Output: [0, 2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Example 2:
 *
 * Input: "2*3-4*5"
 * Output: [-34, -14, -10, -10, 10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 笔记：
 * 笛卡尔积
 * https://zh.wikipedia.org/wiki/%E7%AC%9B%E5%8D%A1%E5%84%BF%E7%A7%AF
 * 为啥叫积呢？因为两个集合互相运算，结果的数据量是两个集合数据量的乘积
 * 对进行过计算的部分进行记忆存储，这种dfs function的设计是：
 * 返回条件：
 * 如果我之前计算过这个解，那么我返回这个计算的解，
 * 如果我之前没有计算过，那么我就新生成一个list去接收这个解
 * 所以用一个global map去记录计算过的所有解
 * 所以不需要helper function，本身的function call自己就可以啦
 *
 * String.substring(beginning point) 有这个语法，可以只写开始点，这样就是从某一点（包含）到尾巴
 * 还有就是此处可以学习一下switch的语法：
 * Example:
 * https://www.geeksforgeeks.org/switch-statement-in-java/
 * public class Test {
 *     public static void main(String[] args)
 *     {
 *         int day = 5;
 *         String dayString;
 *
 *         // switch statement with int data type
 *         switch (day) {
 *         case 1:
 *             dayString = "Monday";
 *             break;
 *         case 2:
 *             dayString = "Tuesday";
 *             break;
 *         case 3:
 *             dayString = "Wednesday";
 *             break;
 *         case 4:
 *             dayString = "Thursday";
 *             break;
 *         case 5:
 *             dayString = "Friday";
 *             break;
 *         case 6:
 *             dayString = "Saturday";
 *             break;
 *         case 7:
 *             dayString = "Sunday";
 *             break;
 *         default:
 *             dayString = "Invalid day";
 *             break;
 *         }
 *         System.out.println(dayString);
 *     }
 * }
 */

public class DifferentWaysToAddParentheses {
  Map<String, List<Integer>> map = new HashMap<>();
  public List<Integer> diffWaysToCompute(String input) {
    if(map.containsKey(input)) return map.get(input);
    List<Integer> res = new ArrayList<>();
    for(int i = 0; i < input.length(); i++) {
      char operator = input.charAt(i);
      if(operator == '-' || operator == '+' || operator == '*') {
        String part1 = input.substring(0, i);
        String part2 = input.substring(i + 1); //取从i + 1位置开始的后面一截
        //以运算符为分隔符，分割成两部分，
        List<Integer> res1 = diffWaysToCompute(part1);
        List<Integer> res2 = diffWaysToCompute(part2);
        //然后把两部分进行笛卡尔积的求解：像matrix那样for loop
        for(int p1 : res1) {
          for(int p2 : res2) {
            int result = 0;
            switch(operator) {
              case '-':
                result = p1 - p2;
                break;
              case '+':
                result = p1 + p2;
                break;
              case '*':
                result = p1 * p2;
                break;
            }
            res.add(result);
          }
        }
      }
    }
    //最底层的base case, 如果全部for loop完毕之后，乘积一遍之后发现没有结果，那说明没有运算符
    //所以就只有数字没有运算符，直接加到结果里面去就好
    //这就是细分之后只剩下数字的时候，是top down，实际上是bottom up的最底层的case
    if(res.size() == 0) res.add(Integer.valueOf(input));
    map.put(input, res);
    return res;
  }

  //Test Case
  public static void main(String[] args) {
    DifferentWaysToAddParentheses test = new DifferentWaysToAddParentheses();
    List<Integer> res = new ArrayList<>();
    res = test.diffWaysToCompute("2*3-4*5");
    for(int i = 0; i < res.size(); i++) {
      System.out.println(res.get(i));
    }
  }
}
