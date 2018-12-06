package com.liquidpig.framework;

/**
 * Stores the whole set of services required for a game, and stores game
 * state.
 */
public interface Game
{
  /**
   * Gives you the game's audio service.
   * @return Audio the audio service.
   */
  public Audio getAudio();

  /**
   * Gives you the game's input service.
   * @return Input the input service.
   */
  public Input getInput();

  /**
   * Gives you the game's fileio service.
   * @return FileIO the fileio service.
   */
  public FileIO getFileIO();

  /**
   * Gives you the game's graphics service.
   * @return Graphics the graphics service.
   */
  public Graphics getGraphics();

  /**
   * Pauses the currently running screen and places a new screen onto the stack.
   * @param screen is the new screen to be set as the game's current screen.
   */
  public void pushScreen(Screen screen);

  /**
   * Removes and destroys the currently running screen and unpauses the previously running screen.
   */
  public void popScreen();

  /**
   *  Gets rid of the current screen and puts a new one in it's place with no stack stuff going on.
   * @param screen is the new screen.
   */
  public void replaceScreen(Screen screen);
  /**
   * Gives you the game's current screen.
   * @return Screen the current screen.
   */
  public Screen getCurrentScreen();

  /**
   * Gives you the game's startup screen.
   * @return Screen the startup screen.
   */
  public Screen getInitScreen();
}
