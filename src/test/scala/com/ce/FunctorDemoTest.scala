package com.ce

import com.sfp.ApplyDemo
import org.scalatest.{FlatSpec, Matchers}


class ApplyDemoTest extends FlatSpec with Matchers {

  "apply demo" should  "pass for my container" in {
    ApplyDemo.myContainer should be (true)
  }

  "apply demo" should  "pass for arity 3" in {
    ApplyDemo.applyDemoArity3 should be (true)
  }

  "apply demo" should  "pass for arity 4" in {
    ApplyDemo.applyDemoArity4 should be (true)
  }

}
