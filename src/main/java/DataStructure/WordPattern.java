package DataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * 290. Word Pattern
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter
 * in pattern and a non-empty word in str.
 *
 * Example 1:
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 *
 * Example 2:
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 *
 * Example 3:
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 *
 * Example 4:
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 *
 * Notes:
 * You may assume pattern contains only lowercase letters,
 * and str contains lowercase letters that may be separated by a single space.
 */

/**
 * 2020-03-23笔记补充：
 * 这种题叫做：Bijection mapping 双射查找 emmmm这个名字...
 * 说白了就是两个hashmap互相查，和 Isomorphic(同构) Strings 一样，去看看这个题吧2333
 * 串联复习小公举^^
 *
 * 这题第一次做的时候最懵逼的是为啥用equals()
 * 其实还是没理解题意，因为string list里面不同位置长得一样的String地址肯定不一样呀 MDZZ
 * 所以就是为了看是否存在长得一样的key，而不是地址一样的key，才要用equals()
 *
 * 笔记：
 * 1. 首先，记住，map的key是唯一的，value是不唯一的。
 * 1.1. 放进相同的key不同的value会覆盖上衣次放进去的pair
 * 1.2. 放进不同的key相同的value会让这两对儿pair同时存在的。
 * 在我没有加对map.getValue()进行判断的时候，上面四种test case的结果是：true/true/false/true
 * 第二个错是因为，最后放进去 a : fish这个pair时，会覆盖掉之前放进的 a : dog（对应1.1）
 * 第四个错是因为，a : dog 和 b : dog这两个pair会同时存在！（对应1.2）
 * 所以在！containsKey的时候，就是没有key的时候，要判断是否已经contain了这个value(第四个例子）
 * 用map保存String不会改变reference,讲道理用==是米问题的，但这里必须用equals()是因为：
 * 当你比较第三位的cat跟第一次put进去的a对应的cat的时候，他们在原始的strs里面的位置是不一样的！
 * 所以reference不一样，只是比较内容也就是长相是否一样的话要用equals()
 */

/**
 * 2020-03-23更新双射Mapping代码见方法2
 * 利用到了Map的语法：
 * getOrDefault(Object key, V defaultValue)
 */
public class WordPattern {
  //方法1：自己写的没有什么美感的解法
  public boolean wordPattern(String pattern, String str) {
    char[] patterns = pattern.toCharArray();
    String[] strs = str.trim().split(" ");

    Map<Character, String> wordMap = new HashMap<>();
    if(patterns.length != strs.length) return false;
    for(int i = 0; i < strs.length; i++) {
      //System.out.println(patterns[i]);
      if(!wordMap.containsKey(patterns[i])) {
        if(wordMap.containsValue(strs[i])) return false;
        wordMap.put(patterns[i], strs[i]);
      }
      else if(!wordMap.get(patterns[i]).equals(strs[i])) {
        return false;
      }
    }
    return true;
  }

  //方法2： 俩字： 妙啊~~~
  public boolean wordPattern2(String pattern, String str) {
    char[] c = pattern.toCharArray();
    String[] s = str.trim().split(" ");
    if(c.length != s.length) return false;
    int n = c.length;
    //两个map同时查找看index是否相同
    Map<Character, Integer> cMap = new HashMap<>();
    Map<String, Integer> sMap = new HashMap<>();

    for(int i = 0; i < n; i++) {
      int idx_c = cMap.getOrDefault(c[i], -1);
      int idx_s = sMap.getOrDefault(s[i], -1);

      if(idx_c != idx_s) return false;

      cMap.put(c[i], i);
      sMap.put(s[i], i);
    }
    return true;
  }

  //方法3：这个才是双射mapping法：
  //如果你中没我，我中没你，互相加入在一起；
  //如果有了的话，四种情况是不对劲的
  //两类：1. 不该有的key； 2. 对不上的value
  public boolean wordPattern3(String pattern, String str) {
    Map<String, String> p2s = new HashMap<>();
    Map<String, String> s2p = new HashMap<>();

    String[] ss = str.split(" ");
    if(ss.length != pattern.length()) return false;

    for(int i = 0; i < ss.length; i++) {
      String pat = "" + pattern.charAt(i);
      String word = ss[i];

      if(!p2s.containsKey(pat) && !s2p.containsKey(word)) {
        p2s.put(pat, word);
        s2p.put(word, pat);
      } else {
        if(p2s.containsKey(word)) return false;
        if(s2p.containsKey(pat)) return false;
        if(!p2s.get(pat).equals(word)) return false;
        if(!s2p.get(word).equals(pat)) return false;
      }
    }
    return true;
  }

  //Test Case
  public static void main(String[] args) {
    WordPattern testClass = new WordPattern();
    String a = "abba";
    String a1 = "dog cat cat dog";
    String b = "abba";
    String b1 = "dog cat cat fish";
    String c = "aaaa";
    String c1 = "dog cat cat dog";
    String d = "abba";
    String d1 = "dog dog dog dog";

    System.out.println(testClass.wordPattern(a, a1));
    System.out.println(testClass.wordPattern(b, b1));
    System.out.println(testClass.wordPattern(c, c1));
    System.out.println(testClass.wordPattern(d, d1));
  }
}
