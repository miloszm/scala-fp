package com.sfp

import cats._
import cats.implicits._



/**
  * Applicative functor is a functor for applying a special value (value in category) to a lifted function
  */


object ApplicativeDemo extends App {

  /**
    * Will make MyContainer an Applicative
    */
  implicit object MyContainerApply extends Applicative[MyContainer] {

    /**
      * packs argument into a container
      * this is needed a starting value for chaining 'ap'
      */
    override def pure[A](x: A): MyContainer[A] = MyContainer(x)

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

  case class MyContainer[A](e:A)

  val s = Applicative[MyContainer].ap(MyContainer[(String)=>(String)](_.toLowerCase))(MyContainer[String]("AAA"))
  val ss = Applicative[List].ap(List[(String)=>(String)](_.toLowerCase))(List[String]("AAA"))

  assert(s == MyContainer("aaa"))
  assert(ss == List("aaa"))

  /**
    * Interesting example
    * Applicative allows partial application of a multi argument function
    * We have 2 argument function g
    * We apply it to 5
    * Then we apply it to 10
    * All is packed in and out automatically
    */
  val F = Applicative[Option]
  val g = (x: Int) => (y: Int) => x + y
  val f = ((F.ap(Option(g))(Option(5))))
  assert(F.ap(f)(Option(10)) == Some(15))
  /**
    * similarly
    */
  def f0 = (x: Int, y: Int) => x + y
  def f1(a: Option[Int], b: Option[Int]) = (a |@| b) map { f0 }
  assert(f1(Some(5), Some(10)) == Some(15))

  /**
    * Here we apply arguments to 3 argument function
    */
  val fun = (a:String) => (b:String) => (c:String) => a + b + c
  val pp = F.pure(fun)
  val gg = F.ap(pp)(Option("tic"))
  val hh = F.ap(gg)(Option("tac"))
  assert(F.ap(hh)(Option("toe")) == Some("tictactoe"))

  /**
    * similarly
    */
  def ff0 = (x: String, y: String, z:Int) => x + y + z.toString
  def ff1(a: Option[String], b: Option[String], c:Option[Int]) = (a |@| b |@| c) map { ff0 }
  assert(ff1(Some("tic"), Some("tac"), Some(10)) == Some("tictac10"))

}


