#!/bin/bash

SRC=cartographer/src/main/java
BENCH=cartographer-benchmarks/src/main/java/com/segment/cartographer/benchmarks/Json*Benchmark.java

java -jar vogar.jar --benchmark --mode device --sourcepath $SRC $BENCH
