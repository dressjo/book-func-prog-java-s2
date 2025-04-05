package fpij.ch6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.jupiter.api.Test;

public class ManagingLocks {

  @Test
  public void runLocking() {
    final Locking locking = new Locking();
    locking.doOp1();
  }

  @Test
  public void runLockedLambda() {
    Locker.runLocked(new ReentrantLock(), () -> System.out.println("Do critical section within lock"));
  }

}

class Locker {
  public static void runLocked(Lock lock, Runnable block) {
    for(int i = 0; i < 10; i++) {
      lock.lock();
    }
    switch(lock) {
      case ReentrantLock reentrantLock -> System.out.println("Hold count: " + reentrantLock.getHoldCount());
      default -> System.out.println("");
    }
    try {
      block.run();
    } finally {
      lock.unlock();
    }
  }
}

class Locking {

  Lock lock = new ReentrantLock(); //or mock

  protected void setLock(final Lock mock) {
    lock = mock;
  }

  public void doOp1() {
    lock.lock();
    try {
      System.out.println("Locking in critical section");
    } finally {
      lock.unlock();
    }
  }
}

class MyLock implements Lock {

  @Override
  public void lock() {
    System.out.println("MyLock::lock");
  }

  @Override
  public void lockInterruptibly() throws InterruptedException {
    System.out.println("MyLock::lockInterruptibly");
  }

  @Override
  public boolean tryLock() {
    System.out.println("MyLock::tryLock");
    return true;
  }

  @Override
  public boolean tryLock(long time, java.util.concurrent.TimeUnit unit) throws InterruptedException {
    return true;
  }

  @Override
  public void unlock() {
    System.out.println("MyLock::unlock");
  }

  @Override
  public java.util.concurrent.locks.Condition newCondition() {
    System.out.println("MyLock::newCondition");
    return null;
  }

}
