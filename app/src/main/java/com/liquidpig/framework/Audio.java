package com.liquidpig.framework;

/**
 * Service for loading music and sound files.
 */
public interface Audio
{
  /**
   * Creates a music stream from a file.
   * @param file is the filename to load the music from.
   * @return the new music.
   */
  public Music createMusic(String file);

  /**
   * Creates a sound from a file.
   * @param file is the filename to load the sound from.
   * @return the new sound.
   */
  public Sound createSound(String file);
}
