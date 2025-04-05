package fpij.ch6;

import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class CleaningUpResources {

  /*
    Automatic Resource Management (ARM) pattern
    Need to remember to use try-with-resources
   */
  @Test
  public void closeResourceWithoutLambdaARM() throws IOException {
    try (final FileWriterARM writerARM = new FileWriterARM("peekaboo.txt")) {
      writerARM.writeStuff("message");
      System.out.println("done with the resource...");
    }
  }

  /*
    Execute Around Method pattern (EAM) pattern
   */
  @Test
  public void closeResourceLambdaEAM() throws IOException {
    FileWriterEAM.use("peekaboo.txt", CleaningUpResources::writeMyStrings);
    System.out.println(
        FileWriterEAM.useResult("peekaboo.txt", CleaningUpResources::writeMyStringsResult));
  }

  private static void writeMyStrings(FileWriterEAM writerEam) throws IOException {
     writerEam.writeStuff("Howdy");
     writerEam.writeStuff("Functional method");
  }

  private static String writeMyStringsResult(FileWriterEAM writerEam) throws IOException {
    writerEam.writeStuff("Howdy");
    writerEam.writeStuff("Functional method");
    return "Howdy function method";
  }

}

class FileWriterEAM {

  private final FileWriter writer;

  private FileWriterEAM(final String fileName) throws IOException {
    writer = new FileWriter(fileName);
  }

  private void close() throws IOException {
    System.out.println("close called automatically...");
    writer.close();
  }

  public void writeStuff(final String message) throws IOException {
    writer.write(message);
  }

  public static void use(final String fileName, final UseInstance<FileWriterEAM, IOException> block) throws IOException {
    final FileWriterEAM writerEAM = new FileWriterEAM(fileName);
    try {
      block.accept(writerEAM);
    } finally {
      writerEAM.close();
    }
  }

  public static <R> R useResult(final String fileName, final UseInstanceResult<FileWriterEAM, R, IOException> block) throws IOException {
    final FileWriterEAM writerEAM = new FileWriterEAM(fileName);
    try {
      return block.accept(writerEAM);
    } finally {
      writerEAM.close();
    }
  }

}

@FunctionalInterface
interface UseInstance<T, X extends Throwable> {
  void accept(T instance) throws X;
}

@FunctionalInterface
interface UseInstanceResult<T, R, X extends Throwable> {
  R accept(T instance) throws X;
}

class FileWriterARM implements AutoCloseable {
  private final FileWriter writer;
  public FileWriterARM(final String fileName) throws IOException {
    writer = new FileWriter(fileName);
  }
  public void writeStuff(final String message) throws IOException {
    writer.write(message);
  }

  public void close() throws IOException {
    System.out.println("close called automatically...");
    writer.close();
  }
}
