package com.tiernebre.util.pagination;

import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public final class Base64CursorMapper implements CursorMapper {

  private final Encoder encoder;
  private final Decoder decoder;

  public Base64CursorMapper(Encoder encoder, Decoder decoder) {
    this.encoder = encoder;
    this.decoder = decoder;
  }

  @Override
  public String idToCursor(long id) {
    return encoder.encodeToString(Long.toBinaryString(id).getBytes());
  }

  @Override
  public long cursorToId(String cursor) {
    return Long.parseLong(new String(decoder.decode(cursor)), 2);
  }
}
