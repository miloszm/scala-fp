package com.sfp

import cats._
import cats.implicits._

object SemigroupDemo extends App {
  println(Semigroup[Option[String]].combine(Some("fizz"), Some("buzz")))
  println(Semigroup[Option[String]].combine(Some("fizz"), None))
  println(Semigroup[Option[String]].combine(None, Some("buzz")))
  println(Semigroup[Option[String]].combine(None, None))

  println(Option("fizz").combine(Some("buzz")))
  println(Option("fizz").combine(None))
  println((None:Option[String]).combine(Some("buzz")))
  println((None:Option[String]).combine(None))

  println(Option("fizz") |+| Some("buzz"))
  println(Option("fizz") |+| None)
  println((None:Option[String]) |+| Some("buzz"))
  println((None:Option[String]) |+| None)
}

