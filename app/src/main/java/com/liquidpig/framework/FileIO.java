package com.liquidpig.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.lang.StringBuffer;
import java.io.InputStreamReader;

import android.content.SharedPreferences;

/**
 * Service for managing file io and such fun things.
 */
public abstract class FileIO
{
  /**
   * Opens a file for reading.
   * @param file is the name of the file to open.
   * @return the input stream of the file.
   * @throws IOException when opening the file fucks up.
   */
  public abstract InputStream readFile(String file) throws IOException;

  /**
   * Opens a file for writing into.
   * @param file is the name of the file to open.
   * @return the outputstream for writing.
   * @throws IOException when opening the file fucks up.
   */
  public abstract OutputStream writeFile(String file) throws IOException;

  /**
   * Opens an asset for reading in.
   * @param file is the filename of the asset.
   * @return inputstream of the file to read.
   * @throws IOException when opening the file fucks up.
   */
  public abstract InputStream readAsset(String file) throws IOException;

  /**
   * not a clue. TODO: lets find out.
   */
  public abstract SharedPreferences getSharedPreferences();

  /**
   * Takes an InputStream and reads it into a string. This is real anoyying to do in java for some
   * god forsaken reason.
   * @param input is this input stream I told you about a second ago.
   * @return full string with line endings and everything.
   */
  public String readString(InputStream input) throws IOException
  {
    StringBuffer buffer = new StringBuffer();
    String line = "";
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    while ((line = reader.readLine()) != null) buffer.append(line + "\n" );
    return buffer.toString();
  }
}
