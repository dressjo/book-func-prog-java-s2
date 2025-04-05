package fpij.ch4;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class AverageNumberOfEmailAddresses {

  @Test
  public void averageNumberOfEmailAddresses() {

    final double emailAddresses = Person.SAMPLE_DATA
        .stream()
        .reduce(0, (acc, p) -> acc + p.emailAddresses().size(),
            (integer, integer2) -> integer + integer2)
        .intValue();

    System.out.println(emailAddresses/Person.SAMPLE_DATA.size());

    double averageAddresses = Person.SAMPLE_DATA
        .stream()
        .map(Person::emailAddresses)
        .mapToDouble(List::size)
        .sum()/Person.SAMPLE_DATA.size();

    System.out.println(averageAddresses);

    averageAddresses = Person.SAMPLE_DATA
        .stream()
        .map(Person::emailAddresses)
        .mapToDouble(List::size)
        .average()
        .orElse(0);

    System.out.println(averageAddresses);

    averageAddresses = Person.SAMPLE_DATA
        .stream()
        .mapToDouble(p -> p.emailAddresses().size())
        .average()
        .orElse(0);

    System.out.println(averageAddresses);

    averageAddresses = Person.SAMPLE_DATA
        .stream()
        .collect(Collectors.averagingDouble(p -> p.emailAddresses().size()));

    System.out.println(averageAddresses);

  }

  @Test
  public void summarizingStatsEmailAddresses() {
    DoubleSummaryStatistics doubleSummaryStatistics = Person.SAMPLE_DATA
        .stream()
        .map(p -> p.emailAddresses().size())
            .collect(Collectors.summarizingDouble(Integer::doubleValue));

    System.out.println(doubleSummaryStatistics.getAverage());
    System.out.println(doubleSummaryStatistics.getCount());
    System.out.println(doubleSummaryStatistics.getSum());
    System.out.println(doubleSummaryStatistics.getMax());
    System.out.println(doubleSummaryStatistics.getMin());

    doubleSummaryStatistics = Person.SAMPLE_DATA
        .stream()
        .map(Person::emailAddresses)
        .map(List::size)
        .collect(Collectors.summarizingDouble(Integer::doubleValue));

    System.out.println(doubleSummaryStatistics.getAverage());
    System.out.println(doubleSummaryStatistics.getCount());
    System.out.println(doubleSummaryStatistics.getSum());
    System.out.println(doubleSummaryStatistics.getMax());
    System.out.println(doubleSummaryStatistics.getMin());

    doubleSummaryStatistics = Person.SAMPLE_DATA
        .stream()
        .mapToDouble(p -> p.emailAddresses().size())
        .summaryStatistics();

    System.out.println(doubleSummaryStatistics.getAverage());
    System.out.println(doubleSummaryStatistics.getCount());
    System.out.println(doubleSummaryStatistics.getSum());
    System.out.println(doubleSummaryStatistics.getMax());
    System.out.println(doubleSummaryStatistics.getMin());
  }

}
