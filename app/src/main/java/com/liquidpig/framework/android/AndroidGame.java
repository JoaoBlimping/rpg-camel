package com.liquidpig.framework.android;

import java.util.LinkedList;
import java.util.Stack;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import com.liquidpig.framework.Audio;
import com.liquidpig.framework.FileIO;
import com.liquidpig.framework.Game;
import com.liquidpig.framework.Graphics;
import com.liquidpig.framework.Input;
import com.liquidpig.framework.Screen;
import com.liquidpig.framework.Point;

/**
 * Partial implementation of game class for use on android.
 */
public abstract class AndroidGame extends Activity implements Game
{
  private Stack<Screen> screens = new Stack<Screen>();
  private AndroidFastRenderView renderView;
  private AndroidGraphics graphics;
  private AndroidAudio audio;
  private AndroidInput input;
  private AndroidFileIO fileIO;
  private WakeLock wakeLock;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(
      WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN
    );
    boolean isPortrait = getResources().getConfiguration().orientation ==
      Configuration.ORIENTATION_PORTRAIT;
    Point frameBufferSize = isPortrait ? new Point(800, 1200) : new Point(1200, 800);
    Bitmap frameBuffer = Bitmap.createBitmap(
            (int)frameBufferSize.x,
            (int)frameBufferSize.y,
      Config.RGB_565
    );
    frameBufferSize.divide(new Point(
      getWindowManager().getDefaultDisplay().getWidth(),
      getWindowManager().getDefaultDisplay().getHeight()
    ));
    renderView = new AndroidFastRenderView(this, frameBuffer);
    graphics = new AndroidGraphics(getAssets(), frameBuffer);
    fileIO = new AndroidFileIO(this);
    audio = new AndroidAudio(this);
    input = new AndroidInput(renderView, frameBufferSize);
    pushScreen(getInitScreen());
    setContentView(renderView);
    PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
    wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "game: wanker");
  }

  @Override
  public void onResume()
  {
    super.onResume();
    wakeLock.acquire(1000000);
    this.screens.peek().resume();
    renderView.resume();
  }

  @Override
  public void onPause()
  {
    super.onPause();
    wakeLock.release();
    renderView.pause();
    this.screens.peek().pause();
    if (isFinishing()) this.screens.peek().dispose();
  }

  @Override
  public Input getInput()
  {
    return input;
  }

  @Override
  public FileIO getFileIO()
  {
    return fileIO;
  }

  @Override
  public Graphics getGraphics()
  {
    return (Graphics)graphics;
  }

  @Override
  public Audio getAudio()
  {
    return audio;
  }

  @Override
  public void pushScreen(Screen screen)
  {
    if (screen == null) throw new IllegalArgumentException("Screen can't be set to null.");
    if (!this.screens.empty()) this.screens.peek().pause();
    screen.resume();
    screen.update(0);
    this.screens.push(screen);
  }

  @Override
  public void popScreen()
  {
    if (this.screens.isEmpty()) return;
    this.screens.pop().dispose();
  }

  @Override
  public void replaceScreen(Screen screen)
  {
    if (!this.screens.isEmpty()) this.popScreen();
    this.pushScreen(screen);
  }

  @Override
  public Screen getCurrentScreen()
  {
    return this.screens.peek();
  }

  @Override
  public void onBackPressed()
  {
    this.screens.peek().backButton();
  }
}
