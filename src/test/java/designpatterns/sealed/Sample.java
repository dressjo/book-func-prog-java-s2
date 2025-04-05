package designpatterns.sealed;



public class Sample {
  TrafficLight trafficLight = new RedLight();
}

sealed interface TrafficLight permits RedLight, GreenLight, YellowLight {
  void showLight();
}

final class RedLight implements TrafficLight {
  @Override
  public void showLight() {
    System.out.println("Red");
  }
}

final class GreenLight implements TrafficLight {
  @Override
  public void showLight() {

  }
}

final class YellowLight implements TrafficLight {
  @Override
  public void showLight() {

  }
}
