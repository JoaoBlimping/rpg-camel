package com.liquidpig.framework;

/**
 * Represents a point in two dimensional space.
 */
public class Point
{
  public float x;
  public float y;

  /**
   * Cloning constructor.
   * @param parent is the point to clone.
   */
  public Point(Point parent)
  {
    this.x = parent.x;
    this.y = parent.y;
  }

  /**
   * Creates a point with a set position.
   * @param x is the x position for the point.
   * @param y is the y position for the point.
   */
  public Point(float x, float y)
  {
    this.x = x;
    this.y = y;
  }

  /**
   * Creates a point at dead 0 on each axis.
   */
  public Point()
  {
    this.x = 0;
    this.y = 0;
  }

  /**
   * sets the point's horizontal position.
   * @param x is the new position.
   */
  public void setX(float x)
  {
    this.x = x;
  }

  /**
   * Gives you the point's horizontal position.
   * @return the horizontal position.
   */
  public float getX()
  {
    return this.x;
  }

  /**
   * sets the point's vertical position.
   * @param y is the new position.
   */
  public void setY(float y)
  {
    this.y = y;
  }

  /**
   * Gets the points vertical position.
   * @return the vertical position.
   */
  public float getY()
  {
    return this.y;
  }

  /**
   * Adds another point to this point, modifying this point.
   * @param other is the other point which does not get modified.
   */
  public void add(Point other)
  {
    this.x += other.x;
    this.y += other.y;
  }

  /**
   * Adds a single value to both axes of the point.
   * @param other is the value to add.
   */
  public void add(float other)
  {
    this.x += other;
    this.y += other;
  }

  /**
   * Subtracts another point from this point, modifying this point.
   * @param other is the other point which emerges from this operating unchanged.
   */
  public void subtract(Point other)
  {
    this.x -= other.x;
    this.y -= other.y;
  }

  /**
   * Subtracts a single value from both axes of the point.
   * @param other is the value to subtract.
   */
  public void subtract(float other)
  {
    this.x -= other;
    this.y -= other;
  }

  /**
   * Multiplies the axes of this point by the axes of another.
   * @param other is the other point which is not modified.
   */
  public void multiply(Point other)
  {
    this.x *= other.x;
    this.y *= other.y;
  }

  /**
   * multiplies both axes of this point by a value.
   * @param other is the value to muliply by.
   */
  public void multiply(float other)
  {
    this.x *= other;
    this.y *= other;
  }

  /**
   * Divides the axes of this point by the axes of another.
   * @param other is the other to divide by which is unmodified by the exchange.
   */
  public void divide(Point other)
  {
    this.x /= other.x;
    this.y /= other.y;
  }

  /**
   * Divide the axes of the point by a value.
   * @param other is the value to divide by.
   */
  public void divide(float other)
  {
    this.x /= other;
    this.y /= other;
  }



  // IDEA: we can add some mathematical stuff here later.
}
