package com.github.reugn

import scala.language.experimental.macros

package object dynamic {

    /**
      * Copies case class using dynamic field name
      * Searches for key recursively
      *
      * {{{
      *     import reug.dynamic._
      *     case class Foo(i: Int, s: String)
      *     val foo = Foo(1, "str")
      *     val copied_foo = copy(foo, "i", 2)
      * }}}
      *
      * @param inst case class instance to copy from
      * @param key  dynamic field name
      * @param nval new value object
      * @tparam T case class type
      * @throws scala.MatchError on non existing key
      * @return T copy result
      */
    @throws(classOf[MatchError])
    def copy[T](inst: T, key: String, nval: Any): T = macro DynamicMacros.copyImpl[T]
}
