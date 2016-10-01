package com.segment.cartographer.benchmarks;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

// From https://github.com/RichardHightower/json-parsers-benchmark/tree/master/data
public final class Maps {
  private Maps() {
    throw new AssertionError("No instances.");
  }

  static final Map<String, Object> ACTION_LABEL;
  static final Map<String, Object> SMALL;

  static {
    ACTION_LABEL = ImmutableMap.<String, Object>builder()
        .put("menu", ImmutableMap.builder()
            .put("header", "SVG Viewer")
            .put("items", ImmutableList.builder()
                .add(ImmutableMap.builder().put("id", "Open"))
                .add(ImmutableMap.builder().put("id", "OpenNew").put("label", "Open New"))
                .add(ImmutableMap.builder().put("id", "ZoomIn").put("label", "Zoom In"))
                .add(ImmutableMap.builder().put("id", "ZoomOut").put("label", "Zoom Out"))
                .add(ImmutableMap.builder().put("id", "OriginalView").put("label", "Original View"))
                .add(ImmutableMap.builder().put("id", "Quality"))
                .add(ImmutableMap.builder().put("id", "Pause"))
                .add(ImmutableMap.builder().put("id", "Mute"))
                .add(ImmutableMap.builder().put("id", "Find").put("label", "Find..."))
                .add(ImmutableMap.builder().put("id", "FindAgain").put("label", "Find Again"))
                .add(ImmutableMap.builder().put("id", "Copy"))
                .add(ImmutableMap.builder().put("id", "CopyAgain").put("label", "Copy Again"))
                .add(ImmutableMap.builder().put("id", "CopySVG").put("label", "Copy SVG"))
                .add(ImmutableMap.builder().put("id", "ViewSVG").put("label", "View SVG"))
                .add(ImmutableMap.builder().put("id", "ViewSource").put("label", "View Source"))
                .add(ImmutableMap.builder().put("id", "SaveAs").put("label", "Save As"))
                .add(ImmutableMap.builder().put("id", "Help"))
                .add(ImmutableMap.builder()
                    .put("id", "About")
                    .put("label", "About Adobe CVG Viewer..."))
                .build())
            .build())
        .build();

    SMALL = ImmutableMap.<String, Object>builder().put("debug", "on\toff").put("num", 1).build();
  }
}
