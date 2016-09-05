package com.sfp

import cats.implicits._
import cats.kernel.Monoid

object MonoidDemo extends App {

  class MyMonoid extends Monoid[Int] {
    override def empty: Int = 1
    override def combine(x: Int, y: Int): Int = x * y
  }

  class MyStringMonoid extends Monoid[String] {
    override def empty: String = ""
    override def combine(x: String, y: String): String = x + "|" + y
  }

  def tryFoldMap: Boolean = {
    val l = List(1, 2, 3, 4, 5)
    val m = new MyMonoid
    val ms = new MyStringMonoid
    (l.foldMap(i => i.toString)(ms) == "|1|2|3|4|5") &&
    (l.foldMap(identity)(m) == 1*2*3*4*5)
  }

  def tryCombineAll: Boolean = {
    val l = List(1, 2, 3, 4, 5)
    val m = new MyMonoid
    l.combineAll(m) == 120
  }

  assert(tryFoldMap)
  assert(tryCombineAll)
}
