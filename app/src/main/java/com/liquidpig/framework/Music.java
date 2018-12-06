package com.liquidpig.framework;

/**
 * A music stream.
 */
public interface Music
{
  /**
   * Makes the stream start playing.
   */
  public void play();

  /**
   * Makes the stream stop playing.
   */
  public void stop();

  /**
   * Makes the stream stop playing but saves the position.
   */
  public void pause();

  /**
   * Sets whether the stream should loop.
   * @param looping is whether it should loop.
   */
  public void setLooping(boolean looping);

  /**
   * Sets the volume of the stream.
   * @param volume is the volume to set it to.
   */
  public void setVolume(float volume);

  /**
   * Tells you if the stream is playing.
   * @return true iff the stream is currently playing.
   */
  public boolean isPlaying();

  /**
   * Tells you if the music is stopped. Meaning not playing or paused.
   * @return true iff the music is stopped.
   */
  public boolean isStopped();

  /**
   * Tells you if the stream is currently looping.
   * @return true iff the stream is looping.
   */
  public boolean isLooping();

  /**
   * Destroys the stream and it's data and everything.
   */
  public void dispose();

  /**
   * Sends the stream back to the beginning.
   * TODO: why not make this seek to any point?
   */
  public void seekBegin();
}
