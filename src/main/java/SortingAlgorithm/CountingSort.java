package SortingAlgorithm;

/**
 * Counting sort is a sorting technique based on keys between a specific range.
 * It works by counting the number of objects having distinct key values (kind of hashing).
 * Then doing some arithmetic to calculate the position of each object in the output sequence.
 */

import java.util.Arrays;

/**
 * Time Complexity of Counting Sort Algorithm:
 * Best Case O(n+k); Average Case O(n+k); Worst Case O(n+k),
 * where n is the size of the input array and k means the
 * values of the input array range from 0 to k.
 * 所以很重要的一点是我们需要知道input array的上界在哪里（如果都是Integer的话）
 */
public class CountingSort {
  public void countingSort(int[] input, int k) {
    // create buckets
    int counter[] = new int[k + 1];

    // fill buckets
    for (int i : input) {
      counter[i]++;
    }

    // sort array
    int ndx = 0;
    for (int i = 0; i < counter.length; i++) {
      while (0 < counter[i]) {
        input[ndx++] = i;
        counter[i]--;
      }
    }
  }

  //Test Case
  public static void main(String[] args) {

    CountingSort testClass = new CountingSort();

    System.out.println("Counting sort in Java");
    int[] input = { 60, 40, 30, 20, 10, 40, 30, 60, 60, 20, 40, 30, 40 };
    int k = 60;

    System.out.println("integer array before sorting");
    System.out.println(Arrays.toString(input));

    // sorting array using Counting Sort Algorithm
    testClass.countingSort(input, k);

    System.out.println("integer array after sorting using counting sort algorithm");
        System.out.println(Arrays.toString(input));

  }
}
