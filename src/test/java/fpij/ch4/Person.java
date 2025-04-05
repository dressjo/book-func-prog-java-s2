package fpij.ch4;

import java.util.List;

public record Person(
    String firstName, String lastName, List<String> emailAddresses) {

  public String fullName() {
    return firstName + " " + lastName;
  }

  public static final List<Person> SAMPLE_DATA = List.of(
      new Person("John", "Doe",
          List.of()),
      new Person("Sara", "Walker",
          List.of("sara@example.com")),
      new Person("Sara", "Peng",
          List.of("sara.peng@example.com", "sara.peng@sdc-automation.se")),
      new Person("Mike", "Baker",
          List.of("mike@example.com", "baker@example.com")),
      new Person("Mike", "Wilson",
          List.of("mike.wilson@gmail.com", "mike.wilson@example.com")),
      new Person("Dev", "Shah",
          List.of("dev@example.com", "shah@example.com")),
      new Person("Sara", "Lee",
          List.of("slee@example.org", "lee@example.com")),
      new Person("Nancy", "Xie",
          List.of("nancy@example.com", "xie@example.com", "nx@example.com")),
      new Person("Nancy", "Peng",
          List.of("nancy.peng@example.com", " nancy.peng@example.com", "nancy.peng@example.com")),
      new Person("Jill", "Smith",
          List.of("jill@example.com")),
      new Person("Jill", "Wilson",
      List.of("jill.wilson@example.com")));
}