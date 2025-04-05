package fpij.ch4;

import static fpij.ch4.Person.SAMPLE_DATA;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.teeing;

import java.util.Comparator;
import org.junit.jupiter.api.Test;

public class Teeing {

  @Test
  public void maxMinNumberOfEmailAddresses() {
    record MinMax(String least, String most) {}

    final MinMax minMax = SAMPLE_DATA
        .stream()
        .collect(teeing(
            minBy(Comparator.comparingInt(value -> value.emailAddresses().size())),
            maxBy(Comparator.comparingInt(value -> value.emailAddresses().size())),
            (min, max) -> new MinMax(min.get().firstName(), max.get().firstName())));

    System.out.println("Min person: " + minMax.least());
    System.out.println("Max person: " + minMax.most);
  }

}
