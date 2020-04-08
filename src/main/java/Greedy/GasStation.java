package Greedy;

public class GasStation {
  public int canCompleteCircuit(int[] gas, int[] cost) {
    //find if we have a solution
    int total = 0;
    int n = gas.length;
    for(int i = 0; i < n; i++) {
      total += gas[i] - cost[i];
    }
    if(total < 0) return -1;

    //if we do have a solution, find where to start
    int start = 0;
    int tank = 0;
    for(int i = 0; i < n; i++) {
      tank += gas[i] - cost[i];
      if(tank < 0) {
        start = i + 1;
        tank = 0;
      }
    }
    return start;
  }

  //Test Case
  public static void main(String[] args) {
    GasStation test = new GasStation();
    int[] gas = {1,2,3,4,5};
    int[] cost = {3,4,5,1,2};
    System.out.println(test.canCompleteCircuit(gas, cost));
  }
}
