package fpij.ch2;

import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class Joining {

  @Test
  public void stringJoining() {
    System.out.println(String.join(", ", "Nate", "Neal", "Nick", "Nancy")
        .formatted("Names: %s"));

  }

  @Test
  public void listJoining() {
    final var friends = java.util.List.of("Nate", "Neal", "Nick", "Nancy");
    System.out.println(
        friends.stream()
            .map(String::toUpperCase)
            .collect(Collectors.joining(", ")));

  }

}
