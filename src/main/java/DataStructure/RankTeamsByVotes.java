package DataStructure;

/**
 * 题目：
 * 1366. Rank Teams by Votes
 * In a special ranking system, each voter gives a rank from highest to lowest to all teams participated in the competition.
 *
 * The ordering of teams is decided by who received the most position-one votes. If two or more teams tie in the first position, we consider the second position to resolve the conflict, if they tie again, we continue this process until the ties are resolved. If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.
 *
 * Given an array of strings votes which is the votes of all voters in the ranking systems. Sort all teams according to the ranking system described above.
 *
 * Return a string of all teams sorted by the ranking system.
 *
 * Example 1:
 *
 * Input: votes = ["ABC","ACB","ABC","ACB","ACB"]
 * Output: "ACB"
 * Explanation: Team A was ranked first place by 5 voters. No other team was voted as first place so team A is the first team.
 * Team B was ranked second by 2 voters and was ranked third by 3 voters.
 * Team C was ranked second by 3 voters and was ranked third by 2 voters.
 * As most of the voters ranked C second, team C is the second team and team B is the third.
 *
 * Constraints:
 * 1 <= votes.length <= 1000
 * 1 <= votes[i].length <= 26
 * votes[i].length == votes[j].length for 0 <= i, j < votes.length.
 * votes[i][j] is an English upper-case letter.
 * All characters of votes[i] are unique.
 * All the characters that occur in votes[0] also occur in votes[j] where 1 <= j < votes.length.
 */

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 笔记：
 * 这道题用到了两个道具，有三件事儿需要记住：
 * 道具： HashMap， PriorityQueue
 * 用法要记住的点：
 * 1. Hashmap是统计频次的map
 * 2. 往hashmap和priorityqueue里面丢的是自己建立的object，所以一开始自己要写个class
 * 3. PriorityQueue对自己定义的object进行排序的Comparator如何重写
 * 很棒很棒的一道理解Map，PQ以及自己建立class的题目！！！多写写！！！
 */

/**
 * 此处补充Comparator比较器的使用方法：
 * 比较器源代码：
 * public interface Comparator {
 *   int compare(T o1, T o2);
 *   boolean equals(object obj);
 * }
 *
 * 1) 若一个类要实现java.util.Comparator接口：它一定要实现int compare(T o1, T o2) 函数，
 * 而另一个可以不实现boolean equals(Object obj) 函数
 *
 * 2）int compare(T o1, T o2) 是比较o1和o2的大小
 * 因为本身是从小到大排序的所以：
 * 如果返回值为负数意味着o1比o2小（就是o1排在o2的前面），否则返回为零意味着o1等于o2，
 * 返回为正数意味着o1大于o2(就是o1排在o2的后面）
 * 有一种自定义谁大谁小的感觉
 *
 * 也可以理解为：
 * 相当于从前往后拿两个数 a b， a在前 如果comparator返回-1就不换 +1就交换
 *
 * 下面是官方解释，很鸡肋，就根据返回负数（多半-1）的时候，你写的条件里面符合的那个排在前面就好啦！
 * jdk官方默认是升序，是基于：
 *
 * < return -1
 * = return 0
 * > return 1
 *
 * 如果要降序就必须完全相反：
 *
 * < return 1
 * = return 0
 * > return -1
 */
public class RankTeamsByVotes {
  class Team{
    Character teamName;
    int[] rank;
  }
  public String rankTeams(String[] votes) {
    int n = votes[0].length(); // number of teams
    Map<Character,Team> teamMap = new HashMap<>();

    for(int i = 0; i < votes.length; i++) {
      for(int j = 0; j < n; j++) {
        if(!teamMap.containsKey(votes[i].charAt(j))) {
          Team curr = new Team();
          curr.teamName = votes[i].charAt(j);
          curr.rank = new int[n];
          curr.rank[j] = 1;
          teamMap.put(votes[i].charAt(j), curr);
        } else {
          teamMap.get(votes[i].charAt(j)).rank[j]++;
          //此处注意，由于map里面的value是object我们只要拿出来改变里面的东西即可
        }
      }
    }

    PriorityQueue<Team> pq = new PriorityQueue<>(new Comparator<Team> () {
      @Override
      public int compare(Team a, Team b) {
        for(int i = 0; i < n; i++) {
          if(a.rank[i] > b.rank[i]) return -1; // return -1是负数，所以是这种情况下，a排在b的前面
          else if(a.rank[i] > b.rank[i]) return 1;
        }
        return a.teamName- b.teamName; //字母本身的升序，所以a-b为负数，a这个字母就排在b前面
      }
    });

    for(char c : teamMap.keySet()) {
      pq.offer(teamMap.get(c));
    }
    StringBuilder sb = new StringBuilder();
    while(!pq.isEmpty()) {
      sb.append(pq.poll().teamName);
    }
    return sb.toString();
  }

  //Test Class
  public static void main(String[] args) {
    RankTeamsByVotes testClass = new RankTeamsByVotes();
    String[] votes = {"ABC","ACB","ABC","ACB","ACB"};
    String res = testClass.rankTeams(votes);
    System.out.print(res);
  }
}
