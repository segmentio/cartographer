package com.segment.cartographer.benchmarks;

import com.google.caliper.Param;
import com.google.caliper.SimpleBenchmark;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.segment.cartographer.Cartographer;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import org.json.JSONObject;

public class JsonDecodingBenchmark extends SimpleBenchmark {

  @Param(value = { "10", "50", "100", "1000" }) int length;

  StringReader reader;
  String json;

  @Override protected void setUp() throws Exception {
    super.setUp();
    Map<String, Object> map = new LinkedHashMap<String, Object>();

    char[] chars = new char[20];
    Random dice = new Random(0);
    for (int i = 0; i < chars.length; i++) {
      chars[i] = (char) dice.nextInt(128);
    }
    String data = new String(chars);
    for (int i = 0; i < length; i++) {
      map.put(data, data);
    }

    json = new Cartographer.Builder().build().toJson(map);
    reader = new StringReader(json);
  }

  public void timeCartographer(int reps) throws IOException {
    Cartographer cartographer = new Cartographer.Builder().build();

    for (int i = 0; i < reps; i++) {
      cartographer.fromJson(reader);
    }
  }

  public void timeGson(int reps) throws IOException {
    Gson gson = new Gson();
    Type type = new TypeToken<Map<String, Object>>() {
    }.getType();

    for (int i = 0; i < reps; i++) {
      gson.fromJson(reader, type);
    }
  }

  public void timeJSONObject(int reps) throws Exception {
    for (int i = 0; i < reps; i++) {
      JSONObject jsonObject = new JSONObject(json);
    }
  }
}
