package fpij.ch8;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class StreamIterate {


  @Test
  void testStreamIterate() {
    Stream.iterate(0, i -> i + 2)
        .limit(10)
        .forEach(System.out::println);
  }

}
