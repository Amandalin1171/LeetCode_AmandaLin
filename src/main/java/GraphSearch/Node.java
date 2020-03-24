package GraphSearch;

import java.util.ArrayList;
import java.util.List;

public class Node {
  public int val;
  public List<Node> neighbors;
  public Node(int val, List<Node> neighbors) {
    val = val;
    neighbors = new ArrayList<>();
  }

  public Node(int val) {
    val = val;
  }
}
