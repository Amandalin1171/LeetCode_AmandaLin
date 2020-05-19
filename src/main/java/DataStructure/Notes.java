package DataStructure;

public class Notes {
/**
 * 对data structure 和用法的补充：
 *
 * 1. Stringbuilder().reverse() 倒序进行appendx， 我在用string进行加减法的时候用到了
 *
 * 2. TreeSet() 类似于hashmap -> treemap： key是sorted
 * 去重的同时将里面的elements进行排序
 *
 * 3. Comparator 逻辑：
 *
 * 一句话： -1就是你想要的顺序，不交换； 1：交换
 * a1 < a2 return -1: 从小到大正序
 *
 * 此处补充Comparator比较器的使用方法：
 * 比较器源代码：
 * public interface Comparator {
 *   int compare(T o1, T o2);
 *   boolean equals(object obj);
 * }
 *
 * 4. build TrieNode 用array比hashmap好的原因：
 * https://stackoverflow.com/questions/21419112/memory-efficiency-hashmap-versus-array
 * Using a HashTable/HashMap needs a huge memory overhead.
 * First all your characters and integer need to be wrapped in an object (Integer/Character).
 * And Integer consumes about 3x as much memory as an int.
 * For arrays the difference can be even larger due to the optimizations java performs on arrays
 * (e.g. the java stack works only in multiples of 4 byte,
 * while in an array java allows smaller types such as a char to consume only 2 bytes).
 *
 * 5. Deque用法补充：
 * First Element (Head): addFirst(); removeFirst(); getFirst() which will throw EXCEPTION for illegal operation
 *                       offerFirst(); pollFirst(); peekFirst() which will return special value
 *
 * Last Element (Tail): addLast(); removeLast(); getLast() which will throw EXCEPTION for illegal operation
 *                      offerLast(); pollLast(); peekLast() which will return special value
 *
 * All Known Implementing Classes:
 * ArrayDeque, ConcurrentLinkedDeque, LinkedBlockingDeque, LinkedList
 */
}
