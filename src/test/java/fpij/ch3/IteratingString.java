package fpij.ch3;

import org.junit.jupiter.api.Test;

public class IteratingString {

  @Test
  public void iterateString() {
    final String str = "w00t";
    str.chars().forEach(IteratingString::printChar);
  }

  private static void printChar(int aChar) {
    System.out.println((char)(aChar));
  }

  @Test
  public void iterateStringWithMap() {
    final String str = "w00t";
    str.chars()
        .mapToObj(ch -> Character.valueOf((char)ch))
        .forEach(System.out::println);
  }

  @Test
  public void iterateStringWitFilter() {
    final String str = "w00t";
    str.chars()
        .filter(Character::isDigit)
        .forEach(IteratingString::printChar);
  }

}
