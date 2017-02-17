package com.segment.cartographer.benchmarks;

import com.google.caliper.Param;
import com.google.caliper.SimpleBenchmark;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.segment.cartographer.Cartographer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import org.json.JSONObject;

public class JsonEncodingBenchmark extends SimpleBenchmark {

  @Param(value = {"small", "action_label"}) String fixture;
  Map<String, ?> map;
  String blackHole;

  @Override protected void setUp() throws Exception {
    super.setUp();

    map = mapFor(fixture);
    blackHole = "";
  }

  static Map<String, ?> mapFor(String name) {
    if ("small".equals(name)) {
      return Maps.SMALL;
    }
    if ("action_label".equals(name)) {
      return Maps.ACTION_LABEL;
    }
    throw new IllegalArgumentException("unknown name: " + name);
  }

  public String timeCartographer(int reps) throws IOException {
    Cartographer cartographer = new Cartographer.Builder().build();

    for (int i = 0; i < reps; i++) {
      blackHole = blackHole + cartographer.toJson(map);
    }

    return blackHole;
  }

  public String timeGson(int reps) throws IOException {
    Gson gson = new Gson();
    Type type = new TypeToken<Map<String, Object>>() {
    }.getType();

    for (int i = 0; i < reps; i++) {
      blackHole = blackHole + gson.toJson(map, type);
    }

    return blackHole;
  }

  public String timeJSONObject(int reps) throws IOException {
    JSONObject jsonObject = new JSONObject(map);

    for (int i = 0; i < reps; i++) {
      blackHole = blackHole + jsonObject.toString();
    }

    return blackHole;
  }
}
