package com.liquidpig.framework;

/**
 * Represents a rectangle in two dimensional space.
 */
public class Rectangle
{
  public final Point position;
  public final Point size;

  /**
   * Creates a rectangle and gives it a set position and size made from points.
   * @param position is the rectangle's position. This represents the top left corner of the
   *                       rectangle so long as size is > 0 in both axes.
   * @param size is how far the ends of the rectangle extend from it's position.
   */
  public Rectangle(Point position, Point size)
  {
    this.position = new Point(position);
    this.size = new Point(size);
  }

  /**
   * Creates a rectangle and creates it's interior points too.
   * @param x is the rectangle's horizontal position.
   * @param y is the rectangle's vertical position.
   * @param w is the rectangle's width.
   * @param h is the rectangle's heigh.
   */
  public Rectangle(float x, float y, float w, float h)
  {
    this.position = new Point(x, y);
    this.size = new Point(w, h);
  }

  /**
   * Size only constructor with position at (0, 0).
   * @param size is the size.
   */
  public Rectangle(Point size)
  {
    this.position = new Point();
    this.size = new Point(size);
  }

  /**
   * Cloning constructor.
   * @param parent is the rectangle to clone.
   */
  public Rectangle(Rectangle parent)
  {
    this.position = new Point(parent.position);
    this.size = new Point(parent.size);
  }
}
