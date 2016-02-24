package section1

import org.scalatest.FunSuite

class FuncSetSpec extends FunSuite {

  import FuncSet._

  val a: Set = singleSet(10)
  val b: Set = singleSet(15)
  val c: Set = singleSet(-150)


  test("contains works with a every time true example Set") {
    val s: Set = x => true
    assert( contains(s, 4711) === true )
  }

  test("Set 'a' contains 10") {
    assert( contains(a, 10) === true)
  }

  test("Set 'a' does not contain -5") {
    assert( contains(a, -5) === false)
  }

  test("union of 'a' and 'b' contains both") {
    val s: Set = union(a, b)
    assert( contains(s, 10) === true)
    assert( contains(s, 15) === true)
  }

  test("union of 'a' and 'b' does not contain 'c'") {
    val s: Set = union(a, b)
    assert( contains(s, -150) === false)
  }

  test("intersect should only contain the elements, which are in both sets") {
    //(10, 15, 30)
    val s1: Set = union(union(a, b), singleSet(30))
    //(10, 30)
    val s2: Set = union(a, singleSet(30))

    val s: Set = intersect(s1, s2)
    assert( contains(s, 10) === true)
    assert( contains(s, 15) === false)
    assert( contains(s, 30) === true)
  }

  test("diff should only contain the elements, which are not in both sets") {
    //(10, 15, 30)
    val s1: Set = union(union(a, b), singleSet(30))
    //(10, 30)
    val s2: Set = union(a, singleSet(30))

    val s: Set = diff(s1, s2)
    assert( contains(s, 10) === false)
    assert( contains(s, 15) === true)
    assert( contains(s, 30) === false)
  }

  test("filter contains only a subset, which does match the filter") {
    //(10, 15, 30)
    val s1: Set = union(union(a, b), singleSet(30))
    val filter1: Int => Boolean = i => i >= 15

    val s: Set = filter(s1, filter1)
    assert( contains(s, 10) === false)
    assert( contains(s, 15) === true)
    assert( contains(s, 30) === true)
  }

}
