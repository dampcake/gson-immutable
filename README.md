gson-immutable
==============

[![Build Status](https://travis-ci.org/dampcake/gson-immutable.svg?branch=master)](https://travis-ci.org/dampcake/gson-immutable)

Gson TypeAdapters for Guava Immutable Objects.

Requires JDK 1.6 or higher (Guava)

Examples
=======
Register Guava Immutable interfaces:

```java
final Gson gson = new GsonBuilder().registerTypeAdapterFactory(ImmutableAdapterFactory.forGuava()).create();
```

Register Java interfaces:

```java
final Gson gson = new GsonBuilder().registerTypeAdapterFactory(ImmutableAdapterFactory.forInterfaces()).create();
```

Supported Types
============
* ImmutableList (List)
* ImmutableSet (Set)
* ImmutableSortedSet (SortedSet)
* ImmutableMap (Map)
* ImmutableSortedMap (SortedMap) 