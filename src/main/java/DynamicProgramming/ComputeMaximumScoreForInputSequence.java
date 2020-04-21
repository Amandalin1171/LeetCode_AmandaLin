package DynamicProgramming;

/**
 * You are playing the following game. At the beginning of the game, you are given a sequence of n digitis (where n\ >1). And you need to divide this sequence into groups of digits, each group may consist of 2, 3, or 4 consecutive digits. For example, if you are given 123456, you can group it like (123)(456), or (12)(3456), etc. For each group you will get a score, where the score is the number of 1’s in its binary representation.
 *
 * In the example with input 123456, if you group the digits (123)(456), then
 * 	For the first group, binary representation of 123 is 1111011, so your score of this group is 6 since there are six 1s.
 * 	For the second group, binary representation of 456 is 111001000, so your score of this group is 4 since there are four 1s.
 *
 * You need to design an efficient algorithm to compute the maximum score you can get for the given input sequence
 */
public class ComputeMaximumScoreForInputSequence {
  public int solution(int[] sequence){
    int len = sequence.length;
    int[] dp = new int[len+1];
    for(int i = 1; i <= len; i++){
      for(int j = 2; j <= 4; j++){
        if(i - j >= 0){
          dp[i] = Math.max(dp[i], dp[i - j] + countBits(sequence, i - j,i));
        }
      }
    }
    return dp[len];
  }
  public int countBits(int[] sequence, int start, int end){
    int num = 0;
    while(start < end){
      num = num * 10 + sequence[start];
      start++;
    }
    int count = 0;
    while (num > 0) {
      count += num & 1;
      num >>= 1;
    }
    return count;
  }

  //Test Case
  public static void main(String[] args) {
    ComputeMaximumScoreForInputSequence test = new ComputeMaximumScoreForInputSequence();
    int[] seq = {1,2,3,4,5,6};
    System.out.println(test.solution(seq));
  }
}
