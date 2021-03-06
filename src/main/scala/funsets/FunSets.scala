package funsets


/**
  * 2. Purely Functional Sets.
  */
object FunSets {
  /**
    * We represent a set by its characteristic function, i.e.
    * its `contains` predicate.
    */
  type Set = Int => Boolean

  /**
    * Indicates whether a set contains a given element.
    */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /**
    * Returns the set of the one given element.
    */
  // fn that takes an integer n and returns the comparison of n with the original elem
  def singletonSet(elem: Int): Set = (n: Int) => n == elem


  /**
    * Returns the union of the two given sets,
    * the sets of all elements that are in either `s` or `t`.
    */
  // fn that takes an integer and returns n present in s or t
  def union(s: Set, t: Set): Set = (n: Int) => s(n) || t(n)

  /**
    * Returns the intersection of the two given sets,
    * the set of all elements that are both in `s` and `t`.
    */
  def intersect(s: Set, t: Set): Set = (n: Int) => s(n) && t(n)

  /**
    * Returns the difference of the two given sets,
    * the set of all elements of `s` that are not in `t`.
    */
  def diff(s: Set, t: Set): Set = (n: Int) => s(n) && !t(n)

  /**
    * Returns the subset of `s` for which `p` holds.
    */
  def filter(s: Set, p: Int => Boolean): Set = (n: Int) => s(n) && p(n)


  /**
    * The bounds for `forall` and `exists` are +/- 1000.
    */
  val bound = 1000

  /**
    * Returns whether all bounded integers within `s` satisfy `p`.
    */
  // iteration from -bound to bound
  // if we make it to bound without p(a) being false, all set members pass the test => true
  // if s is in set s but p(a) is false, then forall is false
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (s(a) && !p(a)) false
      else iter(a+1)
    }
    iter(-bound)
  }

  /**
    * Returns whether there exists a bounded integer within `s`
    * that satisfies `p`.
    */
  // forall means all members pass p
  // so if all pass !p, then none exist
  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, n => !p(n))

  /**
    * Returns a set transformed by applying `f` to each element of `s`.
    * given an existing Set s (which is a fn that takes an int and returns a boolean
    * and a fn f that takes an int and returns an int
    * and returns a fn of type Set (fn int to boolean)
    * then given a particular int,
    */
  def map(s: Set, f: Int => Int): Set = (n: Int) => exists(s, x => f(x) == n)

  /**
    * Displays the contents of a set
    */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
    * Prints the contents of a set on the console.
    */
  def printSet(s: Set) {
    println(toString(s))
  }
}
