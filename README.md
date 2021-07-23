# dynamic
[![Build](https://github.com/reugn/dynamic/actions/workflows/build.yml/badge.svg)](https://github.com/reugn/dynamic/actions/workflows/build.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.reugn/dynamic_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.reugn/dynamic_2.12/)

A Scala library that allows copying a case class using a dynamic property name.

## Getting started
`dynamic` is available for the JVM Runtime using Scala 2.12, 2.13.

Add as a dependency:
```scala
libraryDependencies += "io.github.reugn" %% "dynamic" % version
```

Build from source:
```sh
sbt clean +package
```

## Usage example
```scala
case class Foo(i: Int, s: String)
case class Bar(f: Foo, b: Boolean)
case class Baz(b: Bar, i1: Int, i2: Int, s2: String)

import io.github.reugn.dynamic.copy
val foo = Foo(1, "str")
val bar = Bar(foo, b = false)
val baz = Baz(bar, 4, 5, "str2")

val new_bar = Bar(Foo(10, "str10"), b = true)
val copied_baz = copy(baz, "b", new_bar)
copied_baz.b shouldBe new_bar
```

## License
Licensed under the [Apache 2.0 License](./LICENSE).
