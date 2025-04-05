package fpij.ch2;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

public class PickingElements {

  @Test
  public void pickNameBook() {
    final List<String> names = List.of("Nate", "Neal", "Nick", "Nancy");
    String startingLetter = "N";
    final Optional<String> foundName =
        names.stream()
            .filter(name ->name.startsWith(startingLetter))
            .findFirst();
    System.out.println(String.format("A name starting with %s: %s",
        startingLetter, foundName.orElse("No name found")));

  }

  @Test
  public void pickName() {
    final List<String> names = List.of("Nate", "Neal", "Nick", "Nancy");

    Function<String, Predicate<String>> startsWithLetter = letter -> name -> name.startsWith(letter);
    names.stream().
        filter(startsWithLetter.apply("N")).
        findFirst().
        ifPresentOrElse((s) -> System.out.println("Found name: " + s), () -> System.out.println("No name found"));
  }

}
