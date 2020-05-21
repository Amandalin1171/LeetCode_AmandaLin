package TwoPointers;

/**
 * 821. Shortest Distance to a Character
 * Given a string S and a character C,
 * return an array of integers representing the shortest distance from the character C in the string.
 *
 * Example 1:
 * Input: S = "loveleetcode", C = 'e'
 * Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 *
 * Note:
 * S string length is in [1, 10000].
 * C is a single character, and guaranteed to be in string S.
 * All letters in S and C are lowercase.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 两种方法：
 * 方法1： 2 pointer GOOD GOOD
 * 注意写2 pointer:
 * 1). for loop 里面套 while loop 是for 每走一个index， 要把while loop里面的逻辑走完才继续下一个index
 * 2). 这道题就不是一个指针走一下，等另一个指针走完了再走。注意for loop和while loop的逻辑
 *
 * 方法2： TreeMap
 * 利用了floor 和 ceiling的逻辑
 * ceiling(E e) 注意返回是wrapper class比如Integer
 * Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
 *
 * floor(E e) 注意返回是wrapper class比如Integer
 * Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
 */
public class ShortestDistanceToACharacter {
  //方法1： 2 pointer
  public int[] shortestToChar(String S, char C) {
    List<Integer> cList = new ArrayList<>();

    //store index of C
    for(int i = 0; i < S.length(); i++) {
      if(S.charAt(i) == C) cList.add(i);
    }

    int[] res = new int[S.length()];
    int j = 0; //pointer walk on cList

    //walk pass S to fill result
    for(int i = 0; i < S.length(); i++) {
      if(j < cList.size() && i == cList.get(j)) {
        res[i] = 0;
        j++;
      } else if(j == 0) { //head
        res[i] = Math.abs(i - cList.get(j));
      } else if(j == cList.size()) { //tail
       res[i] = Math.abs(i - cList.get(j - 1));
      } else { //body
        res[i] = Math.min(Math.abs(i - cList.get(j)), Math.abs(i - cList.get(j - 1)));
      }
    }
    return res;

  }

  //方法2： TreeMap
  public int[] shortestToChar2(String S, char C) {
    int n = S.length();
    TreeSet<Integer> set = new TreeSet<>(); //不能写: Set<Integer> set = new TreeSet<>(); 因为后面调用的floor/ceiling function是TreeMap独有的

    //store index of C
    for(int i = 0; i < n; i++) {
      if(S.charAt(i) == C) set.add(i);
    }

    //compare left and right and fill result
    int[] res = new int[n];
    for(int i = 0; i < n; i++) {
      if(!set.contains(i)) {
        Integer left = set.floor(i);
        Integer right = set.ceiling(i);

        if(left == null) left = Integer.MAX_VALUE;
        if(right == null) right = Integer.MAX_VALUE;

        res[i] = Math.min(Math.abs(left - i), Math.abs(right - i));
      } else {
        res[i] = 0;
      }
    }
    return res;
  }
}
