package de.funding.funding.entity;

import java.io.IOException;
import java.io.InputStream;

public interface Image {
  InputStream getStream() throws IOException;
  String getMimeType();
}
