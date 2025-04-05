package fpij.ch3;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchFileChange {

  private WatchService watchService;

  public WatchFileChange() throws IOException {
    final Path path = Paths.get("c:/databases");
    watchService =
        path.getFileSystem()
            .newWatchService();
    path.register(watchService, ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_CREATE);
    System.out.println("Report any file changed within next 1 minute...");
  }

  public void pollChanges() throws InterruptedException {

    WatchKey watchKey = null;
    while((watchKey = watchService.take()) != null) {
      System.out.println("Polling...");
      if (watchKey != null) {
        watchKey.pollEvents()
            .stream()
            .forEach(event ->
                System.out.println("Event: " + event.context() + " " + event.kind()));
        watchKey.reset();
      }
    }
  }

  public static void main(String[] args) {
    try {
      new WatchFileChange().pollChanges();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

}
