package fpij.ch7;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class LazyStreams {

  List<String> names = List.of("Brad", "Kate", "Kim", "Jack", "Joe",
      "Mike", "Susan", "George", "Robert", "Julia", "Parker", "Benson");

  @Test
  public void firstNameWith3LettersLazy() {
    final String firstNameWith3Letters =
        names.stream()
            .filter(name -> length(name) == 1)
            .map(name -> toUpper(name))
            .findFirst()
            .orElse("");
    System.out.println(firstNameWith3Letters);
  }

  Function<String, String> toSomething  = (s) -> {
    record Plump(String s) {};
    return s + new Plump(s).s();
  };

  @Test
  public void firstNameWith3LettersLazySplitSteps() {
    Stream<String> namesWith3Letters =
        names.stream()
            .filter(name -> length(name) == 3)
            .map(name -> toUpper(name));

    System.out.println("Stream created, filtered, mapped...");
    System.out.println("Ready to call findFirst...");

    final String firstNameWith3Letters =
        namesWith3Letters.findFirst()
            .orElse("");
  }

  private static int length(final String name) {
    System.out.println("getting length for " + name);
    return name.length();
  }

  private static String toUpper(final String name) {
    System.out.println("converting to uppercase: " + name);
    return name.toUpperCase();
  }

}
