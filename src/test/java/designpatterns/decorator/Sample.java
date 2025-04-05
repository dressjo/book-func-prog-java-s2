package designpatterns.decorator;

import java.awt.Color;
import java.util.function.Function;
import java.util.stream.Stream;

class Camera {

  private Function<Color, Color> filter;

  public Camera(Function<Color, Color>... filters) {
    filter = Stream
        .of(filters)
        .reduce(Function.identity(), Function::andThen);
  }


  public Color snap(Color input) {
    return filter.apply(input);
  }
}


public class Sample {

  public static void process(Camera camera) {
    System.out.println(camera.snap(new Color(125,125,125)));
  }

  public static void main(String[] args) {
    process(new Camera());
    process(new Camera(Color::brighter, Color::brighter));
  }

}

class FunctionTes {

}
