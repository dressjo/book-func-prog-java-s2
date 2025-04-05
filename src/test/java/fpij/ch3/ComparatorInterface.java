package fpij.ch3;

import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class ComparatorInterface {


  @Test
  public void sortByAgeLambda() {
    final List<Person> listOfPeople = createListOfPeople();
    listOfPeople.sort(((i, i2) -> i.getAge() - i2.getAge()));
    listOfPeople
        .forEach(System.out::println);
  }

  @Test
  public void sortByAgeStaticMethod() {
    final List<Person> listOfPeople = createListOfPeople();
    listOfPeople.sort(Comparator.comparingInt(Person::getAge));
    listOfPeople
        .forEach(System.out::println);
  }

  @Test
  public void sortByAgeWithStreamSorted() {
    final List<Person> listOfPeople = createListOfPeople();
    List<Person> sortedAscending = listOfPeople.stream()
        .sorted((p1, p2) -> p1.ageDifference(p2))
        .collect(Collectors.toList());

    printPeople("Sorted in ascending order: ", sortedAscending);

    sortedAscending = listOfPeople.stream()
        .sorted(Person::ageDifference)
        .collect(Collectors.toList());

    printPeople("Sorted in ascending order: ", sortedAscending);

    List<Person> sortedDescending = listOfPeople.stream()
        .sorted((p1, p2) -> p2.ageDifference(p1))
        .collect(Collectors.toList());

    printPeople("Sorted in ascending order: ", sortedDescending);

    Comparator<Person> compareAscending = Person::ageDifference;
    sortedDescending = listOfPeople.stream()
        .sorted(compareAscending.reversed())
        .collect(Collectors.toList());

    printPeople("Sorted in ascending order: ", sortedDescending);
  }

  @Test
  public void sortByName() {
    Comparator<Person> compareAscending = (p1, p2) -> p1.getName().compareTo(p2.getName());
    List<Person> sortedAscending = createListOfPeople().stream()
        .sorted(compareAscending)
        .collect(Collectors.toList());
    printPeople("Sorted in ascending order: ", sortedAscending);

    Comparator<Person> compareDescending = (p1, p2) -> p1.getName().compareTo(p2.getName());
    List<Person> sortedDescending = createListOfPeople().stream()
        .sorted(compareDescending.reversed())
        .collect(Collectors.toList());

    printPeople("Sorted in descending order: ", sortedDescending);
  }

  @Test
  public void SortFindYoungestAndOldest() {
    final List<Person> people = createListOfPeople();
    final Person youngest = people.stream()
        .min(Person::ageDifference)
        .get();
    final Person oldest = people.stream()
        .max(Person::ageDifference)
        .get();
    System.out.println("Youngest: " + youngest);
    System.out.println("Oldest: " + oldest);
  }

  @Test
  public void comparingUsingHigherOrderFunction() {
    final List<Person> people = createListOfPeople();

    final List<Person> sortedAscending = people.stream()
            .sorted(comparing(Person::getName).thenComparing(Person::getAge))
                .collect(Collectors.toList());
    printPeople("Sorted by age and name ascending: ", sortedAscending);

    final List<Person> sortedDescending = people.stream()
        .sorted(comparing(Person::getName).thenComparing(Person::getAge).reversed())
        .collect(Collectors.toList());

    printPeople("Sorted by age and name descending: ", sortedDescending);
  }

  private static void printPeople(
      final String message, final List<Person> people) {
    System.out.println(message);
    people.forEach(System.out::println);
  }

  private List<Person> createListOfPeople() {
    return Arrays.asList(
        new Person("Anna", 35),
        new Person("Anna", 25),
        new Person("David", 22),
        new Person("Erik", 40),
        new Person("Bertil", 35));
  }

  private static class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }

    public int ageDifference(final Person other) {
      return age - other.age;
    }
    public String toString() {
      return String.format("%s - %d", name, age);
    }
  }

}




