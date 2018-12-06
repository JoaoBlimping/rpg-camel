package com.liquidpig.framework.android;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import com.liquidpig.framework.Graphics;
import com.liquidpig.framework.Image;
import com.liquidpig.framework.Point;
import com.liquidpig.framework.Rectangle;

/**
 * Implementation of Graphics for android.
 */
public class AndroidGraphics implements Graphics
{
  AssetManager assets;
  Bitmap frameBuffer;
  Canvas canvas;
  Paint paint;
  Rect srcRect = new Rect();
  Rect dstRect = new Rect();

  public AndroidGraphics(AssetManager assets, Bitmap frameBuffer)
  {
    this.assets = assets;
    this.frameBuffer = frameBuffer;
    this.canvas = new Canvas(frameBuffer);
    this.paint = new Paint();
  }

  @Override
  public Image newImage(String filename, ImageFormat format)
  {
    // Setup the right config for the image.
    Config config = null;
    if (format == ImageFormat.RGB565) config = Config.RGB_565;
    else if (format == ImageFormat.ARGB4444) config = Config.ARGB_4444;
    else if (format == ImageFormat.ARGB8888) config = Config.ARGB_8888;
    Options options = new Options();
    options.inPreferredConfig = config;
    // Load in the image.
    InputStream in = null;
    Bitmap bitmap = null;
    try
    {
      in = assets.open(filename);
      bitmap = BitmapFactory.decodeStream(in, null, options);
      if (bitmap == null) throw new RuntimeException("couldn't load bitmap from "+filename);
    }
    catch (IOException e)
    {
      throw new RuntimeException("Could NOT load bitmap FORM RE assete "+filename);
    }
    finally
    {
      if (in != null)
      {
        try
        {
          in.close();
        }
        catch (IOException e)
        {
          // do nothing.
        }
      }
    }
    if (bitmap.getConfig() == Config.RGB_565) format = ImageFormat.RGB565;
    else if (bitmap.getConfig() == Config.ARGB_4444) format = ImageFormat.ARGB4444;
    else format = ImageFormat.ARGB8888;
    return new AndroidImage(bitmap, format);
  }

  @Override
  public void clearScreen(int colour)
  {
    canvas.drawRGB((colour & 0xff0000) >> 16, (colour & 0xff00) >> 8, colour & 0xff);
  }

  @Override
  public void drawLine(Point a, Point b, int colour)
  {
    paint.setColor(colour);
    canvas.drawLine(a.getX(), a.getY(), b.getX(), b.getY(), paint);
  }

  @Override
  public void drawRect(Rectangle rect, int colour)
  {
    paint.setColor(colour);
    paint.setStyle(Style.FILL);
    canvas.drawRect(
            (int)rect.position.x,
            (int)rect.position.y,
            (int)(rect.position.x + rect.size.x),
            (int)(rect.position.y + rect.size.y),
            paint
    );
  }

  @Override
  public void drawARGB(int a, int r, int g, int b)
  {
    paint.setStyle(Style.FILL);
    canvas.drawARGB(a, r, g, b);
  }

  @Override
  public void drawString(String text, Point point, Paint paint)
  {
    canvas.drawText(text, point.x, point.y, paint);
  }

  @Override
  public void drawImage(Image image, Rectangle src, Rectangle dst)
  {
    if (src == null) src = new Rectangle(image.getSize());
    if (dst == null) dst = new Rectangle(getSize());
    srcRect.left = (int)src.position.x;
    srcRect.top = (int)src.position.y;
    srcRect.right = (int)(src.position.x + src.size.x);
    srcRect.bottom = (int)(src.position.y + src.size.y);
    dstRect.left = (int)dst.position.x;
    dstRect.top = (int)dst.position.y;
    dstRect.right = (int)(dst.position.x + dst.size.x);
    dstRect.bottom = (int)(dst.position.y + dst.size.y);
    canvas.drawBitmap(((AndroidImage)image).bitmap, srcRect, dstRect, null);
  }

  @Override
  public Point getSize()
  {
    return new Point(frameBuffer.getWidth(), frameBuffer.getHeight());
  }
}
