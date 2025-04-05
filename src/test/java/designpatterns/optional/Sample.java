package designpatterns.optional;

import java.util.Optional;

/*
  Code should reveal it's intention

  1. Return a reference if the value will always exist
  2. Return an optional if the value may or may not exist
  3. Do not use optionals for fields
  4. Do not use optionals for method parameters

 */
public class Sample {

  public static Optional<String> getName(long id) {
    if(id == 0) {
      return Optional.empty();
    }
    return Optional.of("Some name");
  }

  /*
   Don't use get - use orElsexxxxxx
  */
  public static void main(String[] args) {
    var result = getName(0);
    result.ifPresentOrElse(s -> System.out.println(s), () -> System.out.println("Fuck you"));

    result = getName(1);
    result.ifPresentOrElse(s -> System.out.println(s), () -> System.out.println("Fuck you"));
  }

}
