package Mathematics;

/**
 * 273. Integer to English Words
 *
 * Convert a non-negative integer to its english words representation.
 * Given input is guaranteed to be less than 231 - 1.
 *
 * Example 1:
 *
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 *
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 *
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Example 4:
 *
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class IntegerToEnglishWords {
  private final String[] belowTen = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
  private final String[] belowTwenty = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
  private final String[] belowHundred = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

  public String numberToWords(int num) {
    if(num == 0) return "Zero";
    String res = new String();
    if(num < 10) res = belowTen[num];
    else if(num < 20) res = belowTwenty[num - 10];
    else if(num < 100) res = belowHundred[num / 10] + " " + numberToWords(num % 10);
    else if(num < 1000) res = numberToWords(num / 100) + " Hundred " + numberToWords(num % 100);
    else if(num < 1000000) res = numberToWords(num / 1000) + " Thousand " + numberToWords(num % 1000);
    else if(num < 1000000000) res = numberToWords(num / 1000000) + " Million " + numberToWords(num % 1000000);
    else res = numberToWords(num / 1000000000) + " Billion " + numberToWords(num % 1000000000);
    return res.trim();
  }

  //Test
  public static void main(String[] args) {
    IntegerToEnglishWords test = new IntegerToEnglishWords();
    System.out.println(test.numberToWords(1234567891));
  }
}
