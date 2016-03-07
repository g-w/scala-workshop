import scala.Predef._

/** Sequence pattern */
List(1, 2, 3, 4) match {
  case List(first, _, _) => println("Three item list with first element" + first)

  // List with arbitrary length
  case List(first, _*) =>  println("Large list with first: " + first)
}



/** Tuple patterns */
// Tuples können ähnlich wie case classes über ein Konstruktor pattern gematched werden:
(1, 2, 3) match {
  case (_, _, 2) => println("_, _, 2")
  case (_, 2, 4) => println("_, 2, 4")
  case (1, _, _) => println("1, _, _")
}



/** Typed patterns */
// Typed patterns ermöglichen es, anhand des Datentyps zu filtern.
// `typeMatcher` arbeitet mit einem Value vom Typ `Any`. Im Matchingblock überprüfen wir nun den
// eigentlichen Typ des Values. Die Syntax ist hier wie bei einer Variablendeklaration. Also
// `name`: `type`. Alternativ kann als name auch das Wildcardzeichen benutzt werden, wenn uns nur der
// Typ interessiert:
def typeMatcher(x: Any) = x match {
  case theString: String => println("A String with value: " + theString)
  case theInt: Int => println("An Integer with value: " + theInt)
  case _: Boolean => println("A Boolean value")
  case _ => println("Something other")
}
typeMatcher("Hallo")
typeMatcher(43)
typeMatcher(true)
typeMatcher(34.5)



// WARNING for TYPE ERASURES!!!
// As in Java there are NO generic information at runtime!
// So the JVM can NOT verify that the given Map ist of type Map[Int, Int]
// The case will match all Map[Any, Any]!
def isIntCollection(x: Any) = x match {
  case m: Map[_, Int] => true
  case a: Array[Int] => true
  case _ => false
}
// So this example will return true!
// An equal java example:
// http://docs.oracle.com/javase/tutorial/java/generics/bridgeMethods.html
isIntCollection(Map("x" -> "y"))

// But wait... FALSE?!
// Why does this not match the Array[Int]?
// Because arrays are NOT type erased on the JVM. There has always been typed arrays
// like String[] or int[] or something else in Java.
// Arrays stores their type also on runtime!
// http://code.stephenmorley.org/articles/java-generics-type-erasure/
isIntCollection(Array("hallo"))

/** Variable binding */
// How we can get this list out of the Option in one step?
Some(List(1, 2)) match {
  case Some(List(_, _)) => true
}
// Variable binding!
Some(List(1, 2)) match {
  case Some(l @ List(_, _)) => println(l.head)
}

/** Pattern guards */
//// We can adding some guards to a case clause
//User("Hans", "Meier", 19, Male) match {
//  case User(_, _, age, Male) if age > 18 => println("Adult male")
//  case User(_, _, _, Male) => println("childish male")
//}







