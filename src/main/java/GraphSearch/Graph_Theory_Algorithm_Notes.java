package GraphSearch;

public class Graph_Theory_Algorithm_Notes {
/**
 * 欧拉回路存在的充分必要条件：
 * 给定一个无向连通图G=（V，E），其所有顶点的度为偶数   <=======>   此图是无向连通图。
 *
 * 欧拉通路： Hierholzer算法 航线问题
 * https://blog.csdn.net/vocaloid01/article/details/81273901
 *
 * 具体使用实例：332. Reconstruct Itinerary
 * https://leetcode.com/problems/reconstruct-itinerary/solution/
 *
 * 定义：
 * 欧拉回路：每条边恰好只走一次，并能回到出发点的路径
 * 欧拉路径：经过每一条边一次，但是不要求回到起始点
 *
 * 欧拉回路存在性的判定：
 * 无向图： 每个顶点的度数都是偶数，则存在欧拉回路。
 * 有向图： 每个节顶点的入度都等于出度，则存在欧拉回路。
 *
 * 欧拉路径存在性的判定：
 * 有向图： 图连通，当且仅当该图所有顶点数的度数为0，或者一个顶点的度数为1，另一个顶点的度数为-1，其他顶点的度数为0。
 * 无向图：图连通，当且仅当该图所有顶点的度数为偶数，或者除了两个度数为奇数外其余的全是偶数。
 *
 *
 */
}
