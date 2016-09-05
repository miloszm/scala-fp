package com.sfp

import cats._


object ApplyDemo extends App {

  case class MyContainer[A](e: A)

  /**
    * Will make MyContainer an Apply
    */
  implicit object MyContainerApply extends Apply[MyContainer] {
    /**
      * extracts function from a container, and argument from another container,
      * applies the function and packs the result again into a container
      */
    override def ap[A, B](ff: MyContainer[(A) => B])(fa: MyContainer[A]): MyContainer[B] = MyContainer(ff.e(fa.e))

    /**
      * for comparison, functor takes function and argument in a container,
      * applies function and the result again into a container
      */
    override def map[A, B](fa: MyContainer[A])(f: (A) => B): MyContainer[B] = MyContainer(f(fa.e))
  }

  def myContainer:Boolean = {
    val s = Apply[MyContainer].ap(MyContainer[(Int) => (Int)](_ + 3))(MyContainer[Int](2))
    (s == MyContainer(5))
  }


  /**
    * if we have function of arity n
    * we can lift it so that arguments are functors
    * and response is wrapped in a function
    */
  def applyDemoArity3:Boolean = {
    import cats.implicits._
    def f0(a: Int, b: Int, c: Int) = a + b + c + 2
    def f1(a: Option[Int], b: Option[Int], c: Option[Int]) =
      (a |@| b |@| c) map {
        f0
      }
    (f1(Some(1), Some(2), Some(3)) == Some(8)) &&
    (f1(Some(1), None, Some(3)) == None)
  }

  /**
    * once again for arity 4
    */
  def applyDemoArity4:Boolean = {
    import cats.implicits._
    def f0(a: Int, b: Int, c: Int, d: String): String = "as string: " + a + b + c + d
    def f1(a: Option[Int], b: Option[Int], c: Option[Int], d: Option[String]): Option[String] =
      (a |@| b |@| c |@| d) map {
        f0
      }
    (f1(Some(1), Some(2), Some(3), Some("4")) == Some("as string: 1234")) &&
    (f1(Some(1), None, Some(3), Some("4")) == None)
  }

  myContainer
  applyDemoArity3
  applyDemoArity4
}


