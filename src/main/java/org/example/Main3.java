package org.example;

import java.util.Locale;
import java.util.Random;
import java.util.function.Function;

public class Main3 {

  public static void main(String[] args) {
    int uppercase = Integer.parseInt(args[0]);
    int lowercase = Integer.parseInt(args[1]);
    int digits = Integer.parseInt(args[2]);
    int minimumMandatory = uppercase + lowercase + digits;
    int maxLength = Integer.parseInt(args[3]);

    final Random random = new Random();
    record PasswordGen(int previous, int lower, int upper) {

    }
    ;

    Function<PasswordGen, Integer> getCharacter = (pwGen) -> {
      int character = 0;
      while (pwGen.previous == character || character == 0) {
        character = random.nextInt(pwGen.lower, pwGen.upper + 1);
      }
      return character;
    };

    String password = "";
    while (password.length() < maxLength) {
      int previous = password.length() > 0 ? password.charAt(password.length() - 1) : 0;
      int randomType = password.length() < minimumMandatory ? 0 : random.nextInt(1, 4);
      int currentChar = 0;
      if (uppercase > 0 || randomType == 1) {
        currentChar = getCharacter.apply(new PasswordGen(previous, 65, 90));
        uppercase--;
      }
      if (currentChar == 0 && (lowercase > 0 || randomType == 2)) {
        currentChar = getCharacter.apply(new PasswordGen(previous, 97, 122));
        lowercase--;
      }
      if (currentChar == 0 && (digits > 0 || randomType == 3)) {
        currentChar = getCharacter.apply(new PasswordGen(previous, 48, 57));
        digits--;
      }
      System.out.println(currentChar);
      password += (char) currentChar;
    }

    System.out.println(password);
    System.out.println("Press Enter to exit...");
    System.out.printf("%1.3f", Math.PI);
    System.out.println();
    System.out.format(Locale.US, "%1.3f", Math.PI);
  }
}