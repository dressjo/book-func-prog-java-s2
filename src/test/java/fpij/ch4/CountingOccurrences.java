package fpij.ch4;

import static fpij.ch4.Person.SAMPLE_DATA;

import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class CountingOccurrences {

  @Test
  public void groupAndCounting() {
    final Map<String, Integer> countFirstName = SAMPLE_DATA
        .stream()
        .collect(Collectors.groupingBy(Person::firstName, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));

    countFirstName.forEach((name, count) -> System.out.println(name + " -> " + count));
  }

}
