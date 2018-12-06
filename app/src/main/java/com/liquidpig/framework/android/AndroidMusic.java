package com.liquidpig.framework.android;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;

import com.liquidpig.framework.Music;

public class AndroidMusic implements Music, OnCompletionListener, OnSeekCompleteListener,
  OnVideoSizeChangedListener
{
  private MediaPlayer mediaPlayer;
  private boolean isPrepared = false;

  public AndroidMusic(AssetFileDescriptor assetDescriptor)
  {
    mediaPlayer = new MediaPlayer();
    try
    {
      mediaPlayer.setDataSource(
        assetDescriptor.getFileDescriptor(),
        assetDescriptor.getStartOffset(),
        assetDescriptor.getLength()
      );
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void dispose()
  {
    if (this.mediaPlayer.isPlaying()) this.mediaPlayer.stop();
    this.mediaPlayer.release();
  }

  @Override
  public boolean isLooping()
  {
    return mediaPlayer.isLooping();
  }

  @Override
  public boolean isPlaying()
  {
    return mediaPlayer.isPlaying();
  }

  @Override
  public boolean isStopped()
  {
    return !isPrepared;
  }

  @Override
  public void play()
  {
    if (this.mediaPlayer.isPlaying()) return;
    try
    {
      synchronized (this)
      {
        if (!isPrepared) mediaPlayer.prepare();
        mediaPlayer.start();
      }
    }
    catch (IllegalStateException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void stop()
  {
    if (this.mediaPlayer.isPlaying()) this.mediaPlayer.stop();
    synchronized (this)
    {
      isPrepared = false;
    }
  }

  @Override
  public void pause() {

  }

  @Override
  public void setLooping(boolean looping) {

  }

  @Override
  public void setVolume(float volume) {

  }

  @Override
  public void onCompletion(MediaPlayer player)
  {
    synchronized (this)
    {
      isPrepared = false;
    }
  }

  @Override
  public void seekBegin()
  {
    mediaPlayer.seekTo(0);
  }

  @Override
  public void onSeekComplete(MediaPlayer player)
  {
    // pass
  }

  @Override
  public void onVideoSizeChanged(MediaPlayer player, int width, int height)
  {
    // pass
  }




}
