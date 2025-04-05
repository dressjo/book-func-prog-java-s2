package fpij.ch2;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Skipping {

  private static final List<String> friends = List.of("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

  @Test
  public void skipFirst() {
    friends.stream()
        .skip(4)
        .map(String::toUpperCase)
        .forEach(System.out::println);
  }

  @Test
  public void dropWhile() {
    friends.stream()
        .dropWhile(name -> name.length() > 4)
        .map(String::toUpperCase)
        .forEach(System.out::println);
  }

  @Test
  public void limit() {
    friends.stream()
        .limit(2)
        .map(String::toUpperCase)
        .forEach(System.out::println);
  }

}
