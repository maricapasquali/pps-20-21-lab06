package u06lab.code

/**
  * 1) Implement trait Functions with an object FunctionsImpl such that the code
  * in TryFunctions works correctly.
 */

trait Functions {
  def sum(a: List[Double]): Double
  def concat(a: Seq[String]): String
  def max(a: List[Int]): Int // gives Int.MinValue if a is empty
}

object FunctionsImpl extends Functions {

  override def sum(a: List[Double]): Double = a.sum

  override def concat(a: Seq[String]): String = a.mkString

  override def max(a: List[Int]): Int = a match {
    case Nil => Int.MinValue
    case _  => a.max
  }
}


/*
  * 2) To apply DRY principle at the best,
  * note the three methods in Functions do something similar.
  * Use the following approach:
  * - find three implementations of Combiner that tell (for sum,concat and max) how
  *   to combine two elements, and what to return when the input list is empty
  * - implement in FunctionsImpl a single method combiner that, other than
  *   the collection of A, takes a Combiner as input
  * - implement the three methods by simply calling combiner
  *
  * When all works, note we completely avoided duplications..
 */

trait Combiner[A] {
  def unit: A
  def combine(a: A, b: A): A
}

trait CombinerFactory {
  implicit def adder: Combiner[Double]
  implicit def concatenator: Combiner[String]
  implicit def maximizer: Combiner[Int]
}

object CombinerFactory extends CombinerFactory {

  private def combiner[A](u: A, comb:(A, A) => A): Combiner[A] = new Combiner[A] {
    override def unit: A = u
    override def combine(a: A, b: A): A = comb(a, b)
  }

  override implicit def adder: Combiner[Double] = combiner(0.0, (a, b) => a+b)

  override implicit def concatenator: Combiner[String] = combiner("", (a, b) => a+b)

  override implicit def maximizer: Combiner[Int] = combiner(Int.MinValue, (a, b) => math.max(a, b))
}

object FunctionsCombiner extends Functions {
  import CombinerFactory._

  private def combine[A: Combiner](elems: Iterable[A]): A = elems match { // context bounds
    case h :: t => implicitly[Combiner[A]].combine(h, combine(t))
    case _ => implicitly[Combiner[A]].unit
  }

  def sum(a: List[Double]): Double = combine(a)

  def concat(a: Seq[String]): String = combine(a)

  def max(a: List[Int]): Int = combine(a)
}

object TryFunctionsComb extends App {
  val f: Functions = FunctionsImpl
  println(f.sum(List(10.0,20.0,30.1))) // 60.1
  println(f.sum(List()))                // 0.0
  println(f.concat(Seq("a","b","c")))   // abc
  println(f.concat(Seq()))              // ""
  println(f.max(List(-10,3,-5,0)))      // 3
  println(f.max(List()))                // -2147483648
}