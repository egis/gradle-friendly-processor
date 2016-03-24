import java.lang.reflect.Modifier;

public class Test
{
  private static final String TEST = "TEST WORKED";

  public static void main(String[] args) throws Exception
  {
    System.out.println(Private.PRIVATE);
    Private.printTest();
    System.out.println(Modifier.toString(Private.class.getDeclaredField("PRIVATE").getModifiers()));
    System.out.println(Modifier.toString(Test.class.getDeclaredField("TEST").getModifiers()));
  }
}

class Private
{
  private static final String PRIVATE = Test.TEST;

  public static void printTest()
  {
    System.out.println(Test.TEST);
  }
}
