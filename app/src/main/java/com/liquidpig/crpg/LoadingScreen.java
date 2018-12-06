package com.liquidpig.crpg;

import com.liquidpig.framework.Game;
import com.liquidpig.framework.Graphics;
import com.liquidpig.framework.Screen;
import com.liquidpig.framework.Graphics.ImageFormat;

/**
 * Screen to show logos and that kind of boring shit.
 */
public class LoadingScreen extends Screen
{
  /**
   * Creates the screen.
   * @param game is the game object with useful stuff.
   */
  public LoadingScreen(Game game)
  {
    super(game);
  }

  @Override
  public void update(float delta)
  {
    Graphics g = game.getGraphics();
    Assets.menu = g.newImage("images/menu.jpg", ImageFormat.RGB565);
    //Assets.click = game.getAudio().createSound("explode.ogg");
    game.pushScreen(new MenuScreen(game));
  }

  @Override
  public void paint(float delta) {}

  @Override
  public void pause() {}


  @Override
  public void resume() {}


  @Override
  public void dispose() {}


  @Override
  public void backButton() {}
}
