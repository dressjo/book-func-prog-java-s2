package fpij.ch5;

import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class Decorating2 {

  @Test
  public void decorateChristmasTree() {
    final ChristmasTree christmasTree = new ChristmasTree();
    christmasTree.setDecorators(
        tree -> tree + " with lights",
        tree -> tree + " with ornaments",
        tree -> tree + " with star",
        tree -> tree + " with garland"
    );
    final String decoratedTree = christmasTree.decorate("Christmas tree");

    System.out.println(decoratedTree);
  }

}

class ChristmasTree {

  Function<String, String> decorators;

  public ChristmasTree() {
    setDecorators();
  }

  public String decorate(String christmasTree) {
    return decorators.apply(christmasTree);
  }

  public void setDecorators(Function<String, String>... decorators) {
    this.decorators = Stream.of(decorators)
        .reduce((decorator, next) -> decorator.andThen(next))
        .orElseGet(Function::identity);
  }

}
