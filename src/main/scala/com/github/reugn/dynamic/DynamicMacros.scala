package com.github.reugn.dynamic

import scala.language.experimental.macros
import scala.language.postfixOps
import scala.reflect.macros.blackbox

object DynamicMacros {

    def copyImpl[T: c.WeakTypeTag](c: blackbox.Context)(inst: c.Tree, property: c.Tree,
                                                        value: c.Tree): c.universe.Tree = {
        import c.universe._
        val sym = c.symbolOf[T]
        if (!sym.asClass.isCaseClass) {
            c.abort(c.enclosingPosition, s"${sym.fullName} is not a case class")
        }

        def asMap(tpe: c.Type): Map[String, c.Symbol] = {
            tpe.decls.collect {
                case m: TermSymbol if m.isVal => m
            } map {
                s =>
                    s.name.toString.trim -> s.typeSignature.typeSymbol
            } toMap
        }

        def recursiveFields(prefix: String, obj: c.Type): String = {
            val map = asMap(obj)
            map.toList map {
                e =>
                    val path = buildPath(prefix, e._1)
                    val rec = if (e._2.asClass.isCaseClass)
                        recursiveFields(path, e._2.asType.toType)
                    else
                        ""
                    if (e._2.asType.toType == value.tpe.typeSymbol.asType.toType)
                        s"""
                           |    case "${e._1}" =>
                           |        ${show(inst)}.modify(_.$path).setTo($value)
                        """.stripMargin + rec
                    else
                        rec
            } mkString
        }

        def strKey(k: c.Tree): String = k match {
            case Literal(Constant(str: String)) => s""""$str""""
            case _ => k.toString
        }

        val importLens = q"import com.softwaremill.quicklens._"
        val cond =
            s"""
               |${strKey(property)} match {
               |    ${recursiveFields("", inst.tpe)}
               |}
             """.stripMargin
        q"""
            $importLens
            ${c.parse(cond)}
        """
    }

    private def buildPath(prefix: String, key: String): String = {
        if (prefix.isEmpty) key else s"$prefix.$key"
    }

}
