package com.segment.cartographer;

import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.assertj.core.data.MapEntry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE)
public class CartographerTest {
  Cartographer cartographer;

  @Before public void setUp() {
    cartographer = new Cartographer.Builder().build();
  }

  @Test public void encodesPrimitives() throws IOException {
    Map<String, Object> map = ImmutableMap.<String, Object>builder()
        .put("byte", (byte) 32)
        .put("boolean", true)
        .put("short", (short) 100)
        .put("int", 1)
        .put("long", 43L)
        .put("float", 23f)
        .put("double", Math.PI)
        .put("char", 'a')
        .put("String", "string")
        .build();

    assertThat(cartographer.toJson(map)).isEqualTo("{"
        + "\"byte\":32,"
        + "\"boolean\":true,"
        + "\"short\":100,"
        + "\"int\":1,"
        + "\"long\":43,"
        + "\"float\":23.0,"
        + "\"double\":3.141592653589793,"
        + "\"char\":\"a\","
        + "\"String\":\"string\""
        + "}");
  }

  @Test public void decodesPrimitives() throws IOException {
    String json = "{"
        + "\"byte\":32,"
        + "\"boolean\":true,"
        + "\"short\":100,"
        + "\"int\":1,"
        + "\"long\":43,"
        + "\"float\":23.0,"
        + "\"double\":3.141592653589793,"
        + "\"char\":\"a\","
        + "\"String\":\"string\""
        + "}";

    Map<String, Object> map = cartographer.fromJson(json);

    assertThat(map).hasSize(9)
        .contains(MapEntry.entry("byte", 32.0))
        .contains(MapEntry.entry("boolean", true))
        .contains(MapEntry.entry("short", 100.0))
        .contains(MapEntry.entry("int", 1.0))
        .contains(MapEntry.entry("long", 43.0))
        .contains(MapEntry.entry("float", 23.0))
        .contains(MapEntry.entry("double", Math.PI))
        .contains(MapEntry.entry("char", "a"))
        .contains(MapEntry.entry("String", "string"));
  }

  @Test public void disallowsEncodingNullMap() throws IOException {
    try {
      cartographer.toJson(null, new StringWriter());
      fail("null map should throw Exception");
    } catch (IllegalArgumentException e) {
      assertThat(e).hasMessage("map == null");
    }
  }

  @Test public void disallowsEncodingToNullWriter() throws IOException {
    try {
      cartographer.toJson(new LinkedHashMap<Object, Object>(), null);
      fail("null writer should throw Exception");
    } catch (IllegalArgumentException e) {
      assertThat(e).hasMessage("writer == null");
    }
  }

  @Test public void disallowsDecodingNullReader() throws IOException {
    try {
      cartographer.fromJson((Reader) null);
      fail("null map should throw Exception");
    } catch (IllegalArgumentException e) {
      assertThat(e).hasMessage("reader == null");
    }
  }

  @Test public void disallowsDecodingNullString() throws IOException {
    try {
      cartographer.fromJson((String) null);
      fail("null map should throw Exception");
    } catch (IllegalArgumentException e) {
      assertThat(e).hasMessage("json == null");
    }
  }

  @Test public void disallowsDecodingEmptyString() throws IOException {
    try {
      cartographer.fromJson("");
      fail("null map should throw Exception");
    } catch (IllegalArgumentException e) {
      assertThat(e).hasMessage("json empty");
    }
  }

  @Test public void encodesNumberMax() throws IOException {
    StringWriter writer = new StringWriter();
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("byte", Byte.MAX_VALUE);
    map.put("short", Short.MAX_VALUE);
    map.put("int", Integer.MAX_VALUE);
    map.put("long", Long.MAX_VALUE);
    map.put("float", Float.MAX_VALUE);
    map.put("double", Double.MAX_VALUE);
    map.put("char", Character.MAX_VALUE);

    cartographer.toJson(map, writer);

    assertThat(writer.toString()).isEqualTo("{"
        + "\"byte\":127,"
        + "\"short\":32767,"
        + "\"int\":2147483647,"
        + "\"long\":9223372036854775807,"
        + "\"float\":3.4028235E38,"
        + "\"double\":1.7976931348623157E308,"
        + "\"char\":\"\uFFFF\""
        + "}");
  }

  @Test public void encodesNumberMin() throws IOException {
    StringWriter writer = new StringWriter();
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("byte", Byte.MIN_VALUE);
    map.put("short", Short.MIN_VALUE);
    map.put("int", Integer.MIN_VALUE);
    map.put("long", Long.MIN_VALUE);
    map.put("float", Float.MIN_VALUE);
    map.put("double", Double.MIN_VALUE);
    map.put("char", Character.MIN_VALUE);

    cartographer.toJson(map, writer);

    assertThat(writer.toString()).isEqualTo("{"
        + "\"byte\":-128,"
        + "\"short\":-32768,"
        + "\"int\":-2147483648,"
        + "\"long\":-9223372036854775808,"
        + "\"float\":1.4E-45,"
        + "\"double\":4.9E-324,"
        + "\"char\":\"\\u0000\""
        + "}");
  }

  @Test public void encodesLargeDocuments() throws IOException {
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    for (int i = 0; i < 100; i++) {
      map.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }
    StringWriter writer = new StringWriter();
    cartographer.toJson(map, writer);
  }
}
