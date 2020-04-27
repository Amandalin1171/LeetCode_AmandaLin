package DFS;

/**
 * 当dfs helper function返回void的时候，就不能用传参的方法去记录primitive type的参数，因为immutable。
 * 这个时候如果还是要传入int参数，就要返回int而不是void，具体可以看129. Sum Root to Leaf Numbers这题的第二、三个答案
 */
public class PrimitiveTypeImmutableTest {
  //void dfs function
  public int returnA(int A) {
    dfs(A);
    return A;
  }
  private void dfs(int A) {
    for(int i = 0; i < 2; i++) {
      if(A == 10) return;
      A = A * 10;
      dfs(A);
    }
  }

  //return int dfs function
  public int returnA2(int A) {
    return dfs2(A);
  }
  private int dfs2(int A) {
    for(int i = 0; i < 2; i++) {
      if(A == 10) return A;
      A = A * 10;
      dfs(A);
    }
    return A;
  }

  //Test
  public static void main(String[] args) {
    PrimitiveTypeImmutableTest test = new PrimitiveTypeImmutableTest();
    System.out.println(test.returnA(1));
    System.out.println("***********************");
    System.out.println(test.returnA2(1));
  }
}


