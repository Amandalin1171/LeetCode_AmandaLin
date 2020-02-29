package BinaryTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 题目：
 * 1361. Validate Binary Tree Nodes
 *
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.
 *
 * If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
 *
 * Note that the nodes have no values and that we only use the node numbers in this problem.
 */
public class ValidateBinaryTreeNodes {
  //方法1： BFS 解法
  public boolean validateBinaryTreeNodesBFS(int n, int[] leftChild, int[] rightChild) {
    boolean[] visited = new boolean[n];
    int root = -2;
    for(int i = 0; i < n; i++) {
      if(leftChild[i] != -1) {
        visited[leftChild[i]] = true;
      }
      if(rightChild[i] != -1) {
        visited[rightChild[i]] = true;
      }
    }
    for(int i = 0; i < n; i++) {
      if(visited[i] == false) {
        if(root == -2) root = i; // judge the root is the only one
        else return false;
      }
    }
    if(root == -2) return false; // judge if there id no roots like example 3


    Queue<Integer> queue = new LinkedList<>();
    Map<Integer, Integer> map = new HashMap<>();
    queue.add(root);
    while(!queue.isEmpty()) {
      int node = queue.poll();
      if(leftChild[node] != -1) {
        if(!map.containsKey(leftChild[node])) {
          map.put(leftChild[node], 1);
          queue.add(leftChild[node]);
        } else return false;
      }

      if(rightChild[node] != -1) {
        if(!map.containsKey(rightChild[node])) {
          map.put(rightChild[node], 1);
          queue.add(rightChild[node]);
        } else return false;
      }
    }
    return true;
  }

  //方法2：DFS 解法：
  public boolean validateBinaryTreeNodesDFS(int n, int[] leftChild, int[] rightChild) {
    boolean[] visited = new boolean[n];
    boolean res = dfs(0, n, leftChild, rightChild, visited);
    for(int i = 0; i < n; i++) {
      if(!visited[i]) {
        return false;
      }
    }

    return res;
  }
  private boolean dfs(int val, int n, int[] l, int[] r, boolean[] visited) {
    if(val >= n || val == -1) return true;
    if(visited[val]) return false;

    visited[val] = true;
    return dfs(l[val], n, l, r, visited) && dfs(r[val], n, l, r, visited);
  }

  //方法3：自己想出的神奇方法：

  /**
   * 1. More than 1 in-degree: index show-up more than once in leftChild and rightChild combined together like example 2, index 3 showed up twice.
   * 2. No roots: index from 0 to n - 1 all showed up once in leftChild and rightChild combined together (act as each other's root like example 3)
   * 3. More than one root: number of indexes from 0 to n - 1 which haven't showed up was more than 1 like example 4, you can see 0 and 3 didn't show up in both arrays combined together.
   */
  public boolean validateBinaryTreeNodesNew(int n, int[] leftChild, int[] rightChild) {
    int[] visited = new int[n];
    Arrays.fill(visited, -1);

    int root = -2;
    for(int i = 0; i < n; i++) {

      if(leftChild[i] != -1) {
        visited[leftChild[i]]++;
      }
      if(rightChild[i] != -1) {
        visited[rightChild[i]]++;
      }
    }
    for(int i = 0; i < n; i++) {

      if(visited[i] > 0) return false; // visited twice like example 2
      if(visited[i] == -1) {
        if(root == -2) {
          root = i; // judge the root is the only one or otherwise like example 4
        } else return false;
      }
    }
    if(root == -2) return false; // judge if there are no roots like example 3
    return true;
  }

  //Test case:
  public static void main(String[] args) {
    ValidateBinaryTreeNodes testClass = new ValidateBinaryTreeNodes();
    int n = 4;
    int[] leftChild = {1,-1,3,-1};
    int[] rightChild = {2,-1,-1,-1};
    System.out.println(testClass.validateBinaryTreeNodesBFS(n, leftChild,rightChild));
    System.out.println("***********");
    System.out.println(testClass.validateBinaryTreeNodesDFS(n, leftChild,rightChild));
    System.out.println("***********");
    System.out.println(testClass.validateBinaryTreeNodesNew(n, leftChild,rightChild));
  }
}
