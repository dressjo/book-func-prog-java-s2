package fpij.ch4;

import static fpij.ch4.Person.SAMPLE_DATA;
import static java.util.stream.Collectors.partitioningBy;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.Test;

public class Partitioning {

  @Test
  public void partitioningByEmailAddresses() {
    final long count = SAMPLE_DATA.stream()
        .collect(partitioningBy(p -> p.emailAddresses().size() <= 1))
        .entrySet()
        .stream()
        .filter(Entry::getKey)
        .flatMap(e -> e.getValue().stream())
        .map(Person::fullName)
        .count();

    System.out.println("Number of persons having zero or one email address: " + count);

    Map<Boolean, List<Person>> thoseWithAndWithoutMultipleEmails =
        SAMPLE_DATA.stream()
            .collect(partitioningBy(person -> person.emailAddresses().size() > 1));

    System.out.println("Number of people with at most one email address: " +
        thoseWithAndWithoutMultipleEmails.get(false).size());
    System.out.println("Number of people with multiple email addresses: " +
        thoseWithAndWithoutMultipleEmails.get(true).size());

  }

  @Test
  public void partitionWithOrWithoutEmailAddresses() {
    Map<Boolean, List<Person>> thoseWithAndWithoutMultipleEmails =
        SAMPLE_DATA.stream()
            .collect(partitioningBy(person -> person.emailAddresses().size() > 1));

    System.out.println("Number of people with at most one email address: " +
        thoseWithAndWithoutMultipleEmails.get(false).size());

    System.out.println("Number of people with multiple email addresses: " +
        thoseWithAndWithoutMultipleEmails.get(true).size());
  }

}
