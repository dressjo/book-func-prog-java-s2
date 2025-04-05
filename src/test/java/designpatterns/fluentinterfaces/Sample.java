package designpatterns.fluentinterfaces;

import java.util.function.Consumer;

class Mailer {
  private Mailer() {}

  public Mailer from(String from) {
    System.out.println(from);
    return this;
  }

  public Mailer to(String to) {
    System.out.println(to);
    return this;
  }

  public Mailer subject(String subject) {
    System.out.println(subject);
    return this;
  }

  public Mailer body(String body) {
    System.out.println(body);
    return this;
  }

  public static void send(Consumer<Mailer> block) {
    Mailer mailer = new Mailer();
    block.accept(mailer);
  }

}

public class Sample {
  /*
    Code is less noisy
    Creator of abstraction decides if mailer should be reused or not
   */
  public static void main(String[] args) {
    Mailer.send(mailer -> {
      mailer
          .from("jd@ecoclime.se")
          .to("jdto@ecoclime.se")
          .subject("test")
          .body("body");
      System.out.println("Send email");
    });
  }

}
