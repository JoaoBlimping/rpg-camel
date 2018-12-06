package com.liquidpig.framework.android;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.liquidpig.framework.Audio;
import com.liquidpig.framework.Music;
import com.liquidpig.framework.Sound;

/**
 * Does audio playing for android.
 */
public class AndroidAudio implements Audio
{
  AssetManager assets;
  SoundPool soundPool;

  /**
   * Creates the audio thingo and gives it the activity thing whateber that is.
   * @param activity is the activity thing.
   */
  public AndroidAudio(Activity activity)
  {
    activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    this.assets = activity.getAssets();
    this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
  }

  @Override
  public Music createMusic(String filename)
  {
    try
    {
      AssetFileDescriptor assetDescriptor = assets.openFd(filename);
      return new AndroidMusic(assetDescriptor);
    }
    catch (IOException e)
    {
      throw new RuntimeException("Couldn't load music music "+filename);
    }
  }

  @Override
  public Sound createSound(String filename)
  {
    try
    {
      AssetFileDescriptor assetDescriptor = assets.openFd(filename);
      int soundId = soundPool.load(assetDescriptor, 0);
      return new AndroidSound(soundPool, soundId);
    }
    catch (IOException e)
    {
      throw new RuntimeException("couldn't load sound "+filename);
    }
  }
}
