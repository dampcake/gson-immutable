gson-immutable
==============

[![Build Status](https://travis-ci.org/dampcake/gson-immutable.svg?branch=master)](https://travis-ci.org/dampcake/gson-immutable)
[![Coverage Status](https://coveralls.io/repos/dampcake/gson-immutable/badge.svg?branch=master&service=github)](https://coveralls.io/github/dampcake/gson-immutable?branch=master)

Gson TypeAdapters for Guava Immutable Objects.

Requires JDK 1.6 or higher (Guava)

[Javadoc](http://dampcake.github.io/bencode/)

Examples
=======
Register Guava Immutable interfaces:

```java
final Gson gson = new GsonBuilder().registerTypeAdapterFactory(ImmutableAdapterFactory.forGuava()).create();
```

Register Java interfaces:

```java
final Gson gson = new GsonBuilder().registerTypeAdapterFactory(ImmutableAdapterFactory.forJava()).create();
```

Supported Types
============
* ImmutableCollection (Collection)
* ImmutableList (List)
* ImmutableSet (Set)
* ImmutableSortedSet (SortedSet)
* ImmutableMap (Map)
* ImmutableSortedMap (SortedMap) 