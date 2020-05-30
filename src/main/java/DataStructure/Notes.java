package DataStructure;

public class Notes {
/**
 * 对data structure 和用法的补充：
 *
 * 1. Stringbuilder().reverse() 倒序进行appendx， 我在用string进行加减法的时候用到了
 *
 * 2. TreeSet() 类似于hashmap -> treemap： key是sorted
 * https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html
 * 去重的同时将里面的elements进行排序
 * 里面类似于6.TreeMap的两个function:
 * 821. Shortest Distance to a Character这道题用这个很好做，但是还要掌握扫两边的方法
 *
 * ceiling(E e)
 * Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
 *
 * floor(E e)
 * Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
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
 *
 * 6. TreeMap用法
 * https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
 * 在 729. My Calendar I 会用到： 很好用
 * 【注意】这俩个method返回值是个KEY 是Integer，wrapping object不是int
 * eg: Integer preStart = calendar.floorKey(start); 是Integer，不是int
 * floorKey(K key)
 * Returns the greatest key less than or equal to the given key, or null if there is no such key.
 *
 * ceilingKey(K key)
 * Returns the least key greater than or equal to the given key, or null if there is no such key.
 *
 * 7. Set 批量加东西：
 *  Set.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u'));
 *
 * 8. Primitive Data Types
 * Type	  Size(bits)	Minimum	   Maximum	             Example
 * byte	     8	      -2^7	     2^7– 1	               byte b = 100;
 * short	  16	      -2^15	     2^15– 1	             short s = 30_000;
 * int	    32	      -2^31	     2^31– 1	             int i = 100_000_000;
 * long	    64	      -2^63	     2^63– 1	             long l = 100_000_000_000_000;
 * float	  32	      -2^(-149)	 (2-2^(-23))·2^127	   float f = 1.456f;
 * double	  64	      -2^(-1074) (2-2^(-52))·2^1023	   double f = 1.456789012345678;
 * char	    16	      0	         2^16– 1	             char c = ‘c';
 * boolean	1	        –	         –	                   boolean b = true;
 *
 * 9. BST
 * 右子树：大于等于！！！
 * 左子树：小于！！！
 * 二叉查找树（英語：Binary Search Tree），也称为二叉搜索树、有序二叉树（ordered binary tree）或排序二元樹（sorted binary tree），是指一棵空树或者具有下列性质的二叉树：
 *
 * 若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
 * 若任意节点的右子树不空，则右子树上所有节点的值均大于或等于它的根节点的值；
 * 任意节点的左、右子树也分别为二叉查找树；
 */
}
