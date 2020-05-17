package UnionFind;

/**
 * 947. Most Stones Removed with Same Row or Column
 * On a 2D plane, we place stones at some integer coordinate points.
 * Each coordinate point may have at most one stone.
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 * What is the largest possible number of moves we can make?
 * Example 1:
 *
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Example 2:
 *
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Example 3:
 *
 * Input: stones = [[0,0]]
 * Output: 0
 *
 * Note:
 * 1 <= stones.length <= 1000
 * 0 <= stones[i][j] < 10000
 */

/**
 * 笔记：
 * 每一伙如果有N个人，那么最后只能剩下一个人，所以最大movement的数量就是N-1
 * 所以要用到rank[]
 * 注意：
 * 1. initiate时候一开始rank[i]都是1
 * 2. quick union时候，每一步骤都要rank++，见下面注释
 */
public class MostStonesRemovedWithSameRowOrColumn {
  public int removeStones(int[][] stones) {
    int n = stones.length;
    UnionFind uf = new UnionFind(n);

    //union all groups
    for(int i = 0; i < n; i++) {
      for(int j = 1; j < n; j++) {
        if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
          uf.union(i, j);
        }
      }
    }

    //fill result
    int res = 0;
    for(int i = 0; i < uf.rank.length; i++) {
      res += uf.rank[i] - 1;
    }
    return res;
  }

  //union find
  class UnionFind{
    public int[] parent;
    public int[] rank;

    public UnionFind(int N) {
      parent = new int[N];
      rank = new int[N];
      for(int i = 0; i < N; i++) {
        parent[i] = i;
      }
      //为了使用rank，需要一开始initiate的时候将每一个rank标记为1而不是default的0，表示自己一伙的时候团队成员数量为1
      for(int i = 0; i < N; i++) {
        rank[i] = 1;
      }
    }

    //quick find with path compression
    public int find(int x) {
      if(parent[x] != x) {
        parent[x] = find(parent[x]);
        return parent[x];
      }
      return x;
    }

    //quick union by rank
    public void union(int x, int y) {
      int px = find(x);
      int py = find(y);
      //为了使用rank，要每一次都要将rank进行++，而不是模板里面那种写法就是只在rank相等的时候加一边
      //这一步很重要
      if(px != py) {
        if(rank[px] > rank[py]) {
          parent[py] = px;
          rank[px]++;
        }
        else if(rank[px] < rank[py]) {
          parent[px] = py;
          rank[py]++;
        }
        else {
          parent[px] = py;
          rank[py]++;
        }
      }
    }
  }
}
