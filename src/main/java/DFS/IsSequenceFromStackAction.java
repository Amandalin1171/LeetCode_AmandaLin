package DFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 题目：	给一个empty stack，one array with num from 0 ~ N，
 * 每次只能按顺序push但是可以随时pop到一个sequence里，给一个sequence，check是否是可能的结果
 */
public class IsSequenceFromStackAction {
  public boolean solution(int[] origin, int[] target) {
    Stack<Integer> stack = new Stack<>();
    List<Integer> seq = new ArrayList<>();
    return dfs(origin, target, 0, stack, seq);
  }

  //list: sequence generated from pop() action of stack, index: index of current integer on origin
  private boolean dfs(int[] origin, int[] target, int index, Stack<Integer> stack, List<Integer> seq) {
    boolean res = false;
    if(index > origin.length) return false;

    //don't pop current integer to stack
    stack.push(origin[index]);
    res = dfs(origin, target, index + 1, stack, seq);

    //early return: 如果这条路有一个结果，那么就不用找下面这条路了~
    if(res) {
      return true;
    }
    if(origin[index] == seq.get(index)) {
    //pop current integer to stack
      int curr = stack.pop();
      seq.add(curr);
      res = dfs(origin, target,index + 1, stack, seq);
      //backtrack
      stack.push(curr);
      seq.remove(seq.size());
  }
    stack.pop();
    return res;
  }



  //test case
  public static void main(String[] args) {
    IsSequenceFromStackAction test = new IsSequenceFromStackAction();
    int[] origin = {3,4,5,6,7,5};
    int[] target = {5,4,3};
    System.out.println(test.solution(origin, target));
  }
}
