# dynamic
[![Build Status](https://travis-ci.org/reugn/dynamic.svg?branch=master)](https://travis-ci.org/reugn/dynamic)

A Scala library that allows copying a case class using a dynamic property name.

## Getting started
`dynamic` is available for the JVM Runtime using Scala 2.12, 2.13.

Build from source:
```sh
sbt clean +package
```

## Usage example
```scala
case class Foo(i: Int, s: String)
case class Bar(f: Foo, b: Boolean)
case class Baz(b: Bar, i1: Int, i2: Int, s2: String)

import com.github.reugn.dynamic._
val foo = Foo(1, "str")
val bar = Bar(foo, b = false)
val baz = Baz(bar, 4, 5, "str2")

val new_bar = Bar(Foo(10, "str10"), b = true)
val copied_baz = copy(baz, "b", new_bar)
copied_baz.b shouldBe new_bar
```

## License
Licensed under the [Apache 2.0 License](./LICENSE).
