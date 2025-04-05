package fpij.ch8;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class StreamIterate {


  @Test
  void testStreamIterate() {
    Stream.iterate(0, i -> i + 1)
        .limit(3)
            .findFirst().stream().forEach(System.out::println);
  }

}
