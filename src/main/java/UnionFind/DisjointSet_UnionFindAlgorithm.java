package UnionFind;

/**
 * Union Find/ Disjoint Set
 * 笔记：
 * 解决 判断两个点是否处在同一个group, group之间要merge 的问题
 * 最大的问题：只能合并，不能拆分
 * find(x) 找到x的root是谁，find the root/cluster-id of x
 * union(x, y) merge two clusters(cluster 簇）
 *
 * check whether 2 elements are in the same set or not in O(1) constant time!!!
 *
 * without optimization: find: O(n)
 * 2 key optimizations:
 * 1). path compression: make tree flat， happen during find: 趋近于O(1)
 * 在find的过程中触发，比如find(5), 我们把5及5上面的点都指向根节点，就相当于纵向变成一对多的横向，不用traverse
 * 一趟线下来。
 * 2). union by rank: merge low rank tree to high rank one: 趋近于O(1)
 * 把小组合并到大的组中
 * rank可以理解成混乱度或者平均长度
 * 把高度低的树 merge 到高度高的树上，这样可以减少path compression的次数
 *
 * Pseudo Code:
 * class UnionFind:
 *  func UnionFind(n):
 *    parents = [1...n]
      ranks = [0...0] n zeros

    func find(x):
      if x != parents[x]:
        parents[x] = find(parents[i]) 优化1： path compression
      return parents[x]

    func union(x, y): 优化2： union by rank
      rootX, rootY = find(x), find(y)
      if ranks[rootX] > ranks[rootY] : parents[rootY] = rootX
      if ranks[rootX] < ranks[rootY] : parents[rootX] = rootY
      if ranks[rootX] == ranks[rootY]:
        parents[rootY] = rootX
        ranks[rootX]++
 */
public class DisjointSet_UnionFindAlgorithm {
  class UnionFind{
    private int[] parents;
    private int[] ranks;

    public UnionFind(int x) {
      parents = new int[x];
      ranks = new int[x];
      for(int i = 0; i < x; i++) {
        parents[i] = i;
      }
    }

    public int find(int child) {
      if(child != parents[child]) {
        //Optimization 1 : this recursion: path compression
        parents[child] = find(parents[child]);
        return parents[child];
      }
      return child;
    }


    //根据题意，union一般返回boolean或者void, 返回boolean的话，false表示不是后来union的本来就是一组的
    //Optimization 2 : using rank: union by rank: merge low rank tree to high rank one
    public boolean union(int x, int y) {
      int px = find(x);
      int py = find(y);
      if(px == py) return false;
      else if(ranks[px] > ranks[py]) parents[py] = px;
      else if(ranks[px] < ranks[py]) parents[px] = py;
      else {
        parents[px] = py;
        ranks[py]++;
      }
      return true;
    }
  }
}
