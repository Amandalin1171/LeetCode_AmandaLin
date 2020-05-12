package Design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 211. Add and Search Word - Data structure design
 *
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 *
 * Example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 */
public class AddAndSearchWord_DataStructureDesign {
  class WordDictionary {
    public Map<Integer, List<String>> map; //key: length of input word; value: list of word of this length
    public WordDictionary() {
      map = new HashMap<>();
    }

    public void addWord(String word) {
      int key = word.length();
      map.putIfAbsent(key, new ArrayList<>());
      map.get(key).add(word);
    }

    public boolean search(String word) {
      int key = word.length(); //用key命名还是用length, 实际含义 vs 命名的一致性？？？
      if(!map.containsKey(key)) return false;
      List<String> dictionary = map.get(key);
      for(String oldWord : dictionary) {
        if(match(oldWord, word)) return true;
      }
      return false;
    }

    private boolean match(String old, String word) {
      for(int i = 0; i < word.length(); i++) {
        if(word.charAt(i) != old.charAt(i) && word.charAt(i) != '.') return false;
      }
      return true;
    }

//    //Test Case
//    public static void main(String[] args) {
//    这种情况下 test 只能写在外部的class当中啦~
//    }
  }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
}
