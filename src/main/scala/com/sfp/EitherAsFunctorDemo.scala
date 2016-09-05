package com.sfp

import cats._
import cats.instances.all._
import cats.syntax.functor._



/**
  * from http://eed3si9n.com/herding-cats/Functor.html
  *
  *
  * Functor is a typeclass for things that can be mapped over
  *
  *
  * almost like the map method on Scala collection library, except this map doesn’t do the CanBuildFrom auto conversion
  *
  */

object EitherAsFunctorDemo extends App {

  /**
    * Even though Either does not implement map
    * it is a Functor thanks to cats functor instances:
    * implicit def catsStdInstancesForEither[A]
    */
  val r = (Right(1): Either[String, Int]) map { _ + 1 }
  println(r)


}


object MyContainerAsFunctorDemo extends App {
  /**
    * Will make MyContainer a Functor
    */
  implicit object MyContainerFunctor extends Functor[MyContainer] {
    override def map[A, B](fa: MyContainer[A])(f: (A) => B): MyContainer[B] = MyContainer(f(fa.e))
  }

  case class MyContainer[T](e:T)

  val s = Functor[MyContainer].map(MyContainer(1)) { _ + 1 }
  val ss = MyContainer[Int](2) map { _ + 1 }

  println(s)
  println(ss)
}


object FunctionAsFunctorDemo extends App {
  /**
    * even functions can be mapped over
    * this is like a function composition
    */
  val h = ((x: Int) => x + 1) map {_ * 8}
  assert(h(4) == 40)
}
