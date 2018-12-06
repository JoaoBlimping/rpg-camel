package com.liquidpig.crpg;

import java.util.List;
import java.io.IOException;

import com.liquidpig.framework.Game;
import com.liquidpig.framework.FileIO;
import com.liquidpig.framework.Graphics;
import com.liquidpig.framework.Screen;
import com.liquidpig.framework.Input.TouchEvent;

public class MenuScreen extends Screen
{
  public MenuScreen(Game game)
  {
    super(game);
  }

  @Override
  public void update(float delta)
  {
    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
    int len = touchEvents.size();
    for (int i = 0; i < len; i++)
    {
      TouchEvent event = touchEvents.get(i);
      if (event.type == TouchEvent.TOUCH_UP)
      {
        // if (inBounds(event, 0, 0, 250, 250))
        // {
        //   game.setScreen(new GameScreen(game));
        // }
      }
    }
  }

  @Override
  public void paint(float delta)
  {
    Graphics g = game.getGraphics();
    g.drawImage(Assets.menu, null, null);
  }

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  @Override
  public void dispose() {}

  @Override
  public void backButton() {}
}
