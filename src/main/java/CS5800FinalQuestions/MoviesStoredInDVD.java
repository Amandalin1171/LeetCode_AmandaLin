package CS5800FinalQuestions;

/**
 * Problem 3
 * You want to make your friend a birthday present, a DVD with a collection of your favorite movies.
 * You have way too many movies (say n movies in total).
 * For each movie you know how much space it takes. For example, movie 1 takes 2GB of capacity.
 * There is limited capacity on the DVD you have (m GB of capacity in total),
 * and you want to make use of that space as much as possible.
 * Design an efficient algorithm to select a set of movies you store in the DVD.
 */
public class MoviesStoredInDVD {
  public String DVD(int[] A, int m) {
    int[][] dp = new int[A.length][m + 1];
    for (int j = 1; j < m + 1; j++) {
      if (j == A[0]) {
        dp[0][j] = A[0];
      } else {
        dp[0][j] = 0;
      }
      for (int i = 1; i < A.length; i++) {
        if (j < A[i]) {
          dp[i][j] = dp[i - 1][j];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j - A[i]] + A[i], dp[i - 1][j]);
        }
      }
    }

    //The capacity of V could hold max storage as:
    int maxValue = dp[A.length - 1][m];
    //Find all indices of all movies stored in the DVD
    int j = m;
    String numStr = "";
    for(int i = A.length - 1; i > 0; i--){
      //if dp[i][j] > dp[i-1][j], then means the ith movie has been stored in to the DVD
      if(dp[i][j] > dp[i-1][j]){
        numStr = i + " " + numStr;
        j = j - A[i-1];
      }
      if(j == 0)
        break;
    }
    return numStr;
  }

  //Test Case
  public static void main(String[] args) {
    MoviesStoredInDVD test = new MoviesStoredInDVD();
    int storage = 11;
    int[] items = {2,3,5,7};
    System.out.println(test.DVD(items, storage));
  }
}
