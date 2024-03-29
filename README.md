⚠️ This project is no longer maintained.

gson-immutable
==============

[![Build Status](https://travis-ci.org/dampcake/gson-immutable.svg?branch=master)](https://travis-ci.org/dampcake/gson-immutable)
[![Coverage Status](https://coveralls.io/repos/dampcake/gson-immutable/badge.svg?branch=master&service=github)](https://coveralls.io/github/dampcake/gson-immutable?branch=master)
[![Maven](https://img.shields.io/maven-central/v/com.dampcake/gson-immutable.svg)](http://search.maven.org/#search%7Cga%7C1%7Cgson-immutable)
[![GitHub license](https://img.shields.io/github/license/dampcake/gson-immutable.svg)](https://github.com/dampcake/gson-immutable/blob/master/LICENSE)

Gson TypeAdapters for Guava Immutable Objects.

Requires JDK 1.6 or higher (Guava)


## Javadoc
http://dampcake.github.io/gson-immutable

## Usage

### Maven
```xml
<dependency>
    <groupId>com.dampcake</groupId>
    <artifactId>gson-immutable</artifactId>
    <version>1.2</version>
</dependency>
```

### Gradle
```groovy
compile 'com.dampcake:gson-immutable:1.2'
```

### Examples

Register Guava Immutable interfaces:

```java
final Gson gson = new GsonBuilder().registerTypeAdapterFactory(ImmutableAdapterFactory.forGuava()).create();
```

Register Java interfaces:

```java
final Gson gson = new GsonBuilder().registerTypeAdapterFactory(ImmutableAdapterFactory.forJava()).create();
```

## Supported Types
Interface | Immutable Version | Supported?
--- | --- | ---
Collection | ImmutableCollection | :heavy_check_mark:
List | ImmutableList | :heavy_check_mark:
Set | ImmutableSet | :heavy_check_mark:
SortedSet | ImmutableSortedSet | :heavy_check_mark:
NavigableSet | ImmutableSortedSet | :heavy_check_mark:
Map | ImmutableMap | :heavy_check_mark:
SortedMap | ImmutableSortedMap | :heavy_check_mark:
