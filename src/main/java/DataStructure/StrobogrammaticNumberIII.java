package DataStructure;

/**
 * 题目：
 *
 * 248. Strobogrammatic Number III
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 *
 * Example:
 *
 * Input: low = "50", high = "100"
 * Output: 3
 * Explanation: 69, 88, and 96 are three strobogrammatic numbers.
 *
 * Note:
 * Because the range might be a large number, the low and high numbers are represented as string.
 */

/**
 * 笔记：
 * 1. Long的长度无法cover String的长度，网上说可以用什么bigInteger，但是面试时候要是给这种限制，
 * 明摆着就是让你只能操作String，就乖乖想String上如何操作吧！！！
 * A long has 64 bits and has a range of -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807.
 * String的位数范围是 2^31 - 1 也就是说大概可以有2 billion位字符char
 * 所以这道题是不可以用parse成long去进行数学上数字大小的判断的！！！
 *
 * 2. Java String compareTo() 记住这个神器！！！
 * It returns positive number, negative number or 0.
 * It compares strings on the basis of Unicode value of each character in the strings.
 * If first string is lexicographically greater than second string,
 * it returns positive number (difference of character value).
 *
 * 2.1. compareTo()用到数字String：
 *
 * 来源：https://stackoverflow.com/questions/900745/java-compareto-for-string-and-integer-arguments
 *
 * Integer.compareTo sorts numbers numerically.
 *
 * String.compareTo sorts strings lexicographically字典的; that is, in alphabetical order.
 *
 * Consider the strings "10" and "2".
 * Lexicographical ordering would look at the first characters of each, '1' and '2' respectively.
 * The character '1' comes before '2' in the character set that Java uses,
 * so it sorts "10" before "2", in the same way that "bare" is
 * sorted before "hare" because 'b' comes before 'h'
 *
 * 3. 自己写Comparator函数呗！
 * 记住，是Comparator, 很早的时候跟compareTo()搞混过，再理解一下：
 * Comparator not Comparable because Comparator takes two objects
 * whereas Comparable compares the current object to one passed in
 * and you can't change the compareTo() method on String or Integer so.
 */
public class StrobogrammaticNumberIII {

}
