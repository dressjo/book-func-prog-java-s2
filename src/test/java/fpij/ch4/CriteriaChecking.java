package fpij.ch4;

import static fpij.ch4.Person.SAMPLE_DATA;

import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class CriteriaChecking {

  @Test
  public void anyMatchEmailAddresses() {
    System.out.println(
        "Any persons having at least one email address: " + SAMPLE_DATA.stream()
            .anyMatch(p -> p.emailAddresses().size() > 0));

    System.out.println("Any persons having at least ten email addresses: " +
        SAMPLE_DATA
            .stream()
            .anyMatch(p -> p.emailAddresses().size() > 10));

  }

  @Test
  public void allMatchEmailAddresses() {
    System.out.println("All persons have at least one email address: " +
        SAMPLE_DATA.stream()
            .allMatch(p -> p.emailAddresses().size() > 0));

    System.out.println("All persons have zero or more email addresses: " +  SAMPLE_DATA.stream()
        .allMatch(p -> p.emailAddresses().size() >= 0));
  }


  @Test
  public void groupingByFirstNameAndCountOccurrences()  {
   SAMPLE_DATA
       .stream()
       .collect(Collectors.groupingBy(Person::firstName,
           Collectors.collectingAndThen(Collectors.counting(), Long::intValue)))
       .forEach((name, count) -> System.out.println(name + " -> " + count));

  }

  @Test
  public void groupingLastNameAndCountTotalEmailOccurrences() {
    SAMPLE_DATA
        .stream()
        .collect(Collectors.groupingBy(Person::lastName,
            Collectors.summingLong(p -> p.emailAddresses().size())))
        .forEach((name, count) -> System.out.println(name + " -> " + count));


  }
  @Test
  public void groupingLastNameAndFilterEmailOccurrencesByEnding() {
    SAMPLE_DATA
        .stream()
        .collect(Collectors.groupingBy(Person::lastName,
            Collectors.flatMapping(p -> p.emailAddresses().stream(),
                Collectors.filtering(email -> email.endsWith(".com"),
                    Collectors.toList()))))
        .entrySet()
        .stream()
        .filter(e -> e.getValue().size() > 0)
        .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));

    SAMPLE_DATA
        .stream()
        .collect(Collectors.groupingBy(Person::lastName,
            Collectors.flatMapping(p -> p.emailAddresses().stream(),
                Collectors.filtering(email -> email.endsWith(".com"),
                    Collectors.mapping(String::toUpperCase, Collectors.toList())))))
        .entrySet()
        .stream()
        .filter(e -> e.getValue().size() > 0)
        .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
  }


}
