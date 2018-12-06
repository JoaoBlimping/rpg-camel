package com.liquidpig.framework;

import com.liquidpig.framework.Graphics.ImageFormat;

public interface Image
{
  /**
   * Gives you the dimensions of the image.
   * @return the dimensions as a point.
   */
  public Point getSize();

  /**
   * Gives you the format of the image.
   * @return the image format.
   */
  public ImageFormat getFormat();

  /**
   * Destroys the image and all of it's junk hell yeah.
   */
  public void dispose();
}
