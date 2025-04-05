package fpij.ch2;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import org.junit.jupiter.api.Test;

public class Reducing {

  @Test
  public void sumLenghtOfNames() {
    final List<String> friends = List.of("Brian", "Nate", "Neal", "Raju", "Sara", "Scott", "Johannes");
    System.out.println("Total number of characters in all names: " +
        friends.stream()
            .mapToInt(name -> name.length())
            .sum());
  }

  @Test
  public void pickLongest() {
    final List<String> friends = List.of("Brian", "Nate", "Neal", "Raju", "Sara", "Scott", "Johannes");
    Optional<String> aLongName =
        friends.stream()
            .reduce((name1, name2) ->
                name1.length() >= name2.length() ? name1 : name2);
    aLongName.ifPresent(name ->
        System.out.println(String.format("A longest name: %s", name)));

    final String reduce = friends.stream()
        .reduce("Luciferanius", BinaryOperator.maxBy(Comparator.comparingInt(String::length)));
    System.out.println(String.format("A longest name: %s", reduce));
  }

  @Test
  public void reduceToLongest() {
    final List<String> friends = List.of("Brian", "Nate", "Neal", "Raju", "Sara", "Scott", "Johannes");
    System.out.println("Name with most characters: " +
        friends.stream()
            .reduce((name1, name2) -> name1.length() >= name2.length() ? name1 : name2)
            .orElse(""));

    System.out.println("Name with most characters: " +
        friends.stream()
            .mapToInt(String::length)
            .reduce((name1, name2) -> name1 + name2)
            .orElse(0));


  }

  @Test
  public void reduceBook() {
    final List<String> friends = List.of("Brian", "Nate", "Neal", "Raju", "Sara", "Scott", "Johannes");
    final Optional<String> aLongName =
        friends.stream()
            .reduce((name1, name2) ->
                name1.length() >= name2.length() ? name1 : name2);
    aLongName.ifPresent(name ->
        System.out.println(String.format("A longest name: %s", name)));

    friends.stream()
        .max(java.util.Comparator.comparing(String::length))
        .ifPresent(name ->
            System.out.println(String.format("A longest name: %s", name)));

    System.out.println(String.join(";;", friends));
  }



}
