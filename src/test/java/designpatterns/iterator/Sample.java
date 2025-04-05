package designpatterns.iterator;

import java.util.List;

public class Sample {

  public static void main(String[] args) {
    var names = List.of("Tom", "Jerry");

    for(int i = 0; i < names.size(); i++) {
      System.out.println(names.get(i));
      if(names.get(i).equals("Tom")) {
        break;
      }
    }

    for(String name : names) {
      System.out.println(name);
      if(name.equals("Tom")) {
        break;
      }
    }

    names
        .stream()
        .filter(s -> s.equals("Tom"))
        .forEach(System.out::println);

    names
        .stream()
        .limit(1)
        .forEach(System.out::println);

    names
        .stream()
        .takeWhile(s -> s.equals("Tom"))
        .forEach(System.out::println);
  }


}
