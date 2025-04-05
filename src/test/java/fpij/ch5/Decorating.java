package fpij.ch5;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class Decorating {

  @Test
  public void applyColorFilter() {
    final Camera camera = new Camera();
    final Consumer<String> printCaptured = (filterInfo) ->
        System.out.println(String.format("with %s: %s", filterInfo,
            camera.capture(new Color(200, 100, 200))));

    printCaptured.accept("no filter");

    camera.setFilters(Color::brighter, Color::darker);

    printCaptured.accept("brighter filter");
  }

}

class Camera {
  private Function<Color, Color> filter;

  public Camera() {
    setFilters();
  }

  public void setFilters(final Function<Color, Color>... filters) {
    this.filter =
        Stream.of(filters)
            .reduce((filter, next) -> filter.andThen(next))
            .orElseGet(Function::identity);
  }

  public void setFilter(final Function<Color, Color> filter) {
    this.filter = filter;
  }

  public Color capture(final Color inputColor) {
    final Color processedColor = filter.apply(inputColor);
    return processedColor;
  }

}