package fpij.ch3;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class FileHandling {

  @Test
  public void listFiles() throws IOException {
    Files.list(Paths.get("."))
        .forEach(System.out::println);
  }

  @Test
  public void listFilesFilter() throws IOException {
    Files.list(Paths.get("."))
        .filter(Files::isDirectory)
        .forEach(System.out::println);
  }

  @Test
  public void filterFilesVerbose() {
    final String[] files =
        new File(".").list(new java.io.FilenameFilter() {
          public boolean accept(final File dir, final String name) {
            return name.endsWith(".bat");
          }
        });
    if (files != null) {
      for (String file : files) {
        System.out.println(file);
      }
    }
  }

  @Test
  public void filterFiles() throws IOException {
        Files.list(Paths.get("."))
            .filter(f -> {
              return f.getFileName().toFile().getName().endsWith(".bat");
            })
            .forEach((f) -> System.out.println("After: " + f.getFileName()));
  }

  @Test
  public void filterFilesStream() throws IOException {
    Files.newDirectoryStream(Paths.get("c:/windows"), path -> path.toString().contains("twain_32.dll"))
        .forEach(System.out::println);

    Files.newDirectoryStream(Paths.get("c:/windows"), path -> path.toFile().isHidden())
        .forEach(s -> System.out.println("Hidden: " + s));

    final File[] files = new File("c:/windows").listFiles(File::isHidden);
    Stream.of(files).forEach(System.out::println);
  }

  @Test
  public void listSubdirectoriesTheHardWay() {
    List<File> files = new ArrayList<>();
    File[] filesInCurrentDir = new File("c:/databases").listFiles();
    for(File file : filesInCurrentDir) {
      File[] filesInSubDir = file.listFiles();
      if(filesInSubDir != null) {
        files.addAll(Arrays.asList(filesInSubDir));
      } else {
        files.add(file);
      }
    }
    System.out.println("Count: " + files.size());
    files.stream().forEach(System.out::println);
  }

  @Test
  public void listSubdirectoriesBetterWay() {
    List<File> files =
        Stream.of(new File(".").listFiles())
            .flatMap(file -> file.listFiles() == null ?
                Stream.of(file) : Stream.of(file.listFiles()))
            .collect(Collectors.toList());
    System.out.println("Count: " + files.size());
  }

  @Test
  public void listSubdirectoriesWalkFileTree() throws IOException {
    Files.walkFileTree(Paths.get("c:/databases"), new SimpleFileVisitor<>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file);
        return super.visitFile(file, attrs);
      }
    }) ;
  }

}
