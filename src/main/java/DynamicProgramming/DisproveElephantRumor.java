package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Some people think that the bigger an elephant is, the smarter it is.
 * To disprove this, you want to take the data on a collection of elephants and
 * put as large a subset of this data as possible into a sequence so that the weights are increasing,
 * but the IQ’s are decreasing. You have data for n elephants.
 * For each elephant i you know its weight w_i and IQ q_i.
 * Design an efficient algorithm to find such a subset of this data.
 */
public class DisproveElephantRumor {
  public List<int[]> elephantSubset(int[][] records) {
    //records[i][0] : weight
    //records[i][1] : IQ
    //We sorted weights in descending order, then the outputs are the IQs of certain pairs where
    //the IQs are in ascending order.

    Elephant[] elephants = new Elephant[records.length];
    for(int i = 0; i < records.length; i++){
      elephants[i] = new Elephant(records[i][0], records[i][1]);
    }
    Arrays.sort(elephants, (a, b) -> Integer.compare(b.weight, a.weight));


    return LongestIncreasingSubsequence(elephants, elephants.length);
  }

  class Elephant{
    int weight;
    int iq;
    public Elephant(int w, int i) {
      this.weight = w;
      this.iq = i;
    }
  }

  public List<int[]> LongestIncreasingSubsequence(Elephant[] arr, int n) {

    // Add boundary case, when array n is zero
    // Depend on smart pointers

    int tailIndices[] = new int[n];

    // Initialized with 0
    Arrays.fill(tailIndices, 0);

    int prevIndices[] = new int[n];

    // initialized with -1
    Arrays.fill(prevIndices, -1);

    // it will always point to empty
    // location
    int len = 1;

    for (int i = 1; i < n; i++) {
      if (arr[i].iq < arr[tailIndices[0]].iq)

        // new smallest value
        tailIndices[0] = i;

      else if (arr[i].iq > arr[tailIndices[len - 1]].iq) {

        // arr[i] wants to extend
        // largest subsequence
        prevIndices[i] = tailIndices[len - 1];
        tailIndices[len++] = i;
      }
      else {

        // arr[i] wants to be a potential
        // condidate of future subsequence
        // It will replace ceil value in
        // tailIndices
        int pos = GetCeilIndex(arr,
            tailIndices, -1, len - 1, arr[i].iq);

        prevIndices[i] = tailIndices[pos - 1];
        tailIndices[pos] = i;
      }
    }

    //System.out.println("LIS of given input");

    List<int[]> res = new ArrayList<>();
    for (int i = tailIndices[len - 1]; i >= 0; i = prevIndices[i]) {
      int[] cur = new int[2];
      cur[0] = arr[i].weight;
      cur[1] = arr[i].iq;
      res.add(cur);
      //System.out.print(arr[i].iq + " ");
    }

    //System.out.println();

    return res;
  }

  // Binary search
  static int GetCeilIndex(Elephant[] arr,
      int T[], int l,
      int r, int key)
  {

    while (r - l > 1) {

      int m = l + (r - l) / 2;
      if (arr[T[m]].iq >= key)
        r = m;
      else
        l = m;
    }

    return r;
  }

  //Test Case
  public static void main(String[] args) {
    DisproveElephantRumor test = new DisproveElephantRumor();
    int[][] records = {{3,4},{1,2},{2,3},{0,6}};
    List<int[]> res = test.elephantSubset(records);
    for(int[] i : res) {
      for(int j = 0; j < i.length; j++) {
        System.out.println(i[j]);
      }
    }
  }
}
