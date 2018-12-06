package com.liquidpig.framework.android;

import android.graphics.Bitmap;

import com.liquidpig.framework.Image;
import com.liquidpig.framework.Graphics.ImageFormat;
import com.liquidpig.framework.Point;

/**
 * Android image container.
 */
public class AndroidImage implements Image
{
  public final Bitmap bitmap;
  public final ImageFormat format;

  /**
   * Creates an image from bitmap data and tells it it's format.
   * @param bitmap is the bitmap data.
   * @param format is the format of the bitmap.
   */
  public AndroidImage(Bitmap bitmap, ImageFormat format)
  {
    this.bitmap = bitmap;
    this.format = format;
  }

  @Override
  public Point getSize()
  {
    return new Point(this.bitmap.getWidth(), this.bitmap.getHeight());
  }

  @Override
  public ImageFormat getFormat()
  {
    return this.format;
  }

  @Override
  public void dispose()
  {
    this.bitmap.recycle();
  }
}
