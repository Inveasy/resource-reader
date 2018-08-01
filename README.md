# Error Management
[![Build Status](https://travis-ci.org/Inveasy/resource-reader.svg?branch=master)](https://travis-ci.org/Inveasy/resource-reader)
[![Quality gate status](https://sonarcloud.io/api/project_badges/measure?project=io.inveasy%3Aresource-reader&metric=alert_status)](https://sonarcloud.io/dashboard?id=io.inveasy%3Aresource-reader)
[![Download](https://api.bintray.com/packages/inveasy/maven/resource-reader/images/download.svg) ](https://bintray.com/inveasy/maven/resource-reader/_latestVersion)

## What is it ?
This project provides a helper class to read resource files on the classpath in a convenient manner.

## How To
First, include the maven dependency in your build :

```xml
<dependency>
  <groupId>io.inveasy</groupId>
  <artifactId>resource-reader</artifactId>
  <version>1.0.0</version>
</dependency>

<repositories>
  <repository>
    <id>bintray-inveasy-maven</id>
    <name>inveasy-maven</name>
    <url>https://dl.bintray.com/inveasy/maven</url>
  </repository>
</repositories>
```

Then, create an instance of the helper class, providing it the path/name of the file on the classpath,
and how many elements in a line (separated by spaces) it should find :
```java
// In this case, it will read file "file" located in "classpath://path/to/file", expecting 2 elements per lines
ResourceReader reader = new ResourceReader("path/to/file", 2);
```

Now, you can use it to retrieve lines of the file :
```java
List<String[]> lines = reader.getSplittedLines()
```
Each line will contain the elements separated by spaces.

## Where is it used in Inveasy platform ?
This helper is used when resource files are generated compile-time by annotation processors and are needed runtime,
such as in the akka-http module, where workers are read from file.