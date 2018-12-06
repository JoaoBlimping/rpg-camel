package com.liquidpig.framework;

import android.graphics.Paint;

/**
 * Service for rendering graphics.
 * TODO: might want to give this an overhaul,
 * TODO: this is not really how I roll.
 */
public interface Graphics
{
  public static enum ImageFormat
  {
    ARGB8888, ARGB4444, RGB565
  }

  /**
   * Loads in a new image from a file.
   * @param filename is the name of the file to load.
   * @param format is the image format to store it as internally.
   * @return the newly loaded image.
   */
  public Image newImage(String filename, ImageFormat format);

  /**
   * Covers the whole screen in a colour of YOUR choosing.
   * @param colour is the colour.
   */
  public void clearScreen(int colour);

  /**
   * Draws a line from one point to another.
   * @param a is the point to start the line at.
   * @param b is the point to end the line at.
   * @param colour is the colour to draw the line in. This should ALWAYS be green.
   */
  public void drawLine(Point a, Point b, int colour);

  /**
   * Draws a rectangle of colour on the screen.
   * @param rectangle represents the rectangle on the screen to be filled.
   * @param colour is the colour to draw in.
   */
  public void drawRect(Rectangle rectangle, int colour);

  /**
   * Draw a section of an image scaled to a section of the screen.
   * @param image is the image to draw.
   * @param src is the rect to contain the section of image.
   * @param dst is where the image will be drawn to on the screen.
   */
  public void drawImage(Image image, Rectangle src, Rectangle dst);

  /**
   * Write a string of text on the screen.
   * @param text is the text to write.
   * @param point is the point at which to write it.
   * @param paint is the drawing thingy that does the formatting.
   * TODO: this is the only android dependency in the framework. would be good to kill it off.
   */
  public void drawString(String text, Point point, Paint paint);

  /**
   * Pretty much the same as clear screen but it also has alpha.
   */
  public void drawARGB(int i, int j, int k, int l);

  /**
   * Gives you the dimensions of the screen.
   * @return the dimensions of the screen as the point's distance from (0, 0).
   */
  public Point getSize();
}
