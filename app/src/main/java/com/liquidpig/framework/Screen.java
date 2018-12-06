package com.liquidpig.framework;

/**
 * Represents a single game screen for example the menu or the game overworld.
 */
public abstract class Screen
{
  protected final Game game;

  /**
   * Creates a screen and gives it access to the game services.
   * @param Game game is the game services object.
   */
  public Screen(Game game)
  {
    this.game = game;
  }

  /**
   * Updates the state in the screen by the given amount of time.
   * @param float delta is the number of seconds that have passed since last time.
   */
  public abstract void update(float delta);

  /**
   * Renders the screen to the actual screen.
   * @param float delta is the number of seconds that have passed since last time.
   * NOTE: I don't get why we pass delta to paint as well, seems weird.
   */
  public abstract void paint(float delta);

  /**
   * Pause the screen.
   */
  public abstract void pause();

  /**
   * Unpause the screen.
   */
  public abstract void resume();

  /**
   * Destoy the scene and everything in it so it can get garbage collected and shiet.
   */
  public abstract void dispose();

  /**
   * Called upon the backbutton being pressed.
   */
  public abstract void backButton();

}
