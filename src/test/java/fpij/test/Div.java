package fpij.test;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import org.junit.jupiter.api.Test;

public class Div {


  @Test
  public void test() {
    final BinaryOperator<String> stringBinaryOperator = BinaryOperator.maxBy(
        Comparator.comparingInt(String::length));

    System.out.println(stringBinaryOperator.apply("hello", "worldaadfsdfasf"));

  }

}
