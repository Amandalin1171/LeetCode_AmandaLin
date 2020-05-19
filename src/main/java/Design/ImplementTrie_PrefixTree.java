package Design;

/**
 * 208. Implement Trie (Prefix Tree)
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 */
public class ImplementTrie_PrefixTree {
  class TrieNode {
    TrieNode[] children;
    boolean hasWord;
    boolean endOfWord;

    public TrieNode() {
      children = new TrieNode[26];
      hasWord = false;
      endOfWord = false;
    }
  }
  class Trie {
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
      root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
      TrieNode curr = root;
      for(char c : word.toCharArray()) {
        if(curr.children[c - 'a'] == null) {
          curr.children[c - 'a'] = new TrieNode();
          curr.hasWord = true;
        }
        curr = curr.children[c - 'a'];
      }
      curr.endOfWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
      TrieNode curr = root;
      for(char c : word.toCharArray()) {
        if(curr.children[c - 'a'] == null) {
          return false;
        }
        curr = curr.children[c - 'a'];
      }
       return curr.endOfWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
      TrieNode curr = root;
      for(char c : prefix.toCharArray()) {
        if(curr.children[c - 'a'] == null) {
          return false;
        }
        curr = curr.children[c - 'a'];
      }
      return curr.hasWord;
    }
  }

}
