package org.example;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class BatchIterator implements Iterator<List<Character>> {

  char[] chars;
  int currentPos;
  public BatchIterator(char[] chars) {
    this.chars = chars;
    this.currentPos = 0;
  }

  @Override
  public boolean hasNext() {
    return currentPos < chars.length;
  }

  @Override
  public List<Character> next() {
    int endPos = currentPos + 3 < chars.length ? currentPos + 3 : chars.length;
    final char[] batch = Arrays.copyOfRange(chars, currentPos, endPos);
    currentPos = endPos;

    return convertCharArrayToList(batch);
  }

  public static List<Character> convertCharArrayToList(char[] chars) {
    return chars == null ? null : new String(chars).chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    BatchIterator batchIterator = new BatchIterator("XOOXOOOXOO".toCharArray());
    while (batchIterator.hasNext()) {
      batchIterator.next().stream().forEach(System.out::print);
      System.out.println();
    }
  }

}
