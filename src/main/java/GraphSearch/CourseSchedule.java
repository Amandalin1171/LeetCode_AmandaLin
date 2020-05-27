package GraphSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 207. Course Schedule
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * 1 <= numCourses <= 10^5
 */
public class CourseSchedule {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    //corner case
    if(prerequisites == null || prerequisites.length == 0) return true;

    int[] indegree = new int[numCourses];
    Map<Integer, List<Integer>> neighbors = new HashMap<>(); //k: course, v: children
    Queue<Integer> queue = new LinkedList<>();
    List<Integer> res = new ArrayList<>();

    //build graph
    //initiate map的时候要所有的Integer都指向一个list不然没有出度的那些int就指向null，BFS最后一层的时候就会null pointer exception
    for(int i = 0; i < numCourses; i++) {
      neighbors.put(i, new ArrayList<>());
    }

    for(int[] pre : prerequisites) {
      int parent = pre[1];
      int child = pre[0];
      indegree[child]++;
      neighbors.get(parent).add(child);

    }

    //BFS
    for(int i = 0; i < numCourses; i++) {
      if(indegree[i] == 0) queue.offer(i); //offer those indegree = 0

    }

    while(!queue.isEmpty()) {
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        int course = queue.poll();
        res.add(i);
        for(int neighbor : neighbors.get(course)) { //null pointer exception:
          //HashMap 最开始initiate的时候每一个integer都要指向一个空的list不然到leaf就是完全没有出度的节点的时候就会null pointer exception
          indegree[neighbor]--;
          if(indegree[neighbor] == 0) {
            queue.offer(neighbor);
            //for(int j = 0; j < res.size(); j++) System.out.println(res.get(j));
          }
        }
      }
    }
    return res.size() == numCourses;
  }
}
