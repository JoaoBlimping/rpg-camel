package com.liquidpig.framework.android;

import android.media.SoundPool;

import com.liquidpig.framework.Sound;

/**
 * A sound for playing on android.
 */
public class AndroidSound implements Sound
{
  int soundId;
  SoundPool soundPool;

  /**
   * Creates the sound with the soundpool and it's id.
   * @param soundPool is the soundpool it goes in.
   * @param soundId is the id number of this sound.
   */
  public AndroidSound(SoundPool soundPool, int soundId)
  {
    this.soundId = soundId;
    this.soundPool = soundPool;
  }

  @Override
  public void play(float volume)
  {
    soundPool.play(soundId, volume, volume, 0, 0, 1);
  }

  @Override
  public void dispose()
  {
    soundPool.unload(soundId);
  }
}
