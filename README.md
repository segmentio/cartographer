Cartographer
--------------

An Android library to convert between JSON encoded strings and plain old Java Maps.


Usage
-----

* Create an instance of a Cartographer object.
```java
Cartographer cartographer = new Cartographer.Builder().setLenient(true|false).build();
```

* Encode Java Maps
```java
String json = cartographer.toJson(map);
// OR
cartographer.toJson(map, writer);
```

* Decode Json input
```java
Map<String, Object> map = cartographer.fromJson(json);
// OR
Map<String, Object> map = cartographer.fromJson(reader);
```


Download
--------

Download [the latest JAR][2] or grab via Maven:

```xml
<dependency>
  <groupId>com.segment.cartographer</groupId>
  <artifactId>cartographer</artifactId>
  <version>1.0.0</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.segment.cartographer:cartographer:1.0.0'
```


License
-------

```
WWWWWW||WWWWWW
 W W W||W W W
      ||
    ( OO )__________
     /  |           \
    /o o|    MIT     \
    \___/||_||__||_|| *
         || ||  || ||
        _||_|| _||_||
       (__|__|(__|__|

The MIT License (MIT)

Copyright (c) 2014 Segment, Inc.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```


 [1]: http://github.com/segmentio/cartographer
 [2]: http://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.segment.cartographer&a=cartographer&v=LATEST
