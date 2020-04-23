package CS5800FinalQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem 6
 * You are working in the HR department of a huge corporation.
 * Each employee may have several direct managers and/or several direct subordinates.
 * Of course, his subordinates may also have their own subordinates,
 * and his direct managers may have their own managers.
 * We say employee X is a boss of employee Y if there exists a sequence of employees A, B, …, D,
 * such that X is the manager of A, A is the manager of B, and so on,
 * and D is the manager of Y (of course, if X is a direct manager of employee Y, X
 * will be a boss of employee Y).
 *
 * If X is a boss of Y, then Y can not be a boss of X.
 * According to the new company policy, the salary of an employee with no subordinates is 1.
 * If an employee has any subordinates, then his salary is equal to the sum of the salaries of
 * his direct subordinates.
 *
 * You will be given an array of pairs, where a pair (u,v) means u is direct manager of v.
 * Design an efficient algorithm to compute the total salaries of all the employees.
 */
public class TotalSalariesOfAllEmployees {
  // int n -> numOfEmployees
  public int[] solution(int n, int[][] managers){
    int[] res = new int[n];
    int[] indegree = new int[n];
    Map<Integer, List<Integer>> hm = new HashMap<>();
    for(int i = 0; i < managers.length; i++){
      if(hm.containsKey(managers[i][1])){
        hm.get(managers[i][1]).add(managers[i][0]);
      }else{
        List<Integer> subs = new ArrayList<>();
        subs.add(managers[i][0]);
        hm.put(managers[i][1], subs);
      }
      indegree[managers[i][0]]++;
    }

    for(int i = 0; i < indegree.length; i++){
      if(indegree[i] == 0){
        dfs(hm, indegree[i], res);
      }
    }

    return res;
  }

  public int dfs(Map<Integer, List<Integer>> hm, int head, int[] salary){

    int res = 0 ;
    List<Integer> list = new ArrayList<>();
    if(hm.containsKey(head)){
      list = hm.get(head);
      for(int i = 0; i < list.size(); i++){
        int cur = list.get(i);
        // res = Math.max(res, dfs(hm, cur, informTime));
        res += dfs(hm, cur, salary);
      }
    } else {
      salary[head] = 1;
      return 1;
    }

    salary[head] = res;
    return res;
  }

  //Test Case
  public void runTest() {
    // int[i][0] : employee
    // int[i][1] : manager
    int[][] managers = {{1, 0}, {2, 0}, {3, 0}, {4, 1}, {5, 2}};

    int[] res = solution(6, managers);
    for (int i : res) {
      System.out.println("result = " + i);
    }
  }

  public static void main(String[] args) {
    TotalSalariesOfAllEmployees test = new TotalSalariesOfAllEmployees();
    test.runTest();
  }
}
