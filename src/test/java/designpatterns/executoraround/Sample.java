package designpatterns.executoraround;

import java.util.function.Consumer;

class Resource {

  private Resource() {
    System.out.println("Open db connection");
  }

  public void op1() {
    System.out.println("Op1");
  }

  public void op2() {
    System.out.println("Op2");
  }

  private void close() {
    System.out.println("Close db");
  }

  public static void use(Consumer<Resource> block) {
    Resource resource = new Resource();
    try {
      block.accept(resource);
    } finally {
      resource.close();
    }
  }

}

public class Sample {

  public static void main(String[] args) {
    Resource.use(
        (resource) -> {
          resource.op1();
          resource.op2();
        }
    );

    Transaction.runInTransaction((t) -> {
      System.out.println("Doing transaction work");
    });

  }

}

class Transaction {

  private Transaction() {
    System.out.println("Create transaction");
  }

  public static void runInTransaction(Consumer<Transaction> block) {
    final Transaction transaction = new Transaction();
    try {
      block.accept(transaction);
      transaction.commit();
    } catch (Exception e) {
      transaction.rollback();
    }
  }

  private void commit() {
    System.out.println("Commit");
  }

  private void rollback() {
    System.out.println("Rollback");
  }

  private void startTransaction() {
    System.out.println("Start transaction");
  }

}
