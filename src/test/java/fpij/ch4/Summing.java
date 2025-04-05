package fpij.ch4;

import static fpij.ch4.Person.SAMPLE_DATA;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class Summing {

  @Test
  public void groupingLastNameAndSummingNoOfEmailAddresses() {
    var namesAndEmailAddressesCountNoMapping =
        SAMPLE_DATA.stream()
            .collect(Collectors.groupingBy(
                Person::lastName,
                Collectors.summingInt(person -> person.emailAddresses().size())));
    namesAndEmailAddressesCountNoMapping.forEach((name, count) -> System.out.println(name + " -> " + count));

    final Map<String, Integer> namesAndEmailAddressesCountWithMapping = SAMPLE_DATA
        .stream()
        .collect(Collectors
            .groupingBy(Person::lastName, Collectors
                .mapping(Person::emailAddresses, Collectors.summingInt(List::size))));

    namesAndEmailAddressesCountWithMapping.forEach((name, count) -> System.out.println(name + " -> " + count));
  }

}
