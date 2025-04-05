package designpatterns.factorymethod;

interface Player {
  Pet getPet();

  default void play() {
    System.out.println("I like playing");
    System.out.println("playing with " + getPet());
  }
}

interface Pet {}

class Dog implements Pet {}
class Cat implements Pet {}

class DogLover implements Player {
  @Override
  public Pet getPet() { return new Dog(); }
}

public class Sample {

  public static void callPlay(Player player) {
       player.play();
  }

  public static void main(String[] args) {
    callPlay(new DogLover());
  }



}
