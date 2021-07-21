package io.github.reugn

import scala.language.experimental.macros

package object dynamic {

    /**
      * Copies a case class using a dynamic property name.
      * Searches for the specified field name recursively.
      *
      * {{{
      *     import io.github.reugn.dynamic._
      *     case class Foo(i: Int, s: String)
      *     val foo = Foo(1, "str")
      *     val copied_foo = copy(foo, "i", 2)
      * }}}
      *
      * @param inst the case class instance to copy from.
      * @param property the property name with which the specified value is to be associated.
      * @param value the value to be associated with the specified property name.
      * @tparam T the type of the case class.
      * @throws scala.MatchError on non existing field.
      * @return T a shallow copy of the class instance with the updated property.
      */
    @throws(classOf[MatchError])
    def copy[T](inst: T, property: String, value: Any): T = macro DynamicMacros.copyImpl[T]
}
