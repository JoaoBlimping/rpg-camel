package com.liquidpig.framework;

public interface Sound
{
  /**
   * Plays the sound.
   * @param volume is the volume level to play it at.
   */
  public void play(float volume);

  /**
   * Destroys the sound and all it's data.
   */
  public void dispose();
}
