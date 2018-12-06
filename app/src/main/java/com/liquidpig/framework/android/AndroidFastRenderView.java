package com.liquidpig.framework.android;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Manages a game display thread and runs the main loop.
 */
public class AndroidFastRenderView extends SurfaceView implements Runnable
{
  private AndroidGame game;
  private Bitmap frameBuffer;
  private Thread renderThread = null;
  private SurfaceHolder holder;
  volatile boolean running = false;

  /**
   * Creates the view.
   * @param game is the game it will control and render.
   * @param frameBuffer is the game's rendered graphics ready to be put on the screen.
   */
  public AndroidFastRenderView(AndroidGame game, Bitmap frameBuffer)
  {
    super(game);
    this.game = game;
    this.frameBuffer = frameBuffer;
    this.holder = getHolder();
  }

  /**
   * Restarts the render thread.
   */
  public void resume()
  {
    running = true;
    renderThread = new Thread(this);
    renderThread.start();
  }

  /**
   * This seems to be the main loop of the game here.
   */
  public void run()
  {
    Rect dstRect = new Rect();
    long startTime = System.nanoTime();
    while (running)
    {
      if (!holder.getSurface().isValid()) continue;
      long currentTime = System.nanoTime();
      float delta = (currentTime - startTime) / 10000000.0f;
      startTime = currentTime;
      if (delta > 3.15) delta = 3.15f;
      game.getCurrentScreen().update(delta);
      game.getCurrentScreen().paint(delta);
      Canvas canvas = holder.lockCanvas();
      canvas.getClipBounds(dstRect);
      canvas.drawBitmap(frameBuffer, null, dstRect, null);
      holder.unlockCanvasAndPost(canvas);
    }
  }

  /**
   * When the renderer is paused it tries to stop the render thread.
   */
  public void pause()
  {
    running = false;
    while (true)
    {
      try
      {
        renderThread.join();
        break;
      }
      catch (InterruptedException e)
      {
        // pass.
      }
    }
  }
}
