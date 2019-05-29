package com.github.reugn.dynamic.test

import org.scalatest.{FlatSpec, Matchers}

class DynamicTest extends FlatSpec with Matchers {

    case class Foo(i: Int, s: String)

    case class Bar(f: Foo, b: Boolean)

    case class Baz(b: Bar, i1: Int, i2: Int, s2: String)

    behavior of "Dynamic"

    val i_field = "i"
    val new_int_field = 3
    val new_string_field = "new_str"

    it should "copy case class with dynamic key" in {
        import com.github.reugn.dynamic._
        val foo = Foo(1, "str")
        val copied_foo = copy(foo, i_field, new_int_field)
        copied_foo.i shouldBe 3
    }

    it should "throw MatchError on non existing key" in {
        import com.github.reugn.dynamic._
        val foo = Foo(1, "str")
        val non_existing_key = "_i"
        intercept[MatchError] {
            copy(foo, non_existing_key, new_int_field)
        }
    }

    it should "copy case class with compound dynamic Int key" in {
        import com.github.reugn.dynamic._
        val foo = Foo(1, "str")
        val bar = Bar(foo, b = false)
        val baz = Baz(bar, 4, 5, "str2")
        val copied_baz = copy(baz, "i", 3)
        copied_baz.b.f.i shouldBe 3
    }

    it should "copy case class with compound dynamic String key" in {
        import com.github.reugn.dynamic._
        val foo = Foo(1, "str")
        val bar = Bar(foo, b = false)
        val baz = Baz(bar, 4, 5, "str2")
        val copied_baz = copy(baz, "s", new_string_field)
        copied_baz.b.f.s shouldBe new_string_field
    }

    it should "copy case class with compound dynamic case class key" in {
        import com.github.reugn.dynamic._
        val foo = Foo(1, "str")
        val bar = Bar(foo, b = false)
        val baz = Baz(bar, 4, 5, "str2")

        val new_bar = Bar(Foo(10, "str10"), b = true)
        val copied_baz = copy(baz, "b", new_bar)
        copied_baz.b shouldBe new_bar
    }
}
