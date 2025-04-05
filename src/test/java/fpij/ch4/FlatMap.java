package fpij.ch4;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class FlatMap {

  @Test
  public void getEmailAddressList() {
    Person.SAMPLE_DATA.stream()
        .map(Person::emailAddresses)
        .collect(Collectors.toList())
        .forEach(System.out::println);

    Person.SAMPLE_DATA.stream()
        .map(Person::emailAddresses)
        .flatMap(java.util.List::stream)
        .collect(Collectors.toList())
        .forEach(System.out::println);

    final ArrayList<Object> objects = Optional.of(new ArrayList<>()).orElse(null);
    System.out.println(objects);
  }

}
