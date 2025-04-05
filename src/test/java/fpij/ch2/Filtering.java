package fpij.ch2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

public class Filtering {

  @Test
  public void map() {
    Arrays.asList("a", "b", "c").forEach(System.out::println);

    final List<String> friends = List.of("bob", "josh", "megan");
    friends.stream()
        .map(String::valueOf)
        .map(String::length)
        .forEach(count -> System.out.print(count + " "));
    System.out.println("");
  }

  @Test
  public void filter() {
    final List<String> friends =
        Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
    final List<String> editors =
        Arrays.asList("Brian", "Jackie", "John", "Mike");
    final List<String> comrades =
        Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");


    final long countFriendsStartN =
        friends.stream()
            .filter(name -> name.startsWith("N"))
            .count();
    System.out.println(filterAllWithN(friends));


    final long countEditorsStartN =
        editors.stream()
            .filter(name -> name.startsWith("N"))
            .count();
    System.out.println(filterAllWithN(editors));

    final long countComradesStartN =
        comrades.stream()
            .filter(name -> name.startsWith("N"))
            .count();
    System.out.println(filterAllWithN(comrades));


    final long countComradesStartNPredicate =
        comrades.stream()
            .filter(checkIfStartsWith("N"))
            .count();
    System.out.println(countComradesStartNPredicate);

    final Function<String, Predicate<String>> startsWithLetter = letter -> name -> name.startsWith(letter);
    final long countComradesStartNPredicate2 =
        comrades.stream()
            .filter(startsWithLetter.apply("N"))
            .count();
    System.out.println(countComradesStartNPredicate2);

  }

  public static Predicate<String> checkIfStartsWith(final String letter) {
    return name -> name.startsWith(letter);
  }

  private static long filterAllWithN(List<String> list)  {
    return list.stream()
        .filter(name -> name.startsWith("N"))
        .count();
  }

}
