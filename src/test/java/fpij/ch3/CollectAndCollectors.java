package fpij.ch3;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class CollectAndCollectors {

  @Test
  public void collectOlderThanAge() {
    var listOfPeople = this.createListOfPeople();
    List<Person> olderThan20 = listOfPeople.stream()
        .filter(olderThanAge(35))
        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    printPeople("People older than 35: ", olderThan20);

    listOfPeople = this.createListOfPeople();
    olderThan20 = listOfPeople.stream()
        .filter(olderThanAge(35))
        .collect(toList());
    printPeople("People older than 35: ", olderThan20);

  }

  @Test
  public void collectSumOfAges_Summarizing() {
    var listOfPeople = this.createListOfPeople();
    long sumOfAges = listOfPeople.stream()
        .collect(Collectors.summarizingInt(Person::getAge))
        .getSum();

    System.out.println("Sum of ages: " + sumOfAges);
  }

  @Test
  public void collectSumOfAges_Mapping() {
    var listOfPeople = this.createListOfPeople();
    long sumOfAges = listOfPeople.stream()
        .collect(mapping(Person::getAge, Collectors.summarizingInt(Integer::intValue)))
        .getSum();

    System.out.println("Sum of ages: " + sumOfAges);
  }

  @Test
  public void collectSumOfAges_MinBy() {
    var listOfPeople = this.createListOfPeople();
    final Person person = listOfPeople.stream()
        .collect(Collectors.minBy(Comparator.comparing(Person::getAge)))
        .get();

    System.out.println("Get youngest: " + person.getAge());
  }

  @Test
  public void collect_GroupingByAge() {
    var listOfPeople = this.createListOfPeople();
    Map<Integer, List<Person>> peopleByAge =
        listOfPeople.stream()
            .collect(Collectors.groupingBy(Person::getAge));
    System.out.println("Grouped by age: " + peopleByAge);
  }

  @Test
  public void collectAndFindErik_CollectionAndThen() {
    final List<Person> listOfPeople = this.createListOfPeople();
    final Person erik = listOfPeople.stream()
        .collect(Collectors.collectingAndThen(Collectors.<Person>toList(), people -> {
          return people.stream().filter(p -> p.getName().equals("Erik")).findFirst().get();
        }));

    System.out.println("Found erik: " + erik);
  }

  @Test
  public void collect_NameWithGroupingByAge() {
    var listOfPeople = this.createListOfPeople();
    Map<Integer, List<String>> peopleByAge =
        listOfPeople.stream()
            .collect(Collectors.groupingBy(Person::getAge, mapping(Person::getName,toList())));

    System.out.println("Names grouped by age: " + peopleByAge);
  }

  /*
    Let’s look at one more combination. Let’s group the names by their first
    character and then get the oldest person in each group.
   */
  @Test
  public void collect_FirstCharacterNameOldestInEachGroup() {
    Comparator<Person> byAge = Comparator.comparing(Person::getAge);
    var listOfPeople = this.createListOfPeople();
    Map<Character, Optional<Person>> peopleByAge =
        listOfPeople.stream()
            .collect(Collectors
                .groupingBy((p) -> p.getName().charAt(0), Collectors.maxBy(byAge))
            );

    System.out.println("Names grouped by age: " + peopleByAge);

    peopleByAge =
        listOfPeople.stream()
            .collect(Collectors
                .groupingBy((p) -> p.getName().charAt(0), Collectors.reducing(BinaryOperator.maxBy(byAge)))
            );

    System.out.println("Names grouped by age: " + peopleByAge);
  }

  private Predicate<Person> olderThanAge(int age) {
    return (person) -> person.getAge() > age;
  }

  private static void printPeople(
      final String message, final List<Person> people) {
    System.out.println(message);
    people.forEach(System.out::println);
  }

  public List<Person> createListOfPeople() {
    return Arrays.asList(
        new Person("Anna", 35),
        new Person("Anna", 25),
        new Person("Agnes", 45),
        new Person("David", 22),
        new Person("Erik", 40),
        new Person("Bertil", 35),
        new Person("Bo", 40));
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
