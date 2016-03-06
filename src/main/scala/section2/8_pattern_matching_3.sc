/** Sealed classes */
abstract class Color(r: Int, g: Int, b: Int)
case object Red extends Color(255, 0, 0)
case object Green extends Color(0, 255, 0)
case object Blue extends Color(0, 0, 255)

/** Patterns everywhere */
// Everytime we define a var or val
// we can use a pattern matching instead of a simple assignment.
case class Car(km: Int, color: Color)
val car = Car(42000, Red)
val Car(x, _) = car
println(x)

// Very useful for tuple extraction:
val tuple = ("hallo", 123)
val (string, number) = tuple

println(string)
println(number)









