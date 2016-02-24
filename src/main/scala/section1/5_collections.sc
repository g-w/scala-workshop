import scala.collection.mutable

/****
 * Arrays
 ****/
val array = new Array[Int](10)
array(0) = 42
array(1) = 43
//array(11) = 44 <- ArrayIndexOutOfBoundsException

val newArray = Array(1, 2, 3, 4, 5)


/**
 * Lists
 */
// Immutable Lists - Every following operation in the list returns a new list object
val list = List(1, 2, 3, 4, 5, 6)
list(4)

// List concatenations
val addList_1 = 0 :: list
val addList_2 = list :+ 7

// Creation
val emptyList = Nil // or List() as companion object
val newList = -2 :: -1 :: 0 :: Nil
val concatList = newList ::: list

// :: or ::: are prefix operators -- Operator names, which ends with a ':' can switch there parameter. Equivalent to Nil.::(0).::(-1).::(-2)
// Head, tail
val head = concatList.head
val tail = concatList.tail

//Some collection api stuff
concatList.filter(p => p > 0)
concatList.map(p => p + 2)
concatList.mkString(",")
concatList.foldLeft(0)((sum, element) => sum + element)


//flatMap
val nestedList = List(List("Hallo", "Welt", "."), List("Hello", "world", "."))
nestedList.flatten

val mappedList = nestedList.map(l => l.map(s => s.length))
mappedList.flatten

nestedList.flatMap(l => l.map(s => s.length))
// Beside the immutable collections there is a whole package of mutable counterparts.
// e.g. the mutable ListBuffer
val listBuffer  = mutable.ListBuffer[String]()
listBuffer += "hallo"
listBuffer += "welt"
"!" +=: listBuffer
println(listBuffer.toList)


import scala.collection.mutable

/****
 * Maps
 ****/
// Immutable Map
// Each entry is a Tuple2 with a key and value
// We can define an entry directly as tuple or with the implicit arrow notation
val firstMap = Map("one" -> "hallo", "two" -> "welt")
firstMap("one")

// Concatenations
val a_map = firstMap + ("three" -> "foo")
val b_map = a_map - "three"

// Mutable counterpart
val mutableMap: mutable.Map[String, Int]  = mutable.Map()
mutableMap += ("foo" -> 1) += ("bar" -> 2)
val newMutableMap = mutableMap + ("baz" -> 3)

// Outlook:
// If you ask how such statements
//   "key" -> 123, or 1 to 5
// are possible. These are implicit conversions. Like the autoboxing known from java, scala allows own definitions
// of these conversions. So in the above example, the String "key" is implicit converted to an object of the class
// ArrowAssoc of some type A. This object has the '->' method, which takes a value of some type B and returns a
// tuple of type A, B.
//
// For more information how to write your own conversions see section 7.






