package com.segment.cartographer.benchmarks;

import com.google.caliper.Param;
import com.google.caliper.SimpleBenchmark;
import com.segment.cartographer.Cartographer;
import java.io.IOException;
import org.json.JSONObject;

public class JsonDecodingBenchmark extends SimpleBenchmark {

  @Param(value = { "small", "action_label" }) String fixture;
  String json;
  int blackHole;

  @Override protected void setUp() throws Exception {
    super.setUp();

    json = jsonFor(fixture);
    blackHole = 0;
  }

  static String jsonFor(String name) {
    if (name.equals("small")) return Json.SMALL;
    if (name.equals("action_label")) return Json.ACTION_LABEL;
    throw new IllegalArgumentException("unknown name: " + name);
  }

  public int timeCartographer(int reps) throws IOException {
    Cartographer cartographer = new Cartographer.Builder().build();

    for (int i = 0; i < reps; i++) {
      blackHole += cartographer.fromJson(json).size();
    }

    return blackHole;
  }

  /*
  public int timeGson(int reps) throws IOException {
    Gson gson = new Gson();
    Type type = new TypeToken<Map<String, Object>>() {
    }.getType();

    for (int i = 0; i < reps; i++) {
      Map<String, Object> map = gson.fromJson(json, type);
      blackHole += map.size();
    }

    return blackHole;
  }
  */

  public int timeJSONObject(int reps) throws Exception {
    for (int i = 0; i < reps; i++) {
      blackHole += new JSONObject(json).length();
    }

    return blackHole;
  }
}
