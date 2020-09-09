package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Point;

public class PointTest {
  @Test
  public void distanceTest() {
    Point p1 = new Point(2,5);
    Point p2 = new Point(5,5);
    Assert.assertEquals(p1.distance(p2),3.0);
  }

}
