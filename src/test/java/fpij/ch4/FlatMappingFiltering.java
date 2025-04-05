package fpij.ch4;

import static fpij.ch4.Person.SAMPLE_DATA;
import static java.util.stream.Collectors.filtering;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class FlatMappingFiltering {

  @Test
  public void flatMappingFiltering() {
    final Map<String, List<String>> lastNamesComAddresses = SAMPLE_DATA
        .stream()
        .collect(Collectors
            .groupingBy(Person::lastName, Collectors
                .flatMapping(person -> person.emailAddresses().stream().map(String::toLowerCase),
                    filtering(email -> email.endsWith(".com"), Collectors.toList()))));

    lastNamesComAddresses.forEach((name, emails) -> System.out.println(name + " -> " + emails));
  }

}
