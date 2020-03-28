package DFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目：1202. Smallest String With Swaps
 *
 * You are given a string s, and an array of pairs of indices in the string pairs
 * where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 *
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 *
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 * Example 1:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 */

/**
 * 笔记：
 * 根据pair可以找到哪些字母是connected的，只要是connected的字母就可以根据字典排序进行排列
 * 因为可以翻转无数次，他们之间相连接，一定可以实现任意排序
 * 所以这道题变成找connected components，只要他们是一伙的，就可以任意顺序排序
 * 所以变成如下几个步骤：
 * 1. dfs pairs找到所有连接的字母，成为一个个connected part
 * 2. 对这些连在一起的part进行排序
 * 3. 根据他们的index把这些排好顺序的部分连接在一起就是最后的结果
 * DFS的方法是根据323. Number of Connected Components in an Undirected Graph的思路做的
 */
public class SmallestStringWithSwaps {
  public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
    Map<Integer, ArrayList<Integer>> map = new HashMap<>();
    boolean[] visited = new boolean[s.length()];
    char[] res = s.toCharArray();
    for(int i = 0; i < s.length(); i++) {
      map.put(i, new ArrayList<>());
    }

    //注意 list of list也可以这么写，跟array差不多
    for(List<Integer> pair : pairs) {
      map.get(pair.get(0)).add(pair.get(1));
      map.get(pair.get(1)).add(pair.get(0));
    }

    for(int i = 0; i < s.length(); i++) {
      if(!visited[i]) {
        List<Integer> index = new ArrayList<>();
        List<Character> letter = new ArrayList<>();
        dfs(map, index, letter, res, visited, i);
        //把连接成一个group里的index从小到大排序
        //把这些index对应的letter排序
        Collections.sort(index);
        Collections.sort(letter);
        for(int j = 0; j < index.size(); j++) {
          //根据字母排序替换原来的string里面那些group一起的index从小到大的位置
          //就得到答案啦！
          res[index.get(j)] = letter.get(j);
        }
      }
    }
    return new String(res);
  }

  private void dfs(Map<Integer, ArrayList<Integer>> map, List<Integer> index, List<Character> letter, char[] res, boolean[] visited, int start) {
    visited[start] = true;
    index.add(start);
    letter.add(res[start]);
    for(int neighbor : map.get(start)) {
      if(!visited[neighbor]) dfs(map, index, letter, res, visited, neighbor);
    }
  }
}
