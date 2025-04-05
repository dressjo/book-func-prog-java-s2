package fpij.ch5;

import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

public class FluentInterfaces {

  @Test
  public void sendMailNonFluent() {
    new MailBuilder()
        .from("from")
        .send();

  }
  @Test
  public void sendMailFluent() {
    Consumer<FluentMailer> fluentMailConsumer = (FluentMailer fluentMailer) -> fluentMailer
        .from("from")
        .to("to")
        .subject("subject")
        .body("...");
    FluentMailer.send(fluentMailConsumer);
  }

}

class FluentMailer {

  private FluentMailer() {
  }

  public FluentMailer from(final String address) { /*... */
    return this;
  }

  public FluentMailer to(final String address) { /*... */
    return this;
  }

  public FluentMailer subject(final String line) { /*... */
    return this;
  }

  public FluentMailer body(final String message) { /*... */
    return this;
  }

  public static void send(final Consumer<FluentMailer> block) {
    final FluentMailer mailer = new FluentMailer();
    block.accept(mailer);
    System.out.println("sending...");
  }
}

class MailBuilder {

  public MailBuilder from(final String address) { /*... */
    return this;
  }

  public MailBuilder to(final String address) { /*... */
    return this;
  }

  public MailBuilder subject(final String line) { /*... */
    return this;
  }

  public MailBuilder body(final String message) { /*... */
    return this;
  }

  public void send() {
    System.out.println("sending...");
  }
}

