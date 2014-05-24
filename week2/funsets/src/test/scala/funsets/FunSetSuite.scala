package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
  
  test("union of s1 with s1 contains 1") {
    new TestSets {
      val s = union(s1, s1)
      assert(contains(s, 1), "Union 1")
    }
  }
  
  test("intersection of s1 and s2 is empty") {
    new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Intersection 1")
      assert(!contains(s, 2), "Intersection 2")
    }
  }
  
  test("intersection of s1 and s1 is 1") {
    new TestSets {
      val s = intersect(s1, s1)
      assert(contains(s, 1), "Intersection 1")
    }
  }
  
  test("difference between s1 and s1 is empty") {
    new TestSets {
      val s = diff(s1, s1)
      assert(!contains(s, 1), "Difference 1")
    }
  }
  
  test("difference between s1 and s2 is 1") {
    new TestSets {
      val s = diff(s1, s2)
      assert(contains(s, 1), "Difference 1")
      assert(!contains(s, 2), "Difference 2")
    }
  }
  
  test("filter one") {
    new TestSets {
      val allElements = union(union(s1, s2), s3)
      val onlyOne = filter(allElements, x => x == 1)
      assert(contains(onlyOne, 1), "Filter 1")
      assert(!contains(onlyOne, 2), "Filter 2")
      assert(!contains(onlyOne, 3), "Filter 3")
    }
  }
  
  test("filter greater than zero") {
    new TestSets {
      val allElements = union(union(s1, s2), s3)
      val allGreater = filter(allElements, x => x > 0)
      assert(contains(allGreater, 1), "Filter 1")
      assert(contains(allGreater, 2), "Filter 2")
      assert(contains(allGreater, 3), "Filter 3")
    }
  }
  
  test("forall odd numbers") {
    new TestSets {
      val oddNumbers = union(s1, s3)
      assert(forall(oddNumbers, x => x % 2 == 1), "Forall odds in 1 and 3")
    }
  }
  
  test("forall even numbers fails on odd numbers") {
    new TestSets {
      val oddNumbers = union(s1, s3)
      assert(!forall(oddNumbers, x => x % 2 == 0), "Forall evens in 1 and 3")
    }
  }
  
  test("exists even numbers fails on odd numbers") {
    new TestSets {
      val oddNumbers = union(s1, s3)
      assert(!exists(oddNumbers, x => x % 2 == 0), "exists evens in 1 and 3")
    }
  }
  
  test("exists even numbers succeeds when 2 exists") {
    new TestSets {
      val allNumbers = union(union(s1, s3), s2)
      assert(exists(allNumbers, x => x % 2 == 0), "exists evens in 1, 2 and 3")
    }
  }
  
  test("map double") {
    new TestSets {
      val allNumbers = union(union(s1, s2), s3)
      assert(!contains(map(allNumbers, x => x * 2), 1), "1 doesn't exist in the doubles map")
      assert(contains(map(allNumbers, x => x * 2), 2), "2 exists in the doubles map")
      assert(!contains(map(allNumbers, x => x * 2), 3), "3 doesn't exist in the doubles map")
      assert(contains(map(allNumbers, x => x * 2), 4), "4 exists in the doubles map")
      assert(contains(map(allNumbers, x => x * 2), 6), "6 exists in the doubles map")
    }
  }
}
