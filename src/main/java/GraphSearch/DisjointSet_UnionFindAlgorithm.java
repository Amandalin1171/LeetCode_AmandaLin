package GraphSearch;

/**
 * Union Find/ Disjoint Set
 * 笔记：
 * find(x) 找到x的root是谁，find the root/cluster-id of x
 * union(x, y) merge two clusters(cluster 簇）
 *
 * check whether 2 elements are in the same set or not in O(1) constant time!!!
 *
 * without optimization: find: O(n)
 * 2 key optimizations:
 * 1). path compression: make tree flat， happen during find
 * 在find的过程中触发，比如find(5), 我们把5及5上面的点都指向根节点，就相当于纵向变成一对多的横向，不用traverse
 * 一趟线下来。
 * 2). union by rank: merge low rank tree to high rank one
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
        parents[x] = find(parents[i]) 这一步就是path compression
      return parents[x]

    func union(x, y):
      rootX, rootY = find(x), find(y)
      if ranks[rootX] > ranks[rootY] : parents[rootY] = rootX
      if ranks[rootX] < ranks[rootY] : parents[rootX] = rootY
      if ranks[rootX] == ranks[rootY]:
        parents[rootY] = rootX
        ranks[rootX]++
 */
public class DisjointSet_UnionFindAlgorithm {

}
