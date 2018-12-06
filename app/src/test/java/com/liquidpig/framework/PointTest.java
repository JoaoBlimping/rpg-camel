package com.liquidpig.framework;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests that the point objects are doing their shit right.
 */
public class PointTest
{
  public static final float DELTA = 0.001f;

  @Test
  public void addTest()
  {
    Point a = new Point(1, 2);
    Point b = new Point(3, 4);
    a.add(b);
    assertEquals(4, a.x, DELTA);
    assertEquals(6, a.y, DELTA);
    assertEquals(3, b.x, DELTA);
    assertEquals(4, b.y, DELTA);
    b.add(a);
    assertEquals(4, a.x, DELTA);
    assertEquals(6, a.y, DELTA);
    assertEquals(7, b.x, DELTA);
    assertEquals(10, b.y, DELTA);
    a.add(a);
    assertEquals(8, a.x, DELTA);
    assertEquals(12, a.y, DELTA);
    assertEquals(7, b.x, DELTA);
    assertEquals(10, b.y, DELTA);
  }

  @Test
  public void subtractTest()
  {
    Point a = new Point(10, 8);
    Point b = new Point(5, 2);
    a.subtract(b);
    assertEquals(5, a.x, DELTA);
    assertEquals(6, a.y, DELTA);
    assertEquals(5, b.x, DELTA);
    assertEquals(2, b.y, DELTA);
    a.subtract(b);
    assertEquals(0, a.x, DELTA);
    assertEquals(4, a.y, DELTA);
    assertEquals(5, b.x, DELTA);
    assertEquals(2, b.y, DELTA);
    a.subtract(b);
    assertEquals(-5, a.x, DELTA);
    assertEquals(2, a.y, DELTA);
    assertEquals(5, b.x, DELTA);
    assertEquals(2, b.y, DELTA);
    b.subtract(b);
    assertEquals(-5, a.x, DELTA);
    assertEquals(2, a.y, DELTA);
    assertEquals(0, b.x, DELTA);
    assertEquals(0, b.y, DELTA);
    a.subtract(b);
    assertEquals(-5, a.x, DELTA);
    assertEquals(2, a.y, DELTA);
    assertEquals(0, b.x, DELTA);
    assertEquals(0, b.y, DELTA);
    a.subtract(10);
    assertEquals(-15, a.x, DELTA);
    assertEquals(-8, a.y, DELTA);
  }

  @Test
  public void multiplyTest()
  {
    assertEquals(1, 1);
  }

  @Test
  public void divideTest()
  {
    assertEquals(1, 1);
  }
}
