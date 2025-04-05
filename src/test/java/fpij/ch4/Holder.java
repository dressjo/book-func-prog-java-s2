package fpij.ch4;

public class Holder {

  record Tuple(String least, String most) {}

  private Tuple tuple;

  public Holder() {
    this.tuple = new Tuple("least", "most");
  }

  public Tuple getTuple() {
    return tuple;
  }


}
