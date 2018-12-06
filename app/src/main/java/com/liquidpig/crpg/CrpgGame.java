package com.liquidpig.crpg;

import com.liquidpig.framework.Screen;
import com.liquidpig.framework.android.AndroidGame;

/**
 * Entrypoint to the CRPG game engine. Here it builds itself using the android implementation but
 * I suppose others should work too if you used them instead.
 */
public class CrpgGame extends AndroidGame
{
  /**
   * Returns the starting screen of the engine which is the splash screen.
   */
  public Screen getInitScreen()
  {
    return new LoadingScreen(this);
  }
}
