package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * This class is thread safe.
 */
public class ParserFacade {

  public static final String EMPTY_CONTENT = "";

  public String read(File file) throws IOException {
    return read(file, false);
  }

  public String read(File file, boolean skipUnicode) throws IOException {
    FileInputStream i = new FileInputStream(file);
    String output = EMPTY_CONTENT;
    int data;
    while ((data = i.read()) != -1) {
      if ((Character.isUnicodeIdentifierStart(data) && skipUnicode)) {
        continue;
      }
      output += (char) data;
    }
    return output;
  }

  public void save(File file, String content) throws IOException {
    FileOutputStream stream = new FileOutputStream(file);
    FileChannel channel = stream.getChannel();
    FileLock fileLock = null;
    try {
      fileLock = channel.tryLock();
      stream.write(content.getBytes());
    } catch (IOException e) {
      stream.close();
      channel.close();
      throw new IOException("File " + file.getAbsolutePath() + " is locked for writing");
    }
    fileLock.close();
    stream.close();
    channel.close();
  }
}