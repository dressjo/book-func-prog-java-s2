package org.example;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public class Test {

  private String TestString = "Outer Hello World";


  static class LanguageUtil {
    public static void print() {
      Locale locale = new Locale("fr"); // "fr" = French
      System.out.println("Language: " + locale.getDisplayLanguage()); // Output: French

      // List all available language codes
      for (String code : Locale.getISOLanguages()) {
        Locale loc = new Locale(code);
        System.out.println(code + " - " + loc.getDisplayLanguage());
      }
    }
  }

  public static void main(String[] args) {
    new Test().InnerTest().print();
    new LanguageUtil().print();


    Function<Integer, Integer> mapInt = Test::getInt;

    System.out.println(mapInt.andThen(mapInt).apply(1));
    int testInt = 23;
    Supplier<Integer> supplyInt = () -> testInt;

    System.out.println(supplyInt.get());
    System.out.println(supplyInt.get());

  }

  private void testLambda(Function<Integer, Integer> inner) {

  }

  public InnerTest InnerTest() {
    return new InnerTest();
  }

  public static int getInt(int i) {
    System.out.println("Get int");
    return i + 1;
  }


  class InnerTest {

    public static void main(String... args) {
    }

    static class A {
      public void print() {
        System.out.println("A");
      }

      public static void main(String[] args) {
        A a = new B();
a.print();
      }
    }

    static class B extends A {
      public void print() {
        System.out.println("B");
        super.print();
      }
    }

    static class C extends A {
      public void print()  {
        System.out.println("C");
      }
    }

    private String InnerTestString = "Inner Hello World";

    public void print() {
      System.out.println(TestString);
    }

    public String getInnerTestString() {
      return InnerTestString;
    }

    public void setInnerTestString(String innerTestString) {
      InnerTestString = innerTestString;
    }
  }


}
