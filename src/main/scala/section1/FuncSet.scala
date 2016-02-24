package section1

object FuncSet {

  type Set = Int => Boolean

  def emptySet: Set = _ => false

  def singleSet(element: Int): Set = i => i == element


  def contains(s: Set, element: Int): Boolean = ???

  def union(s: Set, t: Set): Set = ???

  def intersect(s: Set, t: Set): Set = ???

  def diff(s: Set, t: Set): Set = ???

  def filter(s: Set, p: Int => Boolean): Set = ???


  /**
    * The bounds for `forall` and `exists` are +/- 1000.
    */
  val bound = 1000

  def forall(s: Set, p: Int => Boolean): Boolean = ???

  def exists(s: Set, p: Int => Boolean): Boolean = ???

  def map(s: Set, f: Int => Int): Set = ???

  def toString(s: Set): String = ???

}
