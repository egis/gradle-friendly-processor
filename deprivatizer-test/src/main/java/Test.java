
public class Test
{
  public static void main(String[] args) throws Exception
  {
    Alice.test();
    Bob.test();
  }
}

class Alice
{
  private static final String ALICE = "Alice.ALICE";

  public static void test() throws Exception
  {
    System.out.println(Bob.BOB+" isn't private");
    System.out.println(Bob.class.getDeclaredField("BOB"));
  }
}

class Bob
{
  private static final String BOB = "Bob.BOB";

  public static void test() throws Exception
  {
    System.out.println(Alice.ALICE+" isn't private");
    System.out.println(Alice.class.getDeclaredField("ALICE"));
  }
}
