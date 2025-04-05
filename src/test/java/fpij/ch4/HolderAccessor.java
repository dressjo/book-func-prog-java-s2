package fpij.ch4;

import fpij.ch4.Holder.Tuple;

public class HolderAccessor {

  public static void main(String[] args) {
    final Holder holder = new Holder();

    final Tuple tuple = holder.getTuple();
    System.out.println(tuple.least());
    System.out.println(tuple.most());
  }

}
